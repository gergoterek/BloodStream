package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findAll();
    List<Message> findAllByDonorId(Integer id);
}
