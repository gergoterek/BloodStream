import { Component, OnInit } from '@angular/core';
import { Place } from '../../domain/place';
import { PlaceService } from '../place.service';

@Component({
  selector: 'app-place-list',
  templateUrl: './place-list.component.html',
  styleUrls: ['./place-list.component.css']
})
export class PlaceListComponent implements OnInit {


  places: Place[] = [];
  // filteredDonors = [];
  // selectedBloodType = '';
   selectedPlace = null;

  constructor(
    public placeService: PlaceService,
  ) { }


  async ngOnInit() {
    this.places = await this.placeService.getPlaces();
  }

  // onFilterChange(data){
  //   this.selectedBloodType = data;
  //   this.filterDonors();
  // }



}
