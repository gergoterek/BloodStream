import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
import { News } from 'src/app/domain/news';
import { NewsService } from '../news.service';


@Component({
  selector: 'app-news-edit',
  templateUrl: './news-edit.component.html',
  styleUrls: ['./news-edit.component.css']
})
export class NewsEditComponent implements OnInit {

  newsId: number = null;
  news: News= new News();
  titleOfPage = 'New news';
  wantToEdit = false;

  constructor(
    private route: ActivatedRoute,
    private newsService: NewsService,
    private location: Location,
    private router: Router,
    private authService: AuthService
  ) { }

  async ngOnInit() {
    const id = this.route.snapshot.paramMap.get('newsId');
    if (id) {
      this.newsId = +id;
      this.news = await this.newsService.getNews(this.newsId);
      this.titleOfPage = 'Modify news';
      this.wantToEdit = true;
    }
  }
 
  

  async onFormSave(news: News) {
    if (this.newsId) {
      await this.newsService.modifyNews(this.newsId, news);      
      this.location.back();
    } else {
      await this.newsService.addNews(news);
      this.location.back();
    }
  }
}
