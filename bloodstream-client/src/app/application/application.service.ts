import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Application } from '../domain/application';
import { httpOptions } from '../authentication/auth.service';


@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  private allAppUrl = 'http://localhost:8080/application/all';
  private appUrl = 'http://localhost:8080/application';
  private newAppUrl = 'http://localhost:8080/application/new';
  private donorPastAppUrl = 'http://localhost:8080/application/donor';
  private nextAppUrl = 'http://localhost:8080/application/next';
  private delAppUrl = 'http://localhost:8080/application/delete';
  private confirmedAppUrl = 'http://localhost:8080/application/donation';
  private transportedAppUrl = 'http://localhost:8080/application/transport';
  private dateUrl = 'http://localhost:8080/application/date';

  constructor(
    private http: HttpClient,
  ) { }
  

  getApplications(): Promise<Application[]> {
    return this.http.get<Application[]>(
      this.allAppUrl,
      httpOptions
    ).toPromise();
  }


  getApplication(id: number): Promise<Application> {
    return this.http.get<Application>(
      `${this.appUrl}/${id}`,
      httpOptions
    ).toPromise();
  }

  newApplication(application: Application): Promise<Application> {
    return this.http.post<Application>(
      this.newAppUrl,
      application,
      httpOptions
    ).toPromise();
  }

  getDonorPastApplications(id: number): Promise<Application[]> {
    return this.http.get<Application[]>(
      `${this.donorPastAppUrl}/${id}`,
      httpOptions
    ).toPromise();
  }

  getNextApplication(id: number): Promise<Application> {
    return this.http.get<Application>(
      `${this.nextAppUrl}/${id}`,
      httpOptions
    ).toPromise();
  }

  setConfirmed(id: number, app: Application): Promise<Application> {
    return this.http.patch<Application>(
      `${this.confirmedAppUrl}/${id}`,
      app,
      httpOptions
    ).toPromise();
  }

  setTransport(id: number, app: Application): Promise<Application> {
    return this.http.patch<Application>(
      `${this.transportedAppUrl}/${id}`,
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

  deleteApplication(id: number): Promise<Application> {
    return this.http.delete<Application>(
      `${this.delAppUrl}/${id}`,
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

  
}
