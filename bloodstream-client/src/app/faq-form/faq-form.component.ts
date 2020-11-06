import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Faq } from '../domain/faq';
import { FaqService } from '../faq.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-faq-form',
  templateUrl: './faq-form.component.html',
  styleUrls: ['./faq-form.component.css']
})
export class FaqFormComponent implements OnInit, OnChanges  {

  faqForm = this.fb.group({
    question: ['', [Validators.required]],
    answer: ['', [Validators.required]],
  });
  @Input() faq: Faq;
  @Input() title: string;
  @Input() wantToEdit: boolean;
  @Output() save = new EventEmitter<Faq>();

  get question() { return this.faqForm.get('question'); }
  get answer() { return this.faqForm.get('answer'); }

  constructor(
    private fb: FormBuilder,
    public faqService: FaqService,
    private location: Location,
    private router: Router,
  ) { }

  ngOnInit() {}

  ngOnChanges() {
    this.faqForm.patchValue(this.faq);
  }

  onSubmit() {
    this.save.emit(
      Object.assign(new Faq(), this.faqForm.value)
    );
  }

  delButton(): void {
      //console.log("deleteButton" + this.faq.faqId);
      this.faqService.deleteFaq(this.faq.faqId);
  }

}
