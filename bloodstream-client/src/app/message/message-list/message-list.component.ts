
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/authentication/auth.service';
import { Message } from 'src/app/domain/message';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css']
})
export class MessageListComponent implements OnInit {

  messages: Message[] = [];
  // filteredDonors = [];
  // selectedBloodType = '';
   selectedMessage = null;
   isDonor: boolean = true;

  constructor(
    public messageService: MessageService,
    private authService: AuthService,
  ) { }


  async ngOnInit() {
    if (this.authService.isAdmin() || this.authService.isNurse()){
      this.isDonor = false;
        this.messages = await this.messageService.getAllMessages();
    } else {    
        this.messages = await this.messageService.getDonorMessages();
    }
  }
}
