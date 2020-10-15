package com.elte.BloodStream.service;

import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.OpeningTime;
import com.elte.BloodStream.model.Place;
import com.elte.BloodStream.repository.OpeningTimeRepository;
import com.elte.BloodStream.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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


}
