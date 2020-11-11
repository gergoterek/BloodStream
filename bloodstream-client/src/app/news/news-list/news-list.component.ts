import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/authentication/auth.service';
import { News } from '../../domain/news';
import { NewsService } from '../news.service';

@Component({
  selector: 'app-news-list',
  templateUrl: './news-list.component.html',
  styleUrls: ['./news-list.component.css']
})
export class NewsListComponent implements OnInit {

  news: News[] = [];
  // filteredDonors = [];
  // selectedBloodType = '';
   selectedNews = null;
   now = new Date();

  constructor(
    public newsService: NewsService,
    public authService: AuthService,
  ) { }


  async ngOnInit() {
    this.news = await this.newsService.getAllNews();
  }

  isPublished(date: Date): boolean{
      return new Date(date).getTime() < new Date(this.now).getTime();
  }
}
