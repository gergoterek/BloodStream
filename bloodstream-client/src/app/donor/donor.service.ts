import { Injectable } from '@angular/core';
import { Donor } from '../domain/donor';
import { BloodType } from '../domain/bloodtype';
import { Role } from '../domain/role';
import { HttpClient } from '@angular/common/http';
import { httpOptions } from "../authentication/auth.service";


@Injectable({
  providedIn: 'root'
})
export class DonorService {

  private donorUrl = 'http://localhost:8080/donor/all';
  private donorIdUrl = 'http://localhost:8080/donor/profile';
  private donorEditUrl = 'http://localhost:8080/donor/profile/edit';

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

  



  //"/profile/:id/edit"
  modifyDonor(id: number, donor: Donor): Promise<Donor> {
    return this.http.patch<Donor>(
      `${this.donorEditUrl}/${id}`,
      donor,
      httpOptions
    ).toPromise();
  }
  



}
