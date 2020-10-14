package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donation;
import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;


@RestController
@RequestMapping("donor")
public class DonorController {

    @Autowired
    DonorService donorService;


    //Fv-ek leellenőrizve 10.14.

    //GUEST
    @PostMapping("/registration")
    public ResponseEntity<Donor> register(@RequestBody Donor donor) { return donorService.register(donor); }


    //USER
    @GetMapping("/profile/{id}")
    public ResponseEntity<Donor> getDonorProfile(@PathVariable Integer id) {
        return donorService.getDonorProfile(id);
    }


    //USER
    @PostMapping("/profile/change/pw")
    public ResponseEntity<Donor> changeDonorPassword(@RequestBody Donor donor) { return donorService.changeDonorPassword(donor); }


    //ADMIN (changeable: name, role, blood_type, TAJ, idCard )
    @PostMapping("/profile/change/donordata")
    public ResponseEntity<Donor> changeDonorDataByAdmin(@RequestBody Donor donor) { return donorService.changeDonorDataByAdmin(donor); }


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

//    //USER
//    @GetMapping("/profile/lastdonationdate/{id}")
//    public LocalDateTime getDonorLastDonation(@PathVariable Integer id){
//        return donorService.getDonorLastDonation(id).getDonationDate();
//    }
}
