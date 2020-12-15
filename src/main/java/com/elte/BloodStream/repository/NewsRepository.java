package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends CrudRepository<News, Integer> {
    List<News> findAll();
    Optional<News> findByNewsId(Integer id);
}
