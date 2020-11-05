package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.Faq;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaqRepository extends CrudRepository<Faq, Integer> {
    List<Faq> findAll();
    Optional<Faq> findByFaqId(Integer id);
}
