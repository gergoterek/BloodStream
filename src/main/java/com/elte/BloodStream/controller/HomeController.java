package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donations;
import com.elte.BloodStream.model.News;
import com.elte.BloodStream.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class HomeController {

//    @Autowired
//    private NewsRepository newsRepository;

//    @RequestMapping("/")
//    public String index(){
//        return getNews();
//    }
//    private String getNews(){
//        List<News> news = newsRepository.findAll();
//        return news.get(0).getMessage().toString();
//    }

//    @GetMapping("")
//    public Iterable<News> getNews() {
//        return newsRepository.findAll();
//    }

}
