package com.elte.BloodStream.service;

import com.elte.BloodStream.controller.DonorController;
import com.elte.BloodStream.model.Donation;
import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.repository.DonationRepository;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DonorService {

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    AuthenticatedUser authenticatedUser;

    @Autowired
    MessageService messageService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //GUEST - /donor/login
    public ResponseEntity<Donor> login() {
        return ResponseEntity.ok(authenticatedUser.getDonor());
    }

    //GUEST - donor/registration
    public ResponseEntity<Boolean> register(Donor donor) {

        Optional<Donor> foundDonor = donorRepository.findByUsername(donor.getUsername());
        if (foundDonor.isEmpty()) {
            Donor newDonor = new Donor();
            newDonor.setPassword(passwordEncoder.encode(donor.getPassword()));
            newDonor.setRole(Donor.Role.ROLE_DONOR);
            newDonor.setBloodType( null );
            newDonor.setUsername(donor.getUsername());
            newDonor.setDonorName(donor.getDonorName());
            newDonor.setNextDonationDate(LocalDateTime.now());
            newDonor.setIdCard(donor.getIdCard());
            newDonor.setTAJ(donor.getTAJ());
            newDonor.setBirthDate(donor.getBirthDate());
            newDonor.setMale(donor.isMale());

            donorRepository.save(newDonor);
            messageService.registrationMessage(newDonor);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }




    //NURSE - /donor/profile/change/donordata
    // (changeable: name, role, blood_type, TAJ, idCard )
    public ResponseEntity<Donor> changeDonorDataByAdmin(Integer id, Donor donor){

        Optional<Donor> foundDonor = donorRepository.findById(id);
        if (foundDonor.isPresent()) {
            Donor modifiedDonor = foundDonor.get();
            modifiedDonor.setRole(donor.getRole());

            modifiedDonor.setBloodType(donor.getBloodType());
            modifiedDonor.setTAJ(donor.getTAJ());
            modifiedDonor.setIdCard(donor.getIdCard());

            return ResponseEntity.ok(donorRepository.save(modifiedDonor));
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    //USER - /donor/profile/{id}
    public ResponseEntity<Donor> getDonorProfile( Integer id) {
        Optional<Donor> foundDonor = donorRepository.findById(id);
        if (foundDonor.isPresent()){
            return ResponseEntity.ok(foundDonor.get());
        } else{
            return ResponseEntity.badRequest().build();
        }
    }


    //NURSE - /donor/all
    public Iterable<Donor> getAllDonors() {
        return donorRepository.findAll();
    }


}
