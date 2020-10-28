import { Injectable } from '@angular/core';
import { Faq } from './domain/faq';

@Injectable({
  providedIn: 'root'
})
export class FaqService {

  constructor() { }

  faqs: Faq[] = [
    {
      id: 1,
      question: 'Hány percig tart?',
      answer: '30 p kb',
    },
    {
      id: 2,
      question: 'Hány emebr jön?',
      answer: '2 kb',
    }
  ];
}
