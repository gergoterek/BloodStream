import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
import { Donor } from 'src/app/domain/donor';
import { DonorService } from '../donor.service';

@Component({
  selector: 'app-donor-edit',
  templateUrl: './donor-edit.component.html',
  styleUrls: ['./donor-edit.component.css']
})
export class DonorEditComponent implements OnInit {

  id: number;
  donor: Donor;


  constructor(
    private route: ActivatedRoute,
    private donorService: DonorService,
    private fb: FormBuilder,
    // private location: Location,
    private router: Router,
    public authService: AuthService,
  ) { }

  async ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.id = +id;
      this.donor = await this.donorService.getDonor(this.id);
      this.donorForm.patchValue(this.donor);
      this.donorForm.patchValue({
        bloodType: (this.donor.bloodType === null) ? "undefined" : this.donor.bloodType,
      }); 

    }
    //console.log(JSON.stringify(this.donor));
  }


  donorForm = this.fb.group({
    taj: ['', [Validators.required]],
    idCard: ['', [Validators.required]],
    role: [{value: 'UNDEFINED', disabled: this.authService.isNurse()}, [Validators.required]],
    bloodType: ['', [Validators.required]],
    //nextDonationDate: ['', [Validators.required]],
  });  

  get taj() { return this.donorForm.get('taj'); }
  get idCard() { return this.donorForm.get('idCard'); }
  get role() { return this.donorForm.get('role'); }
  get bloodType() { return this.donorForm.get('bloodType'); }

  async onFormSave() {
    //this.donor = Object.assign(new Donor(), this.donorForm.value);   
    //console.log(JSON.stringify(this.donor)); 
    const donor = this.donorForm.value as Donor;
    //console.log(JSON.stringify(donor)); 
    await this.donorService.modifyDonor(this.id, donor);
  }

}
