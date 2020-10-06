package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer> {

    List<Application> findAll();
}
