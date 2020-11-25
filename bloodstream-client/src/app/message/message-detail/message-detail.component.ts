import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { AuthGuard } from 'src/app/authentication/auth.guard';
import { AuthService } from 'src/app/authentication/auth.service';
import { Message } from 'src/app/domain/message';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-message-detail',
  templateUrl: './message-detail.component.html',
  styleUrls: ['./message-detail.component.css']
})
export class MessageDetailComponent implements OnInit {

  id: number = null;
  msg: Message = new Message();

  constructor(
    public messageService: MessageService,
    private router: Router,
    private route: ActivatedRoute,
    public authService: AuthService,
    private appComp: AppComponent,
  ) { }

  async ngOnInit() {

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.id = +id;
      this.msg = await this.messageService.getMessage(this.id);
      //console.log(JSON.stringify(this.msg));
      if (this.authService.isDonor() && this.msg.donor.id !== this.authService.user.id) {
        this.authService.logout();
      }
      if (this.authService.isDonor()) {
        this.msg.seen = true;
        //console.log(JSON.stringify(this.msg));
        await this.messageService.setSeen(this.msg.msgId, this.msg);
        this.appComp.fillMsg();
      }
    }
      
  }
}
