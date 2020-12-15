package com.elte.BloodStream;

import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.News;
import com.elte.BloodStream.repository.FaqRepository;
import com.elte.BloodStream.repository.NewsRepository;
import com.elte.BloodStream.service.FaqService;
import com.elte.BloodStream.service.NewsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {


        @Autowired
        private NewsService newsService;

        @MockBean
        private NewsRepository newsRepository;

        News news = null;
        News news2 = null;
        News news3 = null;

        @Before
        public void init() {
            news  = Mockito.mock(News.class);
            news2 = Mockito.mock(News.class);
            news3 = Mockito.mock(News.class);
        }

        @After
        public void cleanUp() {
            news = null;
            news2= null;
            news3= null;
        }

        @Test
        public void getAllNewsTest() {
            //given
            //when
            when(newsRepository.findAll()).thenReturn(
                    Stream.of( news, news2, news3 ).collect(Collectors.toList())
            );
            //then
            assertEquals(3, newsRepository.findAll().size());
            verify(newsRepository, times(1)).findAll();
        }

        @Test
        public void getNewsTest() {
            //given
            int newsID = 1;
            Optional<News> oNews = Optional.of(news);
            //when
            when(news.getNewsId()).thenReturn(newsID);
            when(newsRepository.findByNewsId(news.getNewsId())).thenReturn(oNews);
            //then
            assertEquals(new ResponseEntity(news, HttpStatus.OK), newsService.getNews(news.getNewsId()));
            verify(newsRepository, times(1)).findByNewsId(news.getNewsId());
        }

        @Test
        public void createNewsTest() {
            //given
            //when
            Mockito.when(newsRepository.save(any(News.class))).thenReturn(news);
            //then
            assertEquals(new ResponseEntity(news, HttpStatus.OK), newsService.createNews(news));
            verify(newsRepository, times(1)).save(any(News.class));
        }

        @Test
        public void modifyNewsTest() {
            //given
            int newsID = 1;
            Optional<News> oNews = Optional.of(news);
            //when
            when(news.getNewsId()).thenReturn(newsID);
            when(news2.getNewsId()).thenReturn(newsID);
            when(newsRepository.findByNewsId(news.getNewsId())).thenReturn(oNews);
            //then
            assertEquals(new ResponseEntity(HttpStatus.OK), newsService.modifyNews(news2, news2.getNewsId()));
            verify(newsRepository, times(1)).findByNewsId(news.getNewsId());
        }

        @Test
        public void deleteNewsTest() {
            //given
            int newsID = 1;

            //when
            when(news.getNewsId()).thenReturn(newsID);
            newsService.deleteNews(news.getNewsId());
            //then
            verify(newsRepository, times(1)).deleteById(news.getNewsId());
        }
}
