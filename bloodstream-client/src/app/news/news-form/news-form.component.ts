import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { News } from 'src/app/domain/news';
import { NewsService } from '../news.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-news-form',
  templateUrl: './news-form.component.html',
  styleUrls: ['./news-form.component.css']
})
export class NewsFormComponent implements OnInit, OnChanges {


  minDate = new Date(Date.now());
  maxDate = new Date(2021, 1,1);

  constructor(
    private fb: FormBuilder,
    public newsService: NewsService,
    private router: Router,
    private location: Location,
  ) { }

  
  
  newsForm = this.fb.group({
    title: ['', [Validators.required]],
    text: ['', [Validators.required]],
    publishDate: ['', [Validators.required]],
  });
  //time: ['', [Validators.required]],
  
  @Input() news: News;
  @Input() titleOfPage: string;
  @Input() wantToEdit: boolean;
  @Output() save = new EventEmitter<News>();
  
  get title() { return this.newsForm.get('title'); }
  get text() { return this.newsForm.get('text'); }
  get publishDate() { return this.newsForm.get('publishDate'); }
  // get time() { return this.newsForm.get('time'); }
  
  // async onFormSave() {
  //   const news = this.newsForm.value as News;
  //   await this.newsService.addNews(news);
  // }

  ngOnInit(): void {
  }
  
  ngOnChanges() {
    this.newsForm.patchValue(this.news);
  }

  onSubmit() {
    this.save.emit(
      Object.assign(new News(), this.newsForm.value)
    );
    //console.log(JSON.stringify(this.newsForm.value));
  }

  delButton(): void {
      this.newsService.deleteNews(this.news.newsId);
  }


}
