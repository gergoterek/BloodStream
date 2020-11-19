import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
import { Application } from 'src/app/domain/application';
import { Donor } from 'src/app/domain/donor';
import { Place } from 'src/app/domain/place';
import { PlaceService } from 'src/app/place/place.service';
import { ApplicationService } from '../application.service';

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
  

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private placeService: PlaceService,
    private applicationService: ApplicationService,
    public authService: AuthService,
    //public matDatepickerFilter: DatepickerFilterExample,
  ) { }

  applicationForm = this.fb.group({
    appliedDate: [undefined, [Validators.required]],
  });

  get appliedDate() { return this.applicationForm.get('appliedDate'); }


  async ngOnInit() {
    this.placeInit();
    this.minDate = (new Date(this.now).getTime() >= new Date(this.authService.user.nextDonationDate).getTime()) ?
    this.addMinutes(this.now, 1440) : new Date(this.authService.user.nextDonationDate);

    this.maxDate = this.addMinutes(this.minDate, 1440*14);

    this.applications = (await this.applicationService.getApplications()).filter(app => app.place.id === this.place.id);
    this.applications = this.applications.filter(app => app.donation === null);
  }
  // toggle = true;
  // status = 'Enable'; 

  // enableDisableRule(job) {
  //     this.toggle = !this.toggle;
  //     this.status = this.toggle ? 'Enable' : 'Disable';
  // }
      

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
    console.log(JSON.stringify(this.applicationForm.value));
    await this.applicationService.newApplication(this.applicationForm.value as Application); 
  }




  async choose(time: string){
    if (this.appliedDate.value){
      this.set=true;
    }
    
    this.time = time;
    if (this.applicationForm.value.appliedDate){
      this.date = this.applicationForm.value.appliedDate;
      console.log(this.date);
      //console.log(new Date(this.appliedDate.value).getMinutes() + this.timeToDecimal(time));
      console.log(this.addMinutes(this.appliedDate.value, this.timeToDecimal(time)));
      this.setTime =  this.addMinutes(this.appliedDate.value, this.timeToDecimal(time));  
      console.log(this.setTime);

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

    
  }

  fillTimes(){
    let temp = this.place.openingTime.closingTime - this.place.openingTime.startTime;
    this.times.push(this.decimalToTime(this.place.openingTime.closingTime - temp));
    //console.log(temp);
    while(temp>=30){
      temp -= 30;
      this.times.push(this.decimalToTime(this.place.openingTime.closingTime - temp));
      //console.log(temp);
    }
    console.log(JSON.stringify(this.times));
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
// export class DatepickerFilterExample {
//   myFilter = (d: Date | null): boolean => {
//     const day = (d || new Date()).getDay();
//     // Prevent Saturday and Sunday from being selected.
//     return day !== 0 && day !== 6;
//   }
// }
