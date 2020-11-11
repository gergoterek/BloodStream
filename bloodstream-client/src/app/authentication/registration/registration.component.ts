import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Donor } from '../../domain/donor';
import { DonorService } from '../../donor/donor.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {


  now = Date.now();//new Date(2020, 11, 09);
  minDate = new Date(1955, 1,1)//18-65év
  maxDate = new Date(2002, 1,1);

  // dateFilter = date => {
  //   const day = date.getDay();
  //   return day !== 0 && day !== 6;
  // }
  // minDate = this.makeDate(this.now).setFullYear(this.now.getTime().getFullYear() - 18);

  // makeDate(date:Date) {
  //   return new Date(date.getTime()); //<--error here
  // }
  //const newDate = new Date(); 
  // console.log(makeDate(newDate));

  hidePassword = true;
  donorForm = this.fb.group({
    donorName: ['', [Validators.required]],
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
    taj: ['', [Validators.required]],
    birthDate: ['', [Validators.required]],
    idCard: ['', [Validators.required]],
    male: ['', [Validators.required]],    
    // bloodType: [null],    
    // nextDonationDate: [null],    
    // role: [null],    
    // id: [null],    
  });
  

  get donorName() { return this.donorForm.get('donorName'); }
  get username() { return this.donorForm.get('username'); }
  get password() { return this.donorForm.get('password'); }
  get taj() { return this.donorForm.get('taj'); }
  get birthDate() { return this.donorForm.get('birthDate'); }
  get idCard() { return this.donorForm.get('idCard'); }
  get male() { return this.donorForm.get('male'); }

  constructor(
    private fb: FormBuilder,
    public donorService: DonorService,
    // private location: Location,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  async onFormSave() {
    //this.donor = Object.assign(new Donor(), this.donorForm.value);    
    const donor = this.donorForm.value as Donor;
    await this.donorService.registration(donor);
  }

}
// export const MY_FORMATS = {
//   parse: {
//     dateInput: 'LL',
//   },
//   display: {
//     dateInput: 'YYYY-MM-DD',
//     monthYearLabel: 'YYYY',
//     dateA11yLabel: 'LL',
//     monthYearA11yLabel: 'YYYY',
//   },
// };
