package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donation;
import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/donor")
public class DonorController {

    @Autowired
    DonorService donorService;


    @PostMapping("login")
    public ResponseEntity<Donor> login() {
        return donorService.login();
    }

    @PostMapping("/registration")
    public ResponseEntity<Boolean> register(@RequestBody Donor donor) {
        return donorService.register(donor);
    }

    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/profile/{donorId}")
    public ResponseEntity<Donor> getDonorProfile(@PathVariable Integer donorId) {
        return donorService.getDonorProfile(donorId);
    }

    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @PatchMapping("/profile/edit/{donorId}")
    public ResponseEntity<Donor> changeDonorDataByAdmin(@PathVariable Integer donorId, @RequestBody Donor donor) {
        return donorService.changeDonorDataByAdmin(donorId, donor);
    }

    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/all")
    public Iterable<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }
}

