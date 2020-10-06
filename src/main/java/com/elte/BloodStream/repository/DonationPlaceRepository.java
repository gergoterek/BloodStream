package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.DonationPlace;
import com.elte.BloodStream.model.Donor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationPlaceRepository extends CrudRepository<DonationPlace, Integer> {
    Optional<DonationPlace> findByID(Integer id);
    DonationPlace findAllByID(Integer id);
    List<DonationPlace> findAll();
}
