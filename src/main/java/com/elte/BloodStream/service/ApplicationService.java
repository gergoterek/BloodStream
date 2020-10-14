package com.elte.BloodStream.service;

import com.elte.BloodStream.model.*;
import com.elte.BloodStream.repository.ApplicationRepository;
import com.elte.BloodStream.repository.DonationPlaceRepository;
import com.elte.BloodStream.repository.DonationRepository;
import com.elte.BloodStream.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    DonationRepository donationRepository;

    @Autowired
    DonationPlaceRepository placeRepository;


    //USER - /application/new
    public ResponseEntity<Application> newApplication(Application application){

        Optional<Donor> foundDonor = donorRepository.findByID(application.getDonor().getID());
        Optional<DonationPlace> foundPlace = placeRepository.findByID(application.getPlace().getID());

        if (foundDonor.isPresent() && foundPlace.isPresent() && application.getAppliedDate() != null
                            && application.getDonor().getNextDonationDate().compareTo(LocalDateTime.now())<0) {
            application.setDonor(donorRepository.findByID(application.getDonor().getID()).get());
            application.setPlace(placeRepository.findAllByID(application.getPlace().getID()));
            application.setAppliedDate(application.getAppliedDate());
            application.setDonation(null);
            return ResponseEntity.ok(applicationRepository.save(application));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //USER - /application/delete/{id}
    public ResponseEntity<Application> deleteDonorApplication(Integer applicationID) {

        Optional<Application> optionalApplication = applicationRepository.findById(applicationID);
        System.out.println(applicationID);
        System.out.println(optionalApplication.get().getDonation() == null);
        if(optionalApplication.isPresent() && optionalApplication.get().getDonation() == null){
            optionalApplication.get().getDonor().setNextDonationDate(LocalDateTime.now());
            applicationRepository.deleteById(applicationID);
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }


    //ADMIN - /application/set/appeared/{id}
    public ResponseEntity<Application> setDonorAppeared(Integer applicationID) {

        Optional<Application> optionalApplication = applicationRepository.findById(applicationID);
        if(optionalApplication.isPresent() && optionalApplication.get().getDonation() == null
                                    && optionalApplication.get().getDonor().getBloodType() != null){
            Application appearedApplication = optionalApplication.get();
            Donation tempDon = new Donation();
            tempDon.setDonationDate(LocalDateTime.now());
            tempDon.setUsed(false);
            appearedApplication.setDonation(tempDon);
            appearedApplication.getDonor().setNextDonationDate(LocalDateTime.now().plusDays(56));

            return ResponseEntity.ok(applicationRepository.save(appearedApplication));
        } else{
            return ResponseEntity.notFound().build();
        }
    }


    //ADMIN /application/set/used
    public ResponseEntity<Donation> setDonationUsed(Integer donationID) {

        Optional<Donation> optionalDonation = donationRepository.findById(donationID);
        if(optionalDonation.isPresent()){
            optionalDonation.get().setUsed(true);

            return ResponseEntity.ok(donationRepository.save(optionalDonation.get()));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    //ADMIN - /application/all
    public List<Application> getAllApplications() { return applicationRepository.findAll(); }
}
