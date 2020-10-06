package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.Donor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonorRepository extends CrudRepository<Donor, Integer> {
    Optional<Donor> findByUserName(String username);
    Optional<Donor> findByID(Integer id);
    //Donor findByID(Integer id);
    List<Donor> findAll();
    List<Donor> findAllByBloodType(Donor.BloodTypes type);

    //List<Donors> findAllByFamilyId(Integer id);
    //List<Donors> findAllByBloodType(String type);
}
