import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'src/app/domain/message';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent implements OnInit {

  id: number;
  
  constructor(
    private fb: FormBuilder,
    public messageService: MessageService,
    private router: Router,
    private route: ActivatedRoute,
    
  ) { }

  ngOnInit(): void {
      const id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.id = +id;
        this.msgForm.patchValue({
          donorID: id
          //(this.donor.bloodType === null) ? "undefined" : this.donor.bloodType,
        }); 

        this.donorID.disable();
      }
}
  
  
  msgForm = this.fb.group({
    message: ['', [Validators.required]],
    title: ['', [Validators.required]],
    donorID: ['', [Validators.required]],
    // donorID: [null],  
    // bloodType: [null],    
    // nextDonationDate: [null],    
    // role: [null],    
    // id: [null],    
  });
  

  get message() { return this.msgForm.get('message'); }
  get title() { return this.msgForm.get('title'); }
  get donorID() { return this.msgForm.get('donorID'); }

  async onFormSave() {
    this.donorID.enable();
    console.log(JSON.stringify(this.msgForm.value));
    const msg = this.msgForm.value as Message;
    await this.messageService.sendMsgToDonor(msg);
    this.router.navigate(['/message', 'del']);
  }







}
