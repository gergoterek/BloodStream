import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import { Donor } from 'src/app/domain/donor';
import { Message } from 'src/app/domain/message';
import { DonorService } from 'src/app/donor/donor.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent implements OnInit {

  id: number;
  donors: Donor [] = []
  errorMsg = "";
  
  constructor(
    private fb: FormBuilder,
    public messageService: MessageService,
    private router: Router,
    private route: ActivatedRoute,
    private donorService: DonorService,
  ) { }

  async ngOnInit() {
      const id = this.route.snapshot.paramMap.get('id');
      if (id) {        
        this.id = +id;
        this.msgForm.patchValue({
          donorID: id
          //(this.donor.bloodType === null) ? "undefined" : this.donor.bloodType,
        }); 
        this.donorID.disable();
      } else {
        this.donors = await this.donorService.getDonors();
        this.donors = this.donors.filter(donor => donor.role === "ROLE_DONOR");
        console.log(JSON.stringify(this.donors));
      }

      
}
  
  
  msgForm = this.fb.group({
    message: ['', [Validators.required]],
    title: ['', [Validators.required]],
    donorID: ['', [Validators.required, Validators.pattern(/^\d+$/)]]
  });
  
  get message() { return this.msgForm.get('message'); }
  get title() { return this.msgForm.get('title'); }
  get donorID() { return this.msgForm.get('donorID'); }

  async onFormSave() {
    this.donorID.enable();
    const msg = this.msgForm.value as Message;
    await this.messageService.sendMsgToDonor(msg, parseInt(this.donorID.value));
    this.router.navigate(['/message', 'del']);
  }

  existingID(): boolean {
    if(this.donors && this.msgForm.value.donorID){
      let filterDonors = this.donors.filter(donor => donor.id === parseInt(this.msgForm.value.donorID));
      return (filterDonors.length) !== 0;
    } else {
      return false;
    }
  }







}
