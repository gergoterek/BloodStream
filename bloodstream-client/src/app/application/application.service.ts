import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Application } from '../domain/application';
import { httpOptions } from '../authentication/auth.service';
import { Donation } from '../domain/donation';


@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  private allUrl = 'http://localhost:8080/application/all';
  private url = 'http://localhost:8080/application';
  private donorUrl = 'http://localhost:8080/application/donor';
  private nextUrl = 'http://localhost:8080/application/next';
  private delUrl = 'http://localhost:8080/application/delete';
  private donationUrl = 'http://localhost:8080/application/donation';
  private transportUrl = 'http://localhost:8080/application/transport';
  private dateUrl = 'http://localhost:8080/application/date';

  constructor(
    private http: HttpClient
  ) { }

  

  getApplications(): Promise<Application[]> {
    return this.http.get<Application[]>(
      this.allUrl,
      httpOptions
    ).toPromise();
  }

  getApplication(id: number): Promise<Application> {
    return this.http.get<Application>(
      `${this.url}/${id}`,
      httpOptions
    ).toPromise();
  }

  newApplication(application: Application): Promise<Application> {
    return this.http.post<Application>(
      this.url,
      application,
      httpOptions
    ).toPromise();
  }

  getDonorPastApplications(id: number): Promise<Application[]> {
    return this.http.get<Application[]>(
      `${this.donorUrl}/${id}`,
      httpOptions
    ).toPromise();
  }

  getNextApplication(id: number): Promise<Application> {
    return this.http.get<Application>(
      `${this.nextUrl}/${id}`,
      httpOptions
    ).toPromise();
  }

  setConfirmed(id: number, app: Application): Promise<Application> {
    return this.http.patch<Application>(
      `${this.donationUrl}/${id}`,
      app,
      httpOptions
    ).toPromise();
  }

  setTransport(id: number, app: Application): Promise<Application> {
    return this.http.patch<Application>(
      `${this.transportUrl}/${id}`,
      app,
      httpOptions
    ).toPromise();
  }

  isFullDate(date: Date, id: number): Promise<Boolean> {
    return this.http.get<Boolean>(
      `${this.dateUrl}/${date}/${id}`,
      httpOptions
    ).toPromise();
  }
  

  

  // modifyPlace(id: number, place: Place): Promise<Place> {
  //   return this.http.put<Place>(
  //     `${this.url}/${id}`,
  //     place,
  //     httpOptions
  //   ).toPromise();
  // }

  // addPlace(place: Place): Promise<Place> {
  //   return this.http.post<Place>(
  //     this.url,
  //     place,
  //     httpOptions
  //   ).toPromise();
  // }

  deleteApplication(id: number): Promise<Application> {
    return this.http.delete<Application>(
      `${this.delUrl}/${id}`,
      httpOptions
    ).toPromise();
  }
}
