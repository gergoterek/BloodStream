import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Place } from '../domain/place';

import { httpOptions } from '../authentication/auth.service';
@Injectable({
  providedIn: 'root'
})
export class PlaceService {

  private allPlacesUrl = 'http://localhost:8080/place/all';
  private addPlaceUrl = 'http://localhost:8080/place/add';

  constructor(
    private http: HttpClient
  ) { }

  getPlaces(): Promise<Place[]> {
    return this.http.get<Place[]>(
      this.allPlacesUrl,
      httpOptions
    ).toPromise();
  }

  addPlace(place: Place): Promise<Place> {
    return this.http.post<Place>(
      this.addPlaceUrl,
      place,
      httpOptions
    ).toPromise();
  }


}
