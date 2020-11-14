import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Application } from '../domain/application';
import { httpOptions } from '../authentication/auth.service';


@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  private allUrl = 'http://localhost:8080/application/all';
  private url = 'http://localhost:8080/application';
  private delUrl = 'http://localhost:8080/application/delete';

  constructor(
    private http: HttpClient
  ) { }

  

  getApplications(): Promise<Application[]> {
    return this.http.get<Application[]>(
      this.allUrl,
      httpOptions
    ).toPromise();
  }
  
}
