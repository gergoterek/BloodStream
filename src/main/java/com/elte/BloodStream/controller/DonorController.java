package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    //ADMIN
    @GetMapping("/donor")
    public Iterable<Donor> getDonors() {
        return donorRepository.findAll();
    }
}
