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
import org.springframework.web.bind.annotation.PathVariable;

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

    //NURSE - faq/{id}
    public ResponseEntity<Place> getPlace(Integer id) {
        Optional<Place> foundPlace = placeRepository.findById(id);
        if (foundPlace.isPresent()){
            return ResponseEntity.ok(foundPlace.get());
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

//    //Donor
//    public Iterable<Place> getPlacesByCity(String city) {
//        return placeRepository.findAllByCity(city);
//    }

    //public  Iterable<OpeningTime> getAllOpeningTimes(){return openingTimeRepository.findAll();}


    //NURSE - /news/create
    public ResponseEntity<Place> createPlace(Place place) {
        Place createdPlace = new Place();
        createdPlace.setName(place.getName());
        createdPlace.setCity(place.getCity());
        createdPlace.setAddress(place.getAddress());
        //System.out.println(place.getOpeningTime().getClosingTime());
        createdPlace.setOpeningTime(place.getOpeningTime());
        //openingTimeRepository.save(place.getOpeningTime());

        return ResponseEntity.ok(placeRepository.save(place));
    }

//    //NURSE - news/delete/{id}
//    public ResponseEntity<Place> deletePlace(Integer id) {
//        Optional<Place> foundPlace = placeRepository.findById(id);
//        if (foundPlace.isPresent()) {
//            placeRepository.deleteById(id);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    //NURSE - /news/modify
    public ResponseEntity<Place> modifyPlace(Place place, Integer id){
        Optional<Place> oldPlace = placeRepository.findById(id);
        Optional<OpeningTime> oldOpening = openingTimeRepository.findById(id);
        if (oldPlace.isPresent() && oldOpening.isPresent()) {
            Place createdPlace = oldPlace.get();
            createdPlace.setName(place.getName());
            createdPlace.setCity(place.getCity());
            createdPlace.setAddress(place.getAddress());
            createdPlace.setActive(place.isActive());

            OpeningTime ot = oldOpening.get();
            ot.setStartTime(place.getOpeningTime().getStartTime());
            ot.setClosingTime(place.getOpeningTime().getClosingTime());
            ot.setMonday(place.getOpeningTime().isMonday());
            ot.setTuesday(place.getOpeningTime().isTuesday());
            ot.setWednesday(place.getOpeningTime().isWednesday());
            ot.setThursday(place.getOpeningTime().isThursday());
            ot.setFriday(place.getOpeningTime().isFriday());
            ot.setSaturday(place.getOpeningTime().isSaturday());
            ot.setSunday(place.getOpeningTime().isSunday());

            createdPlace.setOpeningTime(ot);
            //System.out.println(place.getOpeningTime().getClosingTime());
            //openingTimeRepository.save(place.getOpeningTime());

            return ResponseEntity.ok(placeRepository.save(createdPlace));
        } else {
            return ResponseEntity.notFound().build();
        }
    }







}
