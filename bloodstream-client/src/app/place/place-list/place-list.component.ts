import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
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
    public authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }


  async ngOnInit() {
    this.places = await this.placeService.getPlaces();

    let del = this.route.snapshot.url;
    if (del.length === 3){
      if(String(del).split(",")[2] === "del"){
        this.router.navigate(['/place'])
      }
    }
  }

}
