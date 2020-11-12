package com.elte.BloodStream.service;

import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.News;
import com.elte.BloodStream.model.OpeningTime;
import com.elte.BloodStream.model.Place;
import com.elte.BloodStream.repository.OpeningTimeRepository;
import com.elte.BloodStream.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    OpeningTimeRepository openingTimeRepository;

    //ADMIN - /place/all
    public Iterable<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    //public  Iterable<OpeningTime> getAllOpeningTimes(){return openingTimeRepository.findAll();}


    //NURSE - /news/create
    public ResponseEntity<Place> createNews(Place place) {
        Place createdPlace = new Place();
        createdPlace.setName(place.getName());
        createdPlace.setCity(place.getCity());
        createdPlace.setAddress(place.getAddress());

        return ResponseEntity.ok(placeRepository.save(place));
    }

    //NURSE - news/delete/{id}
    public ResponseEntity deleteNews(Integer id) {
        try {
            placeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //NURSE - /news/modify
    public ResponseEntity<Place> modifyNews(Place place, Integer id){
        Optional<Place> oldPlace = placeRepository.findById(id);
        if (oldPlace.isPresent()) {
            Place createdPlace = oldPlace.get();
            createdPlace.setName(place.getName());
            createdPlace.setCity(place.getCity());
            createdPlace.setAddress(place.getAddress());
            return ResponseEntity.ok(placeRepository.save(createdPlace));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //NURSE - faq/{id}
    public ResponseEntity<Place> getPlace(Integer id) {
        Optional<Place> foundPlace = placeRepository.findById(id);
        if (foundPlace.isPresent()){
            return ResponseEntity.ok(foundPlace.get());
        } else{
            return ResponseEntity.badRequest().build();
        }
    }




}
