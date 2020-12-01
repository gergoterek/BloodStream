import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { httpOptions } from '../authentication/auth.service';
import { Faq } from '../domain/faq';

@Injectable({
  providedIn: 'root'
})
export class FaqService {


  private allFaqUrl = 'http://localhost:8080/faq/all';
  private faqUrl = 'http://localhost:8080/faq';
  private delFaqUrl = 'http://localhost:8080/faq/delete';

  constructor(
    private http: HttpClient
  ) { }

  getFaqs(): Promise<Faq[]> {
    return this.http.get<Faq[]>(
      this.allFaqUrl,
      httpOptions
    ).toPromise();
  }

  getFaq(id: number): Promise<Faq> {
    return this.http.get<Faq>(
      `${this.faqUrl}/${id}`,
      httpOptions
    ).toPromise();
  }

  modifyFaq(id: number, faq: Faq): Promise<Faq> {
    return this.http.put<Faq>(
      `${this.faqUrl}/${id}`,
      faq,
      httpOptions
    ).toPromise();
  }

  addFaq(faq: Faq): Promise<Faq> {
    return this.http.post<Faq>(
      this.faqUrl,
      faq,
      httpOptions
    ).toPromise();
  }

  deleteFaq(id: number): Promise<Faq> {
    return this.http.delete<Faq>(
      `${this.delFaqUrl}/${id}`,
      httpOptions
    ).toPromise();
  }

}
