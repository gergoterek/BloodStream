import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Donor } from '../../domain/donor';
import { DonorService } from '../../donor/donor.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {


  now = Date.now();//new Date(2020, 11, 09);
  minDate: Date;//18-65év
  maxDate: Date;


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

  message: string;
  isFailed: boolean;
  hidePassword = true;
  donorForm = this.fb.group({
    donorName: ['', [Validators.required]],
    username: ['', [Validators.required, Validators.minLength(6)]],
    password: ['', [Validators.required, Validators.minLength(8)]],
    taj: ['', [Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/), Validators.minLength(9), Validators.maxLength(9)]],
    birthDate: ['', [Validators.required]],
    idCard: ['', [Validators.required, Validators.minLength(6)]],
    male: ['', [Validators.required]],
    accepted: [false, [Validators.required, Validators.requiredTrue]],    
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
  get accepted() { return this.donorForm.get('accepted'); }

  constructor(
    private fb: FormBuilder,
    public authService: AuthService,
    //public donorService: DonorService,
    // private location: Location,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.minDate = new Date();
    this.minDate.setFullYear(this.minDate.getFullYear()-65);
    this.maxDate = new Date();
    this.maxDate.setFullYear(this.maxDate.getFullYear()-18);
    this.message = "";
    this.isFailed = false;
  }

  async onFormSave() {
    this.accepted.disable();
    //this.print();
    //console.log(JSON.stringify(this.donorForm.value))
    //this.donor = Object.assign(new Donor(), this.donorForm.value);    
    const donor = this.donorForm.value as Donor;
    const success = await this.authService.registration(donor);
    //console.log(JSON.stringify(success));
    if (success) {
      this.router.navigate(["/login"])
    } else {
      this.message = 'Registration failed, try with different username!'
    }
  }


  // print() {
  //   console.log(this.accepted.value);
  // }



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
