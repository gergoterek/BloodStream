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

  constructor(
    public donorService: DonorService
  ) { }

  ngOnInit() {
  }

  


  

}
