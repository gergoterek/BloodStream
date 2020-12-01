package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.model.News;
import com.elte.BloodStream.repository.NewsRepository;
import com.elte.BloodStream.service.DonorService;
import com.elte.BloodStream.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController {

        @Autowired
        NewsService newsService;

        //Fv-ek leellenőrizve 10.14.

        //DONOR
        @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
        @GetMapping("/all")
        public Iterable<News> getAllNews() {
                return newsService.getAllNews();
        }

        //NURSE
        @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
        @GetMapping("/{newsID}")
        public ResponseEntity<News> getNews(@PathVariable Integer newsID) {
                return newsService.getNews(newsID);
        }

        //NURSE
        @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
        @PostMapping("")
        public ResponseEntity<News> createNews(
                @RequestBody News news
        ) {
                return newsService.createNews(news);
        }

        //NURSE
        @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
        @PutMapping("/{newsID}")
        public ResponseEntity<News> modifyNews (@PathVariable Integer newsID, @RequestBody News news) {
                return newsService.modifyNews(news, newsID);
        }

        //NURSE
        @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
        @DeleteMapping("/delete/{newsID}")
        public ResponseEntity deleteNews (@PathVariable Integer newsID) { return newsService.deleteNews(newsID); }



}
