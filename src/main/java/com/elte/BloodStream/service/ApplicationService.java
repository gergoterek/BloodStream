package com.elte.BloodStream.service;

import com.elte.BloodStream.model.*;
import com.elte.BloodStream.repository.ApplicationRepository;
import com.elte.BloodStream.repository.DonationPlaceRepository;
import com.elte.BloodStream.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        if (foundDonor.isPresent() && foundPlace.isPresent() && application.getAppliedTime() != null) {
            application.setDonor(donorRepository.findByID(application.getDonor().getID()).get());
            application.setPlace(placeRepository.findAllByID(application.getPlace().getID()));
            application.setAppliedTime(application.getAppliedTime());
            application.setHasAppeared(false);
            return ResponseEntity.ok(applicationRepository.save(application));
        } else {
            System.out.println(application.getDonor().getID());
            System.out.println(application.getPlace().getID());
            System.out.println("HIBA");
            return ResponseEntity.badRequest().build();
        }
    }

    //USER
    public ResponseEntity<Application> deleteApplication(Integer id) {

        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if(optionalApplication.isPresent()){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    //ADMIN
    public List<Application> getAllApplications() { return applicationRepository.findAll(); }
}
