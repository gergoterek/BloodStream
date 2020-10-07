package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.model.News;
import com.elte.BloodStream.repository.NewsRepository;
import com.elte.BloodStream.service.DonorService;
import com.elte.BloodStream.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {

        @Autowired
        NewsService newsService;


        //USER - ADMIN
        @GetMapping("")
        public Iterable<News> getAllNews() {
                return newsService.getAllNews();
        }

        //ADMIN
        @PostMapping("/create")
        public ResponseEntity<News> createNews(
                @RequestBody News news
        ) {
                return newsService.createNews(news);
        }

        //ADMIN
        @DeleteMapping("/delete/{id}")
        public ResponseEntity deleteNews(
                @PathVariable Integer id
        ) {
                return newsService.deleteNews(id);
        }


        //ADMIN
        @PatchMapping("/modify")
        public ResponseEntity<News> modifyNews(
                @RequestBody News news
        ){
                return newsService.modifyNews(news);
        }
}
