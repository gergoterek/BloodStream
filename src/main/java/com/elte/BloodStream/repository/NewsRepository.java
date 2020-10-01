package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<News, Integer> {
    List<News> findAll();
    //News findFirstByOrderByNewsidDesc();
    //List<Donors> findAllByDonorsNameIgnoreCaseOrderByNameDesc(String name);
}
