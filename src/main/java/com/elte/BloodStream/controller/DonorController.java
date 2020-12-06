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

    //GUEST
    @PostMapping("login")
    public ResponseEntity<Donor> login() {
        return donorService.login();
    }


    //GUEST
    @PostMapping("/registration")
    public ResponseEntity<Boolean> register(@RequestBody Donor donor) { return donorService.register(donor); }


    //ROLE_DONOR
    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/profile/{donorID}")
    public ResponseEntity<Donor> getDonorProfile(@PathVariable Integer donorID) {
        return donorService.getDonorProfile(donorID);
    }

    //ADMIN (changeable: role, blood_type, TAJ, idCard )
    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @PatchMapping("/profile/edit/{id}")
    public ResponseEntity<Donor> changeDonorDataByAdmin(@PathVariable Integer id, @RequestBody Donor donor) {
        return donorService.changeDonorDataByAdmin(id, donor);
    }

    //ADMIN
    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/all")
    public Iterable<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }



}

//    //ADMIN
//    @GetMapping("/all/{type}")
//    public Iterable<Donor> getDonorsByBloodType(@PathVariable String type){
//        return donorService.getDonorsByBloodType(type);
//    }

//    //ROLE_DONOR
//    @PostMapping("/profile/change/pw")
//    public ResponseEntity<Donor> changeDonorPassword(@RequestBody Donor donor) { return donorService.changeDonorPassword(donor); }

//    //ADMIN
//    @GetMapping("/all/admin")
//    public Iterable<Donor> getAllAdmins() {
//        return donorService.getAllAdmins();
//    }

//    //ADMIN
//    @GetMapping("/all/orderbynameasc")
//    public Iterable<Donor> getAllDonorsOrderByName() {
//        return donorService.getAllDonorsOrderByNameAsc();
//    }
//
//ADMIN
//@GetMapping("/all/orderbyage")
//public Iterable<Donor> getAllDonorsOrderByAge() {
//    return donorService.getAllDonorsOrderByAge();
//}

