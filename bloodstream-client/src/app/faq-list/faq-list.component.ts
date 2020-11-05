import { Component, OnInit } from '@angular/core';
import { Faq } from '../domain/faq';

import { FaqService } from '../faq.service'

@Component({
  selector: 'app-faq-list',
  templateUrl: './faq-list.component.html',
  styleUrls: ['./faq-list.component.css']
})
export class FaqListComponent implements OnInit {

  constructor(
    public faqService: FaqService
  ) { }
  faqs: Faq[] = [];
  selectedFaq = null;

  async ngOnInit() {
    this.faqs = await this.faqService.getFaqs();
  } 

}
