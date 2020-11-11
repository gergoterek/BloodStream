import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Message } from 'src/app/domain/message';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    public messageService: MessageService,
    private router: Router,
  ) { }

  ngOnInit(): void {
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
    const msg = this.msgForm.value as Message;
    await this.messageService.sendMsgToDonor(msg);
  }







}
