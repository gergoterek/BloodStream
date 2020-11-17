import { Component, OnInit } from '@angular/core';
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
  // filteredDonors = [];
  // selectedBloodType = '';
   selectedPlace = null;
   title: string;

  constructor(
    public placeService: PlaceService,
    public authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private appService: ApplicationService,
  ) { }


  async ngOnInit() {
    this.title = this.authService.isDonor ? "Choose a donation place!" : "Donation places"; 
    this.places = await this.placeService.getPlaces();
    this.nextApp = await this.appService.getNextApplication(this.authService.user.id);

    let del = this.route.snapshot.url;
    if (del.length === 3){
      if(String(del).split(",")[2] === "del"){
        this.router.navigate(['/place'])
      }
    }
    
  }

  ready(): boolean {    
    return new Date(this.authService.user.nextDonationDate).getDate() <= new Date().getDate();
  }

  showPlaces(): boolean{
    return (this.ready() && !this.nextApp) || !this.authService.isDonor();
  }

}
