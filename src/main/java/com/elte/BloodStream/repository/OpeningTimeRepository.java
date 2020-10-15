package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.OpeningTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningTimeRepository extends CrudRepository<OpeningTime, Integer> {
    Iterable<OpeningTime> findAll();
}
