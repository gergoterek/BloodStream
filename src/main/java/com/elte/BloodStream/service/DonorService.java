package com.elte.BloodStream.service;

import com.elte.BloodStream.controller.DonorController;
import com.elte.BloodStream.model.Donation;
import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.repository.DonationRepository;
import com.elte.BloodStream.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DonorService {

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    DonationRepository donationRepository;


    //GUEST - donor/registration
    public ResponseEntity<Donor> register(Donor donor) {

        Optional<Donor> foundDonor = donorRepository.findByUserName(donor.getUserName());
        if (foundDonor.isEmpty()) {
            Donor newDonor = new Donor();
            newDonor.setPassword(donor.getPassword());
            newDonor.setRole(Donor.Role.ROLE_USER);
            newDonor.setBloodType( null );
            newDonor.setUserName(donor.getUserName());
            newDonor.setDonorName(donor.getDonorName());
            newDonor.setNextDonationDate(LocalDateTime.now());
            newDonor.setIdCard(donor.getIdCard());
            newDonor.setTAJ(donor.getTAJ());
            newDonor.setBirthDate(donor.getBirthDate());

            return ResponseEntity.ok(donorRepository.save(newDonor));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //USER - /donor/profile/change/pw
    // (changeable: password)
    public ResponseEntity<Donor> changeDonorPassword(Donor donor){

        Optional<Donor> foundDonor = donorRepository.findByID(donor.getID());
        if (foundDonor.isPresent()) {
            foundDonor.get().setPassword(donor.getPassword());
            return ResponseEntity.ok(donorRepository.save(foundDonor.get()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //ADMIN - /donor/profile/change/donordata
    // (changeable: name, role, blood_type, TAJ, idCard )
    public ResponseEntity<Donor> changeDonorDataByAdmin(Donor donor){

        Optional<Donor> foundDonor = donorRepository.findByID(donor.getID());
        if (foundDonor.isPresent()) {
            foundDonor.get().setRole(donor.getRole());
            foundDonor.get().setBloodType(donor.getBloodType());
            foundDonor.get().setDonorName(donor.getDonorName());
            foundDonor.get().setTAJ(donor.getTAJ());
            foundDonor.get().setIdCard(donor.getIdCard());

            return ResponseEntity.ok(donorRepository.save(foundDonor.get()));
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    //USER - /donor/profile/{id}
    public ResponseEntity<Donor> getDonorProfile( Integer id) {
        Optional<Donor> foundDonor = donorRepository.findByID(id);
        if (foundDonor.isPresent()){
            return ResponseEntity.ok(foundDonor.get());
        } else{
            return ResponseEntity.badRequest().build();
        }
    }


    //ADMIN - /donor/all
    public Iterable<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    //ADMIN - donor/all/orderbynameasc
    public Iterable<Donor> getAllDonorsOrderByNameAsc() {
        return donorRepository.findAllByOrderByDonorNameAsc();
    }

    //ADMIN - /donor/all/orderbyage
    public Iterable<Donor> getAllDonorsOrderByAge() {
        return donorRepository.findAllByOrderByBirthDate();
    }

    //ADMIN - /donor/all/{type}
    public Iterable<Donor> getDonorsByBloodType( String type){
        return donorRepository.findAllByBloodType(Donor.BloodTypes.valueOf(type));
    }

//    //USER
//    public Donation getDonorLastDonation(Integer id){
//        //return donationRepository.findFirstByDonationIDOrderByDonationDateDesc(id);
//        return donationRepository.findFirstDonation();
//    }

}