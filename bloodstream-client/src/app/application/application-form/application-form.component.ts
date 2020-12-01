import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
import { Application } from 'src/app/domain/application';
import { Donor } from 'src/app/domain/donor';
import { Place } from 'src/app/domain/place';
import { PlaceService } from 'src/app/place/place.service';
import { ApplicationService } from '../application.service';
import { MyApplicationComponent } from '../my-application/my-application.component';

@Component({
  selector: 'app-application-form',
  templateUrl: './application-form.component.html',
  styleUrls: ['./application-form.component.css']
})
export class ApplicationFormComponent implements OnInit {

  id: number;
  place: Place;
  start: string;
  close: string;

  set: boolean = false;
  setTime: Date = null;

  now: Date = new Date();

  minDate: Date;
  maxDate: Date;
  time: string;
  times: string[] = [];
  dateTime: string;

  isFullDate: boolean = true;
  date: Date;
  
  application: Application = new Application();
  applications: Application [] = [];

  dateFilter: any;

  days: boolean [] = [];

  daysEnableToApply = 14;
  appInterval = 30;
  

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private placeService: PlaceService,
    private applicationService: ApplicationService,
    public authService: AuthService,
    private router: Router,
    //private myApplication: MyApplicationComponent,
  ) { }

  applicationForm = this.fb.group({
    appliedDate: [undefined, [Validators.required]],
    directedDonationCode: ['', [Validators.required, Validators.pattern(/\d+/), Validators.minLength(9)]],
  });

  get appliedDate() { return this.applicationForm.get('appliedDate'); }
  get directedDonationCode() { return this.applicationForm.get('directedDonationCode'); }


  async ngOnInit() {
    this.placeInit();
    this.minDate = (new Date(this.now).getTime() >= new Date(this.authService.user.nextDonationDate).getTime()) 
        ? this.addMinutes( this.now, 1440 * 1) 
        : new Date(this.authService.user.nextDonationDate);

    this.maxDate = this.addMinutes(this.minDate, 1440 * this.daysEnableToApply);    
  }

  isFreeTime(time: string): boolean{
    if(this.isSetDate){
      return this.applications.filter(app => new Date(app.appliedDate).getTime() === 
        (this.addMinutes(this.applicationForm.value.appliedDate, this.timeToDecimal(time))).getTime()).length === 0;
    } else {
      return false;
    }
  }

  isSetDate(): boolean {
    return this.applicationForm.value.appliedDate;
  }

    
    
  async onFormSave() {
    this.applicationForm.patchValue({
      appliedDate: this.setTime,
    });

    this.applicationForm.value.donor = this.authService.user;

    this.applicationForm.value.place = this.place;
    //console.log(JSON.stringify(this.applicationForm.value));
    await this.applicationService.newApplication(this.applicationForm.value as Application); 
    
    this.router.navigate(['/donation', await (await this.applicationService.getNextApplication(this.authService.user.id)).applyId]);
  }




  async choose(time: string){
    if (this.appliedDate.value){
      this.set=true;
    }
    
    this.time = time;
    if (this.applicationForm.value.appliedDate){
      this.date = this.applicationForm.value.appliedDate;
      //console.log(this.date);
      //console.log(new Date(this.appliedDate.value).getMinutes() + this.timeToDecimal(time));
      //console.log(this.addMinutes(this.appliedDate.value, this.timeToDecimal(time)));
      this.setTime =  this.addMinutes(this.appliedDate.value, this.timeToDecimal(time));  
      //console.log(this.setTime);

      this.isFullDate = false;
      // this.applications = this.applications.filter(app => new Date(app.appliedDate).getTime() === this.setTime.getTime());
      
      // if(this.applications.length > 0) {
      //   console.log(JSON.stringify(this.applications));
      //   this.isFullDate = true;
      // } else {
      //   this.isFullDate = false;
      // }
      

      // console.log("????" + await this.applicationService.isFullDate(this.setTime, this.place.id ));

      // if( !await this.applicationService.isFullDate(this.setTime, this.place.id)){
      //     this.isFullDate = false;
      // } else {
      //   this.isFullDate = true;
      // }
    }
    
  }

  // isNull() {
  //   return this.applicationForm.value.appliedDate !== null;
  // }

  async placeInit(){
    const id = this.route.snapshot.paramMap.get('placeId');
    if (id) {
      this.id = +id;
      this.place = await this.placeService.getPlace(this.id);
      //console.log(JSON.stringify(this.place));
    }   
    this.start = this.decimalToTime(this.place.openingTime.startTime);
    this.close = this.decimalToTime(this.place.openingTime.closingTime);

    this.fillTimes();

    this.applications = (await this.applicationService.getApplications()).filter(app => app.place.id === this.place.id);
    this.applications = this.applications.filter(app => app.donation === null);
    
    this.days.push(this.place.openingTime.monday, this.place.openingTime.tuesday, this.place.openingTime.wednesday, this.place.openingTime.thursday,
                     this.place.openingTime.friday, this.place.openingTime.saturday, this.place.openingTime.sunday);
     //console.log(JSON.stringify(this.days));


     this.dateFilter = (date: Date) => 
                                           (this.days[6] ? date.getDay()===0 : false) 
                                        || (this.days[1] ? date.getDay()===1 : false) 
                                        || (this.days[2] ? date.getDay()===2 : false) 
                                        || (this.days[3] ? date.getDay()===3 : false) 
                                        || (this.days[4] ? date.getDay()===4 : false) 
                                        || (this.days[5] ? date.getDay()===5 : false) 
                                        || (this.days[0] ? date.getDay()===6 : false);
                                                        
  }

  fillTimes(){
    let temp = this.place.openingTime.closingTime - this.place.openingTime.startTime;
    this.times.push(this.decimalToTime(this.place.openingTime.closingTime - temp));
    //console.log(temp);
    while(temp>=this.appInterval){
      temp -= this.appInterval;
      this.times.push(this.decimalToTime(this.place.openingTime.closingTime - temp));
      //console.log(temp);
    }
  }

  addMinutes(date, minutes) {
    return new Date(date.getTime() + minutes*60000);
  }
  

  timeToDecimal(time: string): number {
    //console.log(time);
    let a = time.split(':');
    let min = (+a[0]) * 60 + (+a[1]);     
    //console.log(min);
    return min;
  }

  decimalToTime(int: number): string{
    //console.log(int);
    let temp = 0;
    while (int>=60){
        int -= 60;
        ++temp;        
    }
    let hour = temp<10 ? ("0" + temp.toString()) : temp.toString();
    let min = int<10 ? ("0" + int.toString()) : int.toString();    
    return hour + ":" + min;
  }



}
