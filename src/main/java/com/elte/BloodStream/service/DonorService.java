package com.elte.BloodStream.service;

import com.elte.BloodStream.controller.DonorController;
import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        Optional<Donor> foundDonor = donorRepository.findByUserName(donor.getUserName());
        if (foundDonor.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        donor.setPassword(donor.getPassword());
        donor.setRole(Donor.Role.ROLE_USER);
        donor.setBloodType( donor.getBloodType() != null ? donor.getBloodType() : null );
        donor.setUserName(donor.getUserName());
        donor.setDonorName(donor.getDonorName());

        return ResponseEntity.ok(donorRepository.save(donor));
    }

    //USER (changeable: password)
    public ResponseEntity<Donor> changeDonorPassword(Donor donor){
        Optional<Donor> foundDonor = donorRepository.findByUserName(donor.getUserName());
        if (!foundDonor.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Donor newDonorData = donorRepository.findAllByID(donor.getID());
        newDonorData.setPassword(donor.getPassword());
        return ResponseEntity.ok(donorRepository.save(newDonorData));
    }

    //ADMIN (changeable: name, role, blood_type)
    public ResponseEntity<Donor> changeDonorDataByAdmin(Donor donor){
        Optional<Donor> foundDonor = donorRepository.findByUserName(donor.getUserName());
        if (!foundDonor.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Donor newDonorData = donorRepository.findAllByID(donor.getID());
        newDonorData.setRole(donor.getRole());
        newDonorData.setBloodType(donor.getBloodType());
        newDonorData.setDonorName(donor.getDonorName());
        return ResponseEntity.ok(donorRepository.save(newDonorData));
    }

    //USER
    public Optional<Donor> getProfile(@PathVariable Integer id) {
        return donorRepository.findByID(id);
    }

    //ADMIN
    public Iterable<Donor> getDonors() {
        return donorRepository.findAll();
    }
}
