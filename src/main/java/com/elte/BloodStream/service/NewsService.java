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

    //USER - ADMIN
    public Iterable<News> getAllNews() {
        return newsRepository.findAll();
    }

    //ADMIN
    public ResponseEntity<News> createNews(News news) {
        news.setPublishDate(news.getPublishDate());
        return ResponseEntity.ok(newsRepository.save(news));
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
