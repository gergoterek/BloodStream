import { Component, OnInit } from '@angular/core';
import { Donor } from '../domain/donor';
import { BloodType } from '../domain/bloodtype';
import { Role } from '../domain/role';
import { DonorService } from '../donor.service';

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

  constructor(
    public donorService: DonorService
  ) { }


  async ngOnInit() {
    this.donors = await this.donorService.getDonors();
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

  


  

}
