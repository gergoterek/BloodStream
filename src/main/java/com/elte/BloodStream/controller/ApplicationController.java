package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Application;
import com.elte.BloodStream.model.Donation;
import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.repository.ApplicationRepository;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.service.ApplicationService;
import com.elte.BloodStream.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    //fv-ek leellenőrizve 10.14.

    //USER - /application/new
    @PostMapping("/new")
    public ResponseEntity<Application> newApplication(@RequestBody Application application){
        return applicationService.newApplication(application); }


    //USER - /application/delete/{id}
    @DeleteMapping("/delete/{applicationID}")
    public ResponseEntity<Application> deleteApplication(@PathVariable Integer applicationID) {
        return applicationService.deleteDonorApplication(applicationID);
    }


    //ADMIN - /application/set/appeared/{id}
    @PostMapping("/set/donation/{applicationID}")
    public ResponseEntity<Application> setDonation(@PathVariable Integer applicationID){ return applicationService.setDonation(applicationID); }


    //ADMIN /application/set/used/{id}
    @PostMapping("/set/used/{donationID}")
    public ResponseEntity<Donation> setDonationTransport(@PathVariable Integer donationID) { return applicationService.setDonationTransport(donationID); }


    //ADMIN - /application/all
    @GetMapping("/all")
    public List<Application> getAllApplications() { return applicationService.getAllApplications(); }

}
