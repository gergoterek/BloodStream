package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("")
public class DonorController {

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    DonorService donorService;

    //GUEST
    @PostMapping("/registration")
    public ResponseEntity<Donor> register(@RequestBody Donor donor) { return donorService.register(donor); }

    //USER
    @GetMapping("/profile/{id}")
    public Optional<Donor> getDonorProfile(@RequestBody Integer id) { return donorService.getProfile(id); }

    //USER
    @PostMapping("/profile/changepw")
    public ResponseEntity<Donor> changeDonorPassword(@RequestBody Donor donor) { return donorService.changeDonorPassword(donor); }

    //ADMIN
    @PostMapping("/profile/change/")
    public ResponseEntity<Donor> changeDonorDataByAdmin(@RequestBody Donor donor) { return donorService.changeDonorDataByAdmin(donor); }

    //ADMIN
    @GetMapping("/donors")
    public Iterable<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    //ADMIN
    @GetMapping("/donors/{type}")
    public Iterable<Donor> getDonorsByBloodType(@PathVariable String type) {
        return donorRepository.findAllByBloodType(type);
    }
}
