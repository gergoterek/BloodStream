package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.News;
import com.elte.BloodStream.model.OpeningTime;
import com.elte.BloodStream.model.Place;
import com.elte.BloodStream.repository.OpeningTimeRepository;
import com.elte.BloodStream.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/place")
public class PlaceController {


    @Autowired
    PlaceService placeService;


    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/all")
    public Iterable<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @Secured({"ROLE_DONOR","ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlace(@PathVariable Integer id) {
        return placeService.getPlace(id);
    }

    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @PostMapping("")
    public ResponseEntity<Place> createPlace(
            @RequestBody Place place
    ) {
        return placeService.createPlace(place);
    }

    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<Place> modifyPlace(@PathVariable Integer id, @RequestBody Place place) {
        return placeService.modifyPlace(place, id);
    }
}


