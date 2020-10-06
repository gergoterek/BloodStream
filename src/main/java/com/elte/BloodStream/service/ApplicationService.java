package com.elte.BloodStream.service;

import com.elte.BloodStream.model.Application;
import com.elte.BloodStream.model.Donation;
import com.elte.BloodStream.model.DonationPlace;
import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.repository.ApplicationRepository;
import com.elte.BloodStream.repository.DonationPlaceRepository;
import com.elte.BloodStream.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    DonationPlaceRepository placeRepository;


    //USER
    public ResponseEntity<Application> newApplication(Application application){
        Optional<Donor> foundDonor = donorRepository.findByID(application.getDonor().getID());
        Optional<DonationPlace> foundPlace = placeRepository.findByID(application.getPlace().getID());
        if (!foundDonor.isPresent() && !foundPlace.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        application.setDonor(donorRepository.findByID(application.getDonor().getID()).get());
        application.setPlace(placeRepository.findAllByID(application.getPlace().getID()));
        application.setAppliedAt(LocalDateTime.now());
        application.setHasAppeared(false);

        return ResponseEntity.ok(applicationRepository.save(application));
    }

    //ADMIN
    public List<Application> getAllApplications() { return applicationRepository.findAll(); }
}
