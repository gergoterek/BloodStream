import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
import { Place } from 'src/app/domain/place';
import { PlaceService } from '../place.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-place-edit',
  templateUrl: './place-edit.component.html',
  styleUrls: ['./place-edit.component.css']
})
export class PlaceEditComponent implements OnInit {

  id: number = null;
  place: Place = new Place();
  titleOfPage = 'New place';
  wantToEdit = false;

  constructor(
    private route: ActivatedRoute,
    private placeService: PlaceService,
    private location: Location,
    private router: Router,
    private authService: AuthService
  ) { }

  async ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.id = +id;
      this.place = await this.placeService.getPlace(this.id);
      this.titleOfPage = 'Modify news';
      this.wantToEdit = true;
    }
  }
 
  

  async onFormSave(place: Place) {
    if (this.id) {
      await this.placeService.modifyPlace(this.id, place);      
      this.location.back();
    } else {
      await this.placeService.addPlace(place);
      this.location.back();
    }
  }

}
