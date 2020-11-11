import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService, httpOptions } from '../authentication/auth.service';
import { Message } from '../domain/message';


@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private donorMessagesUrl = 'http://localhost:8080/message';
  private allMessagesUrl = 'http://localhost:8080/message/all';
  private sendMsgUrl = 'http://localhost:8080/message/send';

  constructor(
    private http: HttpClient,
    private authService: AuthService,
  ) { }

  getAllMessages(): Promise<Message[]> {
    return this.http.get<Message[]>(
      this.allMessagesUrl,
      httpOptions
    ).toPromise();
  }

  getDonorMessages(): Promise<Message[]> {
    return this.http.get<Message[]>(
      `${this.donorMessagesUrl}/${this.authService.user.id}`,
      httpOptions
    ).toPromise();
  }


  sendMsgToDonor(msg: Message): Promise<Message> {
    return this.http.post<Message>(
      `${this.sendMsgUrl}/${msg.donorID}`,
      msg,
      httpOptions
    ).toPromise();
  }
  
  
}
