package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.OpeningTime;
import com.elte.BloodStream.model.Place;
import com.elte.BloodStream.repository.OpeningTimeRepository;
import com.elte.BloodStream.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/place")
public class PlaceController {


    @Autowired
    PlaceService placeService;


    //ADMIN - /place/all
    @GetMapping("/all")
    public Iterable<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }

//    @GetMapping("/ot")
//    public Iterable<OpeningTime> getAllOpeningTimes(){return placeService.getAllOpeningTimes();}
}
