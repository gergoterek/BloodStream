package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.Application;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer> {

    List<Application> findAll();

    @Query(value = "select * from application where donation_id=?1", nativeQuery = true)
    Optional<Application> findByDonationID(Integer donationID);
}
