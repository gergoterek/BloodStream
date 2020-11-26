import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService, httpOptions } from '../authentication/auth.service';
import { Message } from '../domain/message';


@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private donorMessagesUrl = 'http://localhost:8080/message/donor';
  private messagesUrl = 'http://localhost:8080/message';
  private allMessagesUrl = 'http://localhost:8080/message/all';
  private sendMsgUrl = 'http://localhost:8080/message/send';
  private seenMsgUrl = 'http://localhost:8080/message/seen';

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
  getMessage(id: number): Promise<Message> {
    return this.http.get<Message>(
      `${this.messagesUrl}/${id}`,
      httpOptions
    ).toPromise();
  }

  setSeen(id: number, msg: Message): Promise<Message> {
    return this.http.patch<Message>(
      `${this.seenMsgUrl}/${id}`,
      msg,
      httpOptions
    ).toPromise();
  }

  getDonorMessages(id: number): Promise<Message[]> {
    return this.http.get<Message[]>(
      `${this.donorMessagesUrl}/${id}`,
      httpOptions
    ).toPromise();
  }


  sendMsgToDonor(msg: Message, donorID: number): Promise<Message> {
    return this.http.post<Message>(
      `${this.sendMsgUrl}/${donorID}`,
      msg,
      httpOptions
    ).toPromise();
  }
  
  
}
