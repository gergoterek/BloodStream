package com.elte.BloodStream.service;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageService {


    @Autowired
    MessageRepository messageRepository;

    @Autowired
    DonorRepository donorRepository;


    //ADMIN - /message/create/{id}
    public ResponseEntity<Message> createMessage(Message msg, Integer id) {

        Optional<Donor> targetDonor = donorRepository.findByID(id);
        if (targetDonor.isPresent()) {
            Donor donor = targetDonor.get();

            Message newMsg = new Message();
            newMsg.setTitle(msg.getTitle());
            newMsg.setMessage(msg.getMessage());
            newMsg.setApplication(null);
            newMsg.setDonor(targetDonor.get());
            newMsg.setSeen(false);
            newMsg.setSendDate(LocalDateTime.now());

            donor.getMessages().add(msg);

            return ResponseEntity.ok(messageRepository.save(newMsg));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //USER - ADMIN - /message/{id}
    public Iterable<Message> getDonorMessages( Integer id) { return messageRepository.findAllByDonorID(id); }

    //ADMIN - /message/all
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
