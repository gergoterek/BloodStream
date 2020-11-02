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

  constructor(
    private http: HttpClient
  ) { }

  getDonors(): Promise<Donor[]> {
    return this.http.get<Donor[]>(
      this.donorUrl,
      httpOptions
    ).toPromise();
  }

//donors: Donor[] = this.getDonors();
  donors: Donor[] = [
    {
      id: 1,
      taj: 124214,
      birthDate: 'ehgwth',
      bloodType: BloodType.A_POZ,
      donorName: 'string',
      idCard: 'string',
      male: true,
      nextDonationDate: 'string',
      password: 'string',
      role: Role.Guest,
      totalDonations: 3,
      username: 'string',
    },

    {
      id: 2,
      taj: 563546,
      birthDate: 'trhrt',
      bloodType: BloodType.A_NEG,
      donorName: 'string',
      idCard: 'string',
      male: false,
      nextDonationDate: 'string',
      password: 'string',
      role: Role.Admin,
      totalDonations: 1,
      username: 'string',
    }
];



filteredDonors: Donor[] = this.donors;


async filterChange(filterValue: string) {
  if (typeof filterValue === 'string') {
    if (filterValue === '') {
      //this.filteredDonors = this.donors;
      this.filteredDonors = await this.getDonors();
    } else {
      // Lehet ciklussal is :)
      this.filteredDonors = this.donors.filter(donor => {
        return donor.bloodType === filterValue;
      });
    }
  }
}

}
