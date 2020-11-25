import { Component, OnInit } from '@angular/core';
import { Donor } from '../../domain/donor';
import { BloodType } from '../../domain/bloodtype';
import { Role } from '../../domain/role';
import { DonorService } from '../donor.service';
import { AuthService } from 'src/app/authentication/auth.service';


@Component({
  selector: 'app-donor-list',
  templateUrl: './donor-list.component.html',
  styleUrls: ['./donor-list.component.css']
})
export class DonorListComponent implements OnInit {

  donors: Donor[] = [];
  filteredDonors = [];
  selectedBloodType = '';
  selectedDonors = null;

  now: Date = new Date();

  constructor(
    public donorService: DonorService,
    public authService: AuthService,
  ) { }


  async ngOnInit() {
    this.donors = await this.donorService.getDonors();
    this.donors = this.donors.filter( donor => donor.role === "ROLE_DONOR");

    this.donors.sort(function(a, b){
      if(a.donorName < b.donorName) { return -1; }
      if(a.donorName > b.donorName) { return 1; }
      return 0;
  })
    this.filterDonors();
  }

  filterDonors(){
    this.filteredDonors = this.selectedBloodType === ''
      ? this.donors
      : this.donors.filter(donor => donor.bloodType === this.selectedBloodType)
  }

  onFilterChange(data){
    this.selectedBloodType = data;
    this.filterDonors();
  }

  nextDon(date: string): boolean {
    return new Date(date).getTime()<this.now.getTime();
  }

  


  

}
