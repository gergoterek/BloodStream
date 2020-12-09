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

    //fv-ek leellenőrizve 10.14.

    //ADMIN - /application/all
    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/all")
    public List<Application> getAllApplications() { return applicationService.getAllApplications(); }

    //ROLE_DONOR - /application
    @Secured({"ROLE_DONOR"})
    @PostMapping("/new")
    public ResponseEntity<Application> newApplication(@RequestBody Application application){
        return applicationService.newApplication(application);
    }

    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/{applyID}")
    public ResponseEntity<Application> getApplication(@PathVariable Integer applyID) {
        return applicationService.getApplication(applyID);
    }


    //DONOR - /application/{donorID}
    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/donor/{donorID}")
    public List<Application> getDonorPastApplications(@PathVariable Integer donorID) { return applicationService.getDonorPastApplications(donorID); }

    //DONOR - /application/{donorID}
    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/next/{donorID}")
    public Application getNextApplication(@PathVariable Integer donorID) { return applicationService.getNextApplication(donorID); }

    //ROLE_DONOR - /application/delete/{id}
    @DeleteMapping("/delete/{applicationID}")
    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    public ResponseEntity<Application> deleteApplication(@PathVariable Integer applicationID) {
        return applicationService.deleteDonorApplication(applicationID);
    }

    //ADMIN - /application/set/appeared/{id}
    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @PatchMapping("/donation/{applicationID}")
    public ResponseEntity<Application> setDonation(@PathVariable Integer applicationID, @RequestBody Application application){
        return applicationService.setDonation(applicationID, application);
    }


    //ADMIN /application/set/used/{id}
    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @PatchMapping("/transport/{donationID}")
    public ResponseEntity<Application> setDonationTransport(@PathVariable Integer donationID, @RequestBody Application application) {
        return applicationService.setDonationTransport(donationID, application);
    }

//    @GetMapping("/date/{date}/{id}")
//    public Boolean isFullDate(@PathVariable Date date, @PathVariable Integer id) { return applicationService.isFullDate(date, id); }

}
