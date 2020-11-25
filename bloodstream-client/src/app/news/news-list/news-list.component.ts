import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
    private route: ActivatedRoute,
    private router: Router,
  ) { }


  async ngOnInit() {
    this.news = await this.newsService.getAllNews();

    let del = this.route.snapshot.url;
    if (del.length === 3){
      if(String(del).split(",")[2] === "del"){
        this.router.navigate(['/news'])
      }
    }

    this.news.sort(function(a,b) {
      //return new Date(b.publishDate).getTime() - new Date(a.publishDate).getTime();
      return new Date(a.publishDate).getTime() - new Date(b.publishDate).getTime();
    });
  }

  isPublished(date: Date): boolean{
      return new Date(date).getTime() < new Date(this.now).getTime();
  }
}
