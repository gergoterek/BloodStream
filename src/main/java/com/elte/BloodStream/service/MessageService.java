package com.elte.BloodStream.service;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {


    @Autowired
    MessageRepository messageRepository;

    @Autowired
    DonorRepository donorRepository;

    public ResponseEntity<Message> createMessage(Message msg, Integer id) {
        Optional<Donor> targetDonor = donorRepository.findByID(id);
        if (targetDonor.isPresent()) {
            Donor donor = targetDonor.get();
            donor.getMessages().add(msg);
            msg.setDonor(donorRepository.findByID(id).get());
            msg.setMessage(msg.getMessage());
            msg.setTitle(msg.getTitle());
            Message createdMessage = messageRepository.save(msg);
            return ResponseEntity.ok(createdMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
