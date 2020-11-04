import { Injectable } from '@angular/core';
import { Donor } from './domain/donor';
import { BloodType } from './domain/bloodtype';
import { Role } from './domain/role';
import { HttpClient } from '@angular/common/http';
import { httpOptions } from "./auth.service";


@Injectable({
  providedIn: 'root'
})
export class DonorService {

  private donorUrl = 'http://localhost:8080/donor/all';
  private donorIdUrl = 'http://localhost:8080/donor/profile';

  constructor(
    private http: HttpClient
  ) { }

  getDonors(): Promise<Donor[]> {
    return this.http.get<Donor[]>(
      this.donorUrl,
      httpOptions
    ).toPromise();
  }

  getDonor(id: number): Promise<Donor> {
    return this.http.get<Donor>(
      `${this.donorIdUrl}/${id}`,
      httpOptions
    ).toPromise();
  }

}
