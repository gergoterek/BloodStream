import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService, httpOptions } from '../authentication/auth.service';
import { Message } from '../domain/message';


@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private allMsgesUrl = 'http://localhost:8080/message/all';
  private donorMsgUrl = 'http://localhost:8080/message/donor';
  private msgUrl = 'http://localhost:8080/message';
  private sendMsgUrl = 'http://localhost:8080/message/send';
  private seenMsgUrl = 'http://localhost:8080/message/seen';

  constructor(
    private http: HttpClient,
    private authService: AuthService,
  ) { }

  getAllMessages(): Promise<Message[]> {
    return this.http.get<Message[]>(
      this.allMsgesUrl,
      httpOptions
    ).toPromise();
  }
  getMessage(id: number): Promise<Message> {
    return this.http.get<Message>(
      `${this.msgUrl}/${id}`,
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
      `${this.donorMsgUrl}/${id}`,
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
