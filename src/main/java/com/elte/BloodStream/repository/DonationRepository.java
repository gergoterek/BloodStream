package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.Donation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends CrudRepository<Donation, Integer> {
//    @Query("SELECT donation_date FROM donation LIMIT 1")
//    Donation findFirstDonation();
    //Donation findFirstByDonorIDOrderByDonationDateDesc(Integer id);
    Donation findByDonationId(Integer id);
    List<Donation> findAll();
}
