import { Component } from '@angular/core';
import { AuthService } from './authentication/auth.service';
import { Router } from '@angular/router';
import { MessageService } from './message/message.service';
import { Message } from './domain/message';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'bloodstream-client';
  messageList: Message [] = [];
  newMsg: Message [] = [];

  constructor (
    public authService: AuthService,
    private router: Router,
    private msgService: MessageService,
  ) {}

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  async fillMsg(){
    if (this.authService.isLoggedIn && this.authService.isDonor()){
      this.messageList = await this.msgService.getDonorMessages(this.authService.user.id);
      this.newMsg = this.messageList.filter( msg => msg.seen === false );
      //console.log(JSON.stringify(this.newMsg));
    }  
  }
}
