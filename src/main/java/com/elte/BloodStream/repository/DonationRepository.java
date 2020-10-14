package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.Donation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends CrudRepository<Donation, Integer> {
    @Query(value = "SELECT donation_date FROM donation LIMIT 1", nativeQuery = true)
    Donation findFirstDonation();
    //Donation findFirstByDonationIDOrderByDonationDateDesc(Integer id);
    //Donation findByDonationId(Integer id);
    List<Donation> findAll();
}
