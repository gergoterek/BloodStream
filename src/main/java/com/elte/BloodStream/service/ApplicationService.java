package com.elte.BloodStream.service;

import com.elte.BloodStream.model.*;
import com.elte.BloodStream.repository.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Date;
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


    //ADMIN - /application/all
    public List<Application> getAllApplications() { return applicationRepository.findAll(); }

    //DONOR - /application/{applyID}
    public Application getApplication(Integer id) { return applicationRepository.findByApplyId(id).get(); }

    //ADMIN - /application/{donorID}
    public List<Application> getDonorPastApplications(Integer id) {
        return applicationRepository.findAllByDonorIdAndDonationIsNotNull(id);
    }

    //ADMIN - /application/next/{donorID}
    public Application getNextApplication(Integer id) {
        return applicationRepository.findByDonorIdAndDonationIsNull(id);
    }

    //USER - /application
    public ResponseEntity<Application> newApplication(Application application){

        Optional<Donor> foundDonor = donorRepository.findById(application.getDonor().getId());
        Optional<Place> foundPlace = placeRepository.findById(application.getPlace().getId());

        if (
                foundDonor.isPresent()
                && foundPlace.isPresent()
                && application.getAppliedDate() != null
                && application.getDonor().getNextDonationDate().compareTo(LocalDateTime.now())<0)
        {
            Application newApp = new Application();
            newApp.setDonor(donorRepository.findById(application.getDonor().getId()).get());
            newApp.setPlace(placeRepository.findById(application.getPlace().getId()).get());
            System.out.println(application.getAppliedDate());
            newApp.setAppliedDate(application.getAppliedDate());
            newApp.setDonation(null);
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
    public ResponseEntity<Application> setDonation(Integer applicationID, Application application) {

        Optional<Application> optionalApplication = applicationRepository.findByApplyId(applicationID);
        if(
                optionalApplication.isPresent()
                && optionalApplication.get().getDonation() == null
                && optionalApplication.get().getDonor().getBloodType() != null
        ){
            Application modifiedApplication = optionalApplication.get();
            Donation newDonation = new Donation();
            newDonation.setDonationDate(LocalDateTime.now());
            newDonation.setTransportDate(null);
            modifiedApplication.setDonation(newDonation);
            modifiedApplication.getDonor().setNextDonationDate(LocalDateTime.now().plusDays(56));

            donationRepository.save(newDonation);
            //optionalApplication.get().getDonor().getApplications().add(optionalApplication.get());
            return ResponseEntity.ok(applicationRepository.save(modifiedApplication));
        } else{
            return ResponseEntity.notFound().build();
        }
    }
    //NURSE /application/set/used
    public ResponseEntity<Application> setDonationTransport(Integer donationID, Application application) {

        Optional<Application> optionalApplication = applicationRepository.findByApplyId(application.getApplyId());
        Optional<Donation> optionalDonation = donationRepository.findById(donationID);
        if(optionalDonation.isPresent() && optionalApplication.isPresent()){
            Application modifiedApplication = optionalApplication.get();
            Donation modifiedDonation = optionalDonation.get();
            modifiedDonation.setTransportDate(LocalDateTime.now());
            modifiedApplication.setDonation(modifiedDonation);
            donationRepository.save(modifiedDonation);

            messageService.transportNewMsg(modifiedApplication);
            return ResponseEntity.ok(applicationRepository.save(modifiedApplication));
        } else{
            return ResponseEntity.notFound().build();
        }
    }


}
