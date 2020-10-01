package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.News;
import com.elte.BloodStream.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {

        @Autowired
        private NewsRepository newsRepository;

        @GetMapping("")
        public Iterable<News> getNews() {
            return newsRepository.findAll();
        }

        //ADMIN
        @PostMapping("/create")
        public ResponseEntity<News> createNews(
                @RequestBody News news
        ) {
                news.setCreatedAt(LocalDateTime.now());
                News savedNews = newsRepository.save(news);
                return ResponseEntity.ok(savedNews);
        }

        //ADMIN
        @DeleteMapping("/delete/{id}")
        public ResponseEntity deleteNews(
                @PathVariable Integer id
        ) {
                try {
                        newsRepository.deleteById(id);
                        return ResponseEntity.ok().build();
                } catch (Exception e) {
                        return ResponseEntity.notFound().build();
                }
        }

        //ADMIN
        @PatchMapping("/modify")
        public ResponseEntity<News> modifyNews(@RequestBody News news){
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
