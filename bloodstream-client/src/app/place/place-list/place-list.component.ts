import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ApplicationService } from 'src/app/application/application.service';
import { AuthGuard } from 'src/app/authentication/auth.guard';
import { AuthService } from 'src/app/authentication/auth.service';
import { Application } from 'src/app/domain/application';
import { Place } from '../../domain/place';
import { PlaceService } from '../place.service';

@Component({
  selector: 'app-place-list',
  templateUrl: './place-list.component.html',
  styleUrls: ['./place-list.component.css']
})
export class PlaceListComponent implements OnInit {

  
  nextApp: Application;

  places: Place[] = [];
  filteredPlaces: Place[] = [];
  allPlaces: Place[] = [];
  applications: Application [] = [];
  
  selectedPlace = null;
  title: string;

  chooseApplication: boolean = false;

  constructor(
    private fb: FormBuilder,
    public placeService: PlaceService,
    public authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private appService: ApplicationService,
  ) { }

  cityForm = this.fb.group({
    city: [""],
  });
  get city() { return this.cityForm.get('city'); }



  async ngOnInit() {
    this.routeManage(this.route.snapshot.url);
    this.allPlaces = await this.placeService.getPlaces();    
    this.applications = await this.appService.getDonorPastApplications(this.authService.user.id);
    this.nextApp = await this.appService.getNextApplication(this.authService.user.id);
    

    if(this.chooseApplication || this.authService.isDonor()){
      this.allPlaces = this.allPlaces.filter(place => place.active === true);      
    }
    this.places = this.allPlaces;
    
    this.initTitle();
  }

  async routeManage(url: any){
    // const placeId = this.route.snapshot.paramMap.get('placeId');
    // if (placeId) {
    //   this.placeId = +placeId;
    //   if (this.nextApp !== null){
    //       this.nextApp = null; 
    //     } else {         
    //       this.nextApp = await this.applicationService.getApplication(this.applyplaceIdId);
    //     }        
    // }
    // this.router.navigate(["/donor/profile", this.id]);

    if (url.length === 3){
      if(String(url).split(",")[2] === "del"){
        this.router.navigate(['/place'])
      }
    }
    if(String(url) === "application" || this.authService.isDonor()){
      this.chooseApplication = true;
    }
  }

  onKey() {
    if (!this.cityForm.value.city){
      this.places = this.allPlaces;
    } else{
      this.places = this.allPlaces.filter(place => place.city.toLowerCase().includes(this.cityForm.value.city.toLowerCase()));        
    }
    this.changeTitle();
  }



  ready(): boolean {    
    return new Date(this.authService.user.nextDonationDate).getTime() <= new Date().getTime();
  }

  showPlaces(): boolean{
    return (this.ready() && !this.nextApp) || !this.authService.isDonor();
  }

  initTitle(){
    this.title = this.authService.isDonor ? "Choose a donation place!" : "Donation places"; 
    if( this.allPlaces.length === 0){
      this.title = "There is no active place right now!";
    }
  }

  changeTitle(){
    if(this.places.length === 0) {
      this.title = "Not found!";
    } else {
      this.title = "Choose a donation place!"
    }
    //console.log(this.cityForm.value.city)
  }

}
