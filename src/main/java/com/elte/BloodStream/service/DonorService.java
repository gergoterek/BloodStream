package com.elte.BloodStream.service;

import com.elte.BloodStream.controller.DonorController;
import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class DonorService {

    @Autowired
    DonorRepository donorRepository;

//    @Autowired
//    DonorController donorController;

    //GUEST
    public ResponseEntity<Donor> register(Donor donor) {
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

    //ADMIN
    public Iterable<Donor> getDonors() {
        return donorRepository.findAll();
    }
}
