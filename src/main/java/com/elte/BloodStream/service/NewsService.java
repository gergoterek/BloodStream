package com.elte.BloodStream.service;

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

    public Iterable<News> getNews() {
        return newsRepository.findAll();
    }

    //ADMIN
    public ResponseEntity<News> createNews(News news) {
        news.setPublishDate(LocalDateTime.now());
        News savedNews = newsRepository.save(news);
        return ResponseEntity.ok(savedNews);
    }

    //ADMIN
    public ResponseEntity deleteNews(Integer id) {
        try {
            newsRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //ADMIN
    public ResponseEntity<News> modifyNews(News news){
        Optional<News> oldNews = newsRepository.findById(news.getNewsId());
        if (oldNews.isPresent()) {
            News createdNews = oldNews.get();
            createdNews.setTitle(news.getTitle());
            createdNews.setMessage(news.getMessage());
            newsRepository.save(createdNews);
            return ResponseEntity.ok(createdNews);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
