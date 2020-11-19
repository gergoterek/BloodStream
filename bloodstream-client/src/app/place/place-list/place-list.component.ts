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
  allPlaces: Place[] = [];
  // filteredDonors = [];
  // selectedBloodType = '';
   selectedPlace = null;
   title: string;

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
    this.title = this.authService.isDonor ? "Choose a donation place!" : "Donation places"; 
    this.allPlaces = await this.placeService.getPlaces();
    this.places = this.allPlaces;
    this.nextApp = await this.appService.getNextApplication(this.authService.user.id);

    let del = this.route.snapshot.url;
    if (del.length === 3){
      if(String(del).split(",")[2] === "del"){
        this.router.navigate(['/place'])
      }
    }
    //console.log(new Date(this.authService.user.nextDonationDate).getTime());
  }

  search() {
    if (!this.cityForm.value.city){
      this.places = this.allPlaces;
    } else{
      this.places = this.allPlaces.filter(place => place.city.toLowerCase() === this.cityForm.value.city.toLowerCase());      
    }

    if(this.places.length === 0) {
      this.title = "Not found!";
    } else {
      this.title = "Choose a donation place!"
    }
  }



  ready(): boolean {    
    return new Date(this.authService.user.nextDonationDate).getTime() <= new Date().getTime();
  }

  showPlaces(): boolean{
    return (this.ready() && !this.nextApp) || !this.authService.isDonor();
  }

}
