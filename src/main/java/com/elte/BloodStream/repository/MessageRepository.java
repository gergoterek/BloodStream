package com.elte.BloodStream.repository;

import com.elte.BloodStream.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findAll();
    List<Message> findAllByDonorId(Integer id);
    Optional<Message> findByMsgId(Integer msgId);
}
