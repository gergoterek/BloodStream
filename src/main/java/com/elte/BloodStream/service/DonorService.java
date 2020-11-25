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
    public ResponseEntity<Donor> register(Donor donor) {

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

            //messageService.regMessage(newDonor);

            return ResponseEntity.ok(donorRepository.save(newDonor));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    //USER - /donor/profile/change/pw
    // (changeable: password)
    public ResponseEntity<Donor> changeDonorPassword(Donor donor){

        Optional<Donor> foundDonor = donorRepository.findById(donor.getId());
        if (foundDonor.isPresent()) {
            foundDonor.get().setPassword(passwordEncoder.encode(donor.getPassword()));
            return ResponseEntity.ok(donorRepository.save(foundDonor.get()));
        } else {
            return ResponseEntity.badRequest().build();
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

    //NURSE - donor/all/orderbynameasc
    public Iterable<Donor> getAllDonorsOrderByNameAsc() {
        return donorRepository.findAllByOrderByDonorNameAsc();
    }

    //NURSE - /donor/all/orderbyage
    public Iterable<Donor> getAllDonorsOrderByAge() {
        return donorRepository.findAllByOrderByBirthDate();
    }

    //NURSE - /donor/all/{type}
    public Iterable<Donor> getDonorsByBloodType( String type){
        return donorRepository.findAllByBloodType(Donor.BloodTypes.valueOf(type));
    }

//    //USER
//    public Donation getDonorLastDonation(Integer id){
//        //return donationRepository.findFirstByDonationIDOrderByDonationDateDesc(id);
//        return donationRepository.findFirstDonation();
//    }

}