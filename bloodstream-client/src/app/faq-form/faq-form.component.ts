import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Faq } from '../domain/faq';

@Component({
  selector: 'app-faq-form',
  templateUrl: './faq-form.component.html',
  styleUrls: ['./faq-form.component.css']
})
export class FaqFormComponent implements OnInit  {

  faqForm = this.fb.group({
    title: ['', [Validators.required]],
    place: ['', [Validators.required, Validators.pattern(/^PC\d+/)]],
    description: [''],
    status: ['', [Validators.required]],
  });
  @Input() faq: Faq;
  @Input() showStatus = false;
  @Output() save = new EventEmitter<Faq>();

  get title() { return this.faqForm.get('title'); }
  get place() { return this.faqForm.get('place'); }
  get description() { return this.faqForm.get('description'); }
  get status() { return this.faqForm.get('status'); }

  constructor(
    private fb: FormBuilder
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
}
