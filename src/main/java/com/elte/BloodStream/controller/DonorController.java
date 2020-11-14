package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donation;
import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/donor")
public class DonorController {

    @Autowired
    DonorService donorService;


    //Fv-ek leellenőrizve 10.14.

    //GUEST
    @PostMapping("login")
    public ResponseEntity<Donor> login() {
        return donorService.login();
    }


    //GUEST
    @PostMapping("/registration")
    public ResponseEntity<Donor> register(@RequestBody Donor donor) { return donorService.register(donor); }


    //ROLE_DONOR
    @GetMapping("/profile/{donorID}")
    public ResponseEntity<Donor> getDonorProfile(@PathVariable Integer donorID) {
        return donorService.getDonorProfile(donorID);
    }


    //ROLE_DONOR
    @PostMapping("/profile/change/pw")
    public ResponseEntity<Donor> changeDonorPassword(@RequestBody Donor donor) { return donorService.changeDonorPassword(donor); }


    //ADMIN (changeable: name, role, blood_type, TAJ, idCard )
    @PatchMapping("/profile/edit/{id}")
    public ResponseEntity<Donor> changeDonorDataByAdmin(@PathVariable Integer id, @RequestBody Donor donor) {
        return donorService.changeDonorDataByAdmin(id, donor);
    }


    //ADMIN
    @GetMapping("/all")
    public Iterable<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    //ADMIN
    @GetMapping("/all/orderbynameasc")
    public Iterable<Donor> getAllDonorsOrderByName() {
        return donorService.getAllDonorsOrderByNameAsc();
    }

    //ADMIN
    @GetMapping("/all/orderbyage")
    public Iterable<Donor> getAllDonorsOrderByAge() {
        return donorService.getAllDonorsOrderByAge();
    }


    //ADMIN
    @GetMapping("/all/{type}")
    public Iterable<Donor> getDonorsByBloodType(@PathVariable String type){
        return donorService.getDonorsByBloodType(type);
    }

}
