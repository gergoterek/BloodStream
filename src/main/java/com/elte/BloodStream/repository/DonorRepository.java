package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.Donor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonorRepository extends CrudRepository<Donor, Integer> {
    Optional<Donor> findByUsername(String username);
    Optional<Donor> findById(Integer id);
    List<Donor> findAll();
    List<Donor> findAllByRole(Role role);
    List<Donor> findAllByOrderByDonorNameAsc();
    List<Donor> findAllByOrderByBirthDate();

    List<Donor> findAllByBloodType(Donor.BloodTypes type);
    List<Donor> findAllByDonorNameIgnoreCase(String name);
}
