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


    //GUEST
    public ResponseEntity<Donor> register(Donor donor) {

        Optional<Donor> foundDonor = donorRepository.findByID(donor.getID());
        if (foundDonor.isEmpty()) {
            donor.setPassword(donor.getPassword());
            donor.setRole(Donor.Role.ROLE_USER);
            donor.setBloodType( donor.getBloodType() != null ? donor.getBloodType() : null );
            donor.setUserName(donor.getUserName());
            donor.setDonorName(donor.getDonorName());

            return ResponseEntity.ok(donorRepository.save(donor));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //USER (changeable: password)
    public ResponseEntity<Donor> changeDonorPassword(Donor donor){

        Optional<Donor> foundDonor = donorRepository.findByID(donor.getID());
        if (foundDonor.isPresent()) {
            Donor newDonorData = donorRepository.findByID(donor.getID()).get();
            newDonorData.setPassword(donor.getPassword());
            return ResponseEntity.ok(donorRepository.save(newDonorData));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //ADMIN (changeable: name, role, blood_type, TAJ, idCard )
    public ResponseEntity<Donor> changeDonorDataByAdmin(Donor donor){

        Optional<Donor> foundDonor = donorRepository.findByID(donor.getID());
        if (foundDonor.isPresent()) {
            Donor newDonorData = donorRepository.findByID(donor.getID()).get();
            newDonorData.setRole(donor.getRole());
            newDonorData.setBloodType(donor.getBloodType());
            newDonorData.setDonorName(donor.getDonorName());
            newDonorData.setTAJ(donor.getTAJ());
            newDonorData.setIdCard(donor.getIdCard());
            return ResponseEntity.ok(donorRepository.save(newDonorData));
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    //USER
    public ResponseEntity<Donor> getDonorProfile( Integer id) {

        Optional<Donor> foundDonor = donorRepository.findByID(id);
        if (foundDonor.isPresent()){
            return ResponseEntity.ok(foundDonor.get());
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    //ADMIN
    public Iterable<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    //ADMIN
    public Iterable<Donor> getDonorsByBloodType( String type){
        return donorRepository.findAllByBloodType(Donor.BloodTypes.valueOf(type));
    }
}
