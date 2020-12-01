import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Place } from '../domain/place';

import { httpOptions } from '../authentication/auth.service';
@Injectable({
  providedIn: 'root'
})
export class PlaceService {

  private allPlacesUrl = 'http://localhost:8080/place/all';
  private placeUrl = 'http://localhost:8080/place';
  // private cityUrl = 'http://localhost:8080/place/city';
  // private delUrl = 'http://localhost:8080/place/delete';

  constructor(
    private http: HttpClient
  ) { }

  getPlaces(): Promise<Place[]> {
    return this.http.get<Place[]>(
      this.allPlacesUrl,
      httpOptions
    ).toPromise();
  }


  getPlace(id: number): Promise<Place> {
    return this.http.get<Place>(
      `${this.placeUrl}/${id}`,
      httpOptions
    ).toPromise();
  }


  modifyPlace(id: number, place: Place): Promise<Place> {
    return this.http.put<Place>(
      `${this.placeUrl}/${id}`,
      place,
      httpOptions
    ).toPromise();
  }

  addPlace(place: Place): Promise<Place> {
    return this.http.post<Place>(
      this.placeUrl,
      place,
      httpOptions
    ).toPromise();
  }
}
  // deletePlace(id: number): Promise<Place> {
  //   return this.http.delete<Place>(
  //     `${this.delUrl}/${id}`,
  //     httpOptions
  //   ).toPromise();
  // }

  
  // getPlacesByCity(city: string): Promise<Place[]> {
  //   return this.http.get<Place[]>(
  //     `${this.cityUrl}/${city}`,
  //     httpOptions
  //   ).toPromise();
  // }


