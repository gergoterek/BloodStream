import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Place } from '../domain/place';

import { httpOptions } from '../authentication/auth.service';
@Injectable({
  providedIn: 'root'
})
export class PlaceService {

  private allUrl = 'http://localhost:8080/place/all';
  private url = 'http://localhost:8080/place';
  private delUrl = 'http://localhost:8080/place/delete';

  constructor(
    private http: HttpClient
  ) { }

  getPlaces(): Promise<Place[]> {
    return this.http.get<Place[]>(
      this.allUrl,
      httpOptions
    ).toPromise();
  }

  getPlace(id: number): Promise<Place> {
    return this.http.get<Place>(
      `${this.url}/${id}`,
      httpOptions
    ).toPromise();
  }

  modifyPlace(id: number, place: Place): Promise<Place> {
    console.log(id);
    return this.http.put<Place>(
      `${this.url}/${id}`,
      place,
      httpOptions
    ).toPromise();
  }

  addPlace(place: Place): Promise<Place> {
    return this.http.post<Place>(
      this.url,
      place,
      httpOptions
    ).toPromise();
  }

  deletePlace(id: number): Promise<Place> {
    return this.http.delete<Place>(
      `${this.delUrl}/${id}`,
      httpOptions
    ).toPromise();
  }


}
