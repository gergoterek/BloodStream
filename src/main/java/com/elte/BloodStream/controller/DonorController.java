package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("")
public class DonorController {

    @Autowired
    DonorRepository donorRepository;

    @GetMapping("/donor")
    public Iterable<Donor> getDonors() {
        return donorRepository.findAll();
    }


    //GUEST
    @PostMapping("/registration")
    public ResponseEntity<Donor> register(@RequestBody Donor donor) {
        Optional<Donor> oUser = donorRepository.findByUserName(donor.getUserName());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        donor.setPassword(donor.getPassword());
        donor.setRole(Donor.Role.ROLE_USER);
        donor.setBloodType(donor.getBloodType());
        donor.setUserName(donor.getUserName());
        donor.setDonorName(donor.getDonorName());

        return ResponseEntity.ok(donorRepository.save(donor));
    }
}
