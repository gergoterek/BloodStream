package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Integer> {
    Optional<Place> findById(Integer id);
    List<Place> findAll();

    List<Place> findAllByCity(String city);
}
