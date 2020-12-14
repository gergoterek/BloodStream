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
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;


    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/all")
    public List<Application> getAllApplications() { return applicationService.getAllApplications(); }

    @Secured({"ROLE_DONOR"})
    @PostMapping("/new")
    public ResponseEntity<Application> newApplication(@RequestBody Application application){
        return applicationService.newApplication(application);
    }

    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/{applyId}")
    public ResponseEntity<Application> getApplication(@PathVariable Integer applyId) {
        return applicationService.getApplication(applyId);
    }


    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/donor/{donorId}")
    public List<Application> getPastApplications(@PathVariable Integer donorId) { return applicationService.getPastApplications(donorId); }

    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/next/{donorID}")
    public Application getNextApplication(@PathVariable Integer donorID) { return applicationService.getNextApplication(donorID); }

    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @DeleteMapping("/delete/{applyId}")
    public ResponseEntity<Application> deleteApplication(@PathVariable Integer applyId) {
        return applicationService.deleteDonorApplication(applyId);
    }

    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @PatchMapping("/donation/{applyId}")
    public ResponseEntity<Application> setDonation(@PathVariable Integer applyId, @RequestBody Application application){
        return applicationService.setDonation(applyId, application);
    }

    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @PatchMapping("/transport/{donationId}")
    public ResponseEntity<Application> setDonationTransport(@PathVariable Integer donationId, @RequestBody Application application) {
        return applicationService.setDonationTransport(donationId, application);
    }
}
