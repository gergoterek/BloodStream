package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Application;
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

    //Osztaly fv-ek leellenőrizve 10.07.

    //USER
    @PostMapping("/new")
    public ResponseEntity<Application> newApplication(@RequestBody Application application){ return applicationService.newApplication(application); }

    //USER
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Application> deleteApplication(@PathVariable Integer id) {
        return applicationService.deleteApplication(id);
    }

    //ADMIN
    @GetMapping("/all")
    public List<Application> getAllApplications() { return applicationService.getAllApplications(); }

}
