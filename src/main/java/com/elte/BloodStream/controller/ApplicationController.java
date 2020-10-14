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

    //fv-ek leellenőrizve 10.07.

    //USER - /application/new
    @PostMapping("/new")
    public ResponseEntity<Application> newApplication(@RequestBody Application application){
        return applicationService.newApplication(application); }


    //USER - /application/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Application> deleteApplication(@PathVariable Integer id) {
        return applicationService.deleteDonorApplication(id);
    }


    //ADMIN - /application/set/appeared/{id}
    @PostMapping("/set/appeared/{id}")
    public ResponseEntity<Application> setDonorAppeared(@PathVariable Integer id){ return applicationService.setDonorAppeared(id); }


    //ADMIN /application/set/used/{id}
    @PostMapping("/set/used/{id}")
    public ResponseEntity<Donation> setDonationUsed(@PathVariable Integer id) { return applicationService.setDonationUsed(id); }


    //ADMIN - /application/all
    @GetMapping("/all")
    public List<Application> getAllApplications() { return applicationService.getAllApplications(); }

}
