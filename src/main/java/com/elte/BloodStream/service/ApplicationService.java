package com.elte.BloodStream.service;

import com.elte.BloodStream.model.*;
import com.elte.BloodStream.repository.*;
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
    PlaceRepository placeRepository;

    @Autowired
    MessageService messageService;


    //USER - /application/new
    public ResponseEntity<Application> newApplication(Application application){

        Optional<Donor> foundDonor = donorRepository.findByID(application.getDonor().getID());
        Optional<Place> foundPlace = placeRepository.findByID(application.getPlace().getID());

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


    //NURSE - /application/set/appeared/{id}
    public ResponseEntity<Application> setDonation(Integer applicationID) {

        Optional<Application> optionalApplication = applicationRepository.findById(applicationID);
        if(optionalApplication.isPresent() && optionalApplication.get().getDonation() == null
                                    && optionalApplication.get().getDonor().getBloodType() != null){

            Donation donation = new Donation();
            donation.setDonationDate(LocalDateTime.now());
            donation.setTransportDate(null);
            optionalApplication.get().setDonation(donation);
            optionalApplication.get().getDonor().setNextDonationDate(LocalDateTime.now().plusDays(56));

            donationRepository.save(donation);
            optionalApplication.get().getDonor().getApplications().add(optionalApplication.get());
            return ResponseEntity.ok(applicationRepository.save(optionalApplication.get()));
        } else{
            return ResponseEntity.notFound().build();
        }
    }


    //NURSE /application/set/used
    public ResponseEntity<Donation> setDonationTransport(Integer donationID) {

        Optional<Donation> optionalDonation = donationRepository.findById(donationID);
        Optional<Application> optionalApplication = applicationRepository.findByDonationID(donationID);
        if(optionalDonation.isPresent() && optionalApplication.isPresent()){
            optionalDonation.get().setTransportDate(LocalDateTime.now());

            messageService.transportNewMsg(optionalApplication.get());
            return ResponseEntity.ok(donationRepository.save(optionalDonation.get()));
        } else{
            return ResponseEntity.notFound().build();
        }
    }


    //ADMIN - /application/all
    public List<Application> getAllApplications() { return applicationRepository.findAll(); }
}
