package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("")
public class DonorController {

    @Autowired
    DonorService donorService;


    //Osztaly fv-ek leellenőrizve 10.06.

    //GUEST
    @PostMapping("/registration")
    public ResponseEntity<Donor> register(@RequestBody Donor donor) { return donorService.register(donor); }

    //USER
    @GetMapping("/profile/{id}")
    public Donor getDonorProfile(@PathVariable Integer id) {
        return donorService.getDonorProfile(id);
    }


    //USER
    @PostMapping("/profile/change/pw")
    public ResponseEntity<Donor> changeDonorPassword(@RequestBody Donor donor) { return donorService.changeDonorPassword(donor); }

    //ADMIN (changeable: name, role, blood_type, TAJ, idCard )
    @PostMapping("/profile/change/donordata")
    public ResponseEntity<Donor> changeDonorDataByAdmin(@RequestBody Donor donor) { return donorService.changeDonorDataByAdmin(donor); }

    //ADMIN
    @GetMapping("/donors")
    public Iterable<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    //ADMIN
    @GetMapping("/donors/{type}")
    public Iterable<Donor> getDonorsByBloodType(@PathVariable String type){
        return donorService.getDonorsByBloodType(type);
    }
}
