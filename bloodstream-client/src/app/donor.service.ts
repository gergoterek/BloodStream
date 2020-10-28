import { Injectable } from '@angular/core';
import { Donor } from './domain/donor';
import { BloodType } from './domain/bloodtype';
import { Role } from './domain/role';

@Injectable({
  providedIn: 'root'
})
export class DonorService {

  constructor() { }


  donors: Donor[] = [
    {
      id: 1,
      taj: 124214,
      birthDate: 'ehgwth',
      bloodType: BloodType.A_POZ,
      name: 'string',
      idCard: 'string',
      male: true,
      nextDonationDate: 'string',
      password: 'string',
      role: Role.Guest,
      totalDonations: 3,
      userName: 'string',
    },

    {
      id: 2,
      taj: 563546,
      birthDate: 'trhrt',
      bloodType: BloodType.A_NEG,
      name: 'string',
      idCard: 'string',
      male: false,
      nextDonationDate: 'string',
      password: 'string',
      role: Role.Admin,
      totalDonations: 1,
      userName: 'string',
    }
];



filteredDonors: Donor[] = this.donors;


filterChange(filterValue: string) {
  if (typeof filterValue === 'string') {
    if (filterValue === '') {
      this.filteredDonors = this.donors;
    } else {
      // Lehet ciklussal is :)
      this.filteredDonors = this.donors.filter(donor => {
        return donor.bloodType === filterValue;
      });
    }
  }
}

}
