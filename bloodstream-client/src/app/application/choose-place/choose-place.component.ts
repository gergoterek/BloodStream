import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
import { Place } from 'src/app/domain/place';
import { PlaceService } from 'src/app/place/place.service';
import { ApplicationService } from '../application.service';

@Component({
  selector: 'app-choose-place',
  templateUrl: './choose-place.component.html',
  styleUrls: ['./choose-place.component.css']
})
export class ChoosePlaceComponent implements OnInit {

  places: Place[] = [];
  allPlaces: Place[] = [];
  selectedPlace = null;
  title: string;

  cityForm = this.fb.group({
    city: [""],
  });
  get city() { return this.cityForm.get('city'); }

  constructor(
    private fb: FormBuilder,
    public placeService: PlaceService,
    public authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private appService: ApplicationService,
  ) { }

  async ngOnInit() {
    this.allPlaces = await this.placeService.getPlaces();
    this.places = this.allPlaces;
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

  

}
