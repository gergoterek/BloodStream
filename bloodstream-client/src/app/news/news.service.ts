import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { httpOptions } from '../authentication/auth.service';
import { News } from '../domain/news';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  private allNewsUrl = 'http://localhost:8080/news/all';
  private newsUrl = 'http://localhost:8080/news';
  private delNewsUrl = 'http://localhost:8080/news/delete';


  constructor(
    private http: HttpClient
  ) { }

  getAllNews(): Promise<News[]> {
    return this.http.get<News[]>(
      this.allNewsUrl,
      httpOptions
    ).toPromise();
  }

  getNews(id: number): Promise<News> {
    return this.http.get<News>(
      `${this.newsUrl }/${id}`,
      httpOptions
    ).toPromise();
  }

  modifyNews(id: number, news: News): Promise<News> {
    console.log(id);
    return this.http.put<News>(
      `${this.newsUrl }/${id}`,
      news,
      httpOptions
    ).toPromise();
  }

  addNews(news: News): Promise<News> {
    return this.http.post<News>(
      this.newsUrl ,
      news,
      httpOptions
    ).toPromise();
  }

  deleteNews(id: number): Promise<News> {
    return this.http.delete<News>(
      `${this.delNewsUrl}/${id}`,
      httpOptions
    ).toPromise();
  }
}
