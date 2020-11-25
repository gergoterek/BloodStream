package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.News;
import com.elte.BloodStream.model.OpeningTime;
import com.elte.BloodStream.model.Place;
import com.elte.BloodStream.repository.OpeningTimeRepository;
import com.elte.BloodStream.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/place")
public class PlaceController {


    @Autowired
    PlaceService placeService;


    //Donor - /place/all
    @GetMapping("/all")
    public Iterable<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }


    //NURSE
    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlace(@PathVariable Integer id) {
        return placeService.getPlace(id);
    }

//    //Donor
//    @GetMapping("/city/{city}")
//    public Iterable<Place> getPlacesByCity(@PathVariable String city) {
//        return placeService.getPlacesByCity(city);
//    }

    //NURSE
    @PostMapping("")
    public ResponseEntity<Place> createPlace(
            @RequestBody Place place
    ) {
        return placeService.createPlace(place);
    }

//    //NURSE
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Place> deletePlace (@PathVariable Integer id) { return placeService.deletePlace(id); }


    //NURSE
    @PutMapping("/{id}")
    public ResponseEntity<Place> modifyPlace (@PathVariable Integer id, @RequestBody Place place) {
        return placeService.modifyPlace(place, id);
    }




//    @GetMapping("/ot")
//    public Iterable<OpeningTime> getAllOpeningTimes(){return placeService.getAllOpeningTimes();}
}
