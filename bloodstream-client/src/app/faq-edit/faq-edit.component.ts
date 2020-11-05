import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';
import { Faq } from '../domain/faq';
import { DonorService } from '../donor.service';
import { FaqService } from '../faq.service';

@Component({
  selector: 'app-faq-edit',
  templateUrl: './faq-edit.component.html',
  styleUrls: ['./faq-edit.component.css']
})
export class FaqEditComponent implements OnInit {

  

  faqId: number;
  faq: Faq;

  constructor(
    private route: ActivatedRoute,
    private faqService: FaqService,
    public authService: AuthService,
  ) { }

  async ngOnInit() {
    const id = this.route.snapshot.paramMap.get('faqId');
    if (id) {
      this.faqId = +id;
      this.faq = await this.faqService.getFaq(this.faqId);
    }
  }

}
