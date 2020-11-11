package com.elte.BloodStream.service;

import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.News;
import com.elte.BloodStream.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;


    //USER - ADMIN - /news/all
    public Iterable<News> getAllNews() {
        return newsRepository.findAll();
    }

    //NURSE - /news/create
    public ResponseEntity<News> createNews(News news) {
        News createNews = new News();
        createNews.setTitle(news.getTitle());
        createNews.setMessage(news.getMessage());
        createNews.setPublishDate(news.getPublishDate());

        return ResponseEntity.ok(newsRepository.save(news));
    }

    //NURSE - news/delete/{id}
    public ResponseEntity deleteNews(Integer newsID) {
        try {
            newsRepository.deleteById(newsID);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //NURSE - /news/modify
    public ResponseEntity<News> modifyNews(News news, Integer newsID){

        Optional<News> oldNews = newsRepository.findById(newsID);
        if (oldNews.isPresent()) {
            News createdNews = oldNews.get();
            createdNews.setTitle(news.getTitle());
            createdNews.setMessage(news.getMessage());
            createdNews.setPublishDate(news.getPublishDate());

            return ResponseEntity.ok(newsRepository.save(createdNews));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //NURSE - faq/{id}
    public ResponseEntity<News> getNews(Integer id) {
        Optional<News> foundNews = newsRepository.findByNewsId(id);
        if (foundNews.isPresent()){
            return ResponseEntity.ok(foundNews.get());
        } else{
            return ResponseEntity.badRequest().build();
        }
    }
}
