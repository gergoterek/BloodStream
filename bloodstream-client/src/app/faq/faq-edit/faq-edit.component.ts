import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../authentication/auth.service';
import { Faq } from '../../domain/faq';
import { DonorService } from '../../donor/donor.service';
import { FaqService } from '../faq.service';
import { Location } from '@angular/common';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-faq-edit',
  templateUrl: './faq-edit.component.html',
  styleUrls: ['./faq-edit.component.css']
})
export class FaqEditComponent implements OnInit {

  faqId: number = null;
  faq: Faq = new Faq();
  title = 'New Faq';
  wantToEdit = false;

  constructor(
    private route: ActivatedRoute,
    private faqService: FaqService,
    private location: Location,
    private router: Router,
    private authService: AuthService
  ) { }

  async ngOnInit() {
    const id = this.route.snapshot.paramMap.get('faqId');
    if (id) {
      this.faqId = +id;
      this.faq = await this.faqService.getFaq(this.faqId);
      this.title = 'Modify Faq';
      this.wantToEdit = true;
    }
  }
  

  async onFormSave(faq: Faq) {
    if (this.faqId) {
      await this.faqService.modifyFaq(this.faqId, faq)
      this.location.back();
    } else {
      await this.faqService.addFaq(faq);
      this.location.back();
    }
  }

}
