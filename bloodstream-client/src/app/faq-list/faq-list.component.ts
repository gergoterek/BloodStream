import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Faq } from '../domain/faq';

import { FaqService } from '../faq.service'

@Component({
  selector: 'app-faq-list',
  templateUrl: './faq-list.component.html',
  styleUrls: ['./faq-list.component.css']
})
export class FaqListComponent implements OnInit {

  faqs: Faq[] = [];
  selectedFaq = null;
  
  constructor(
    public faqService: FaqService,
    public authService: AuthService,
  ) { }

  async ngOnInit() {
    this.faqs = await this.faqService.getFaqs();
  } 

}
