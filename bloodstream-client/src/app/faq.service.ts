import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { httpOptions } from './auth.service';
import { Faq } from './domain/faq';

@Injectable({
  providedIn: 'root'
})
export class FaqService {


  private faqAllUrl = 'http://localhost:8080/faq/all';
  private faqUrl = 'http://localhost:8080/faq';

  constructor(
    private http: HttpClient
  ) { }

  getFaqs(): Promise<Faq[]> {
    return this.http.get<Faq[]>(
      this.faqAllUrl,
      httpOptions
    ).toPromise();
  }

  getFaq(id: number): Promise<Faq> {
    return this.http.get<Faq>(
      `${this.faqUrl}/${id}`,
      httpOptions
    ).toPromise();
  }

}
