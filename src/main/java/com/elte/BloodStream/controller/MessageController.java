package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.repository.MessageRepository;
import com.elte.BloodStream.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageService messageService;


    //USER - ADMIN
    @GetMapping("/{id}")
    public Iterable<Message> getDonorMessages(@PathVariable Integer id) {
        return messageRepository.findAllByDonorID(id);
    }

    //ADMIN
    @GetMapping("/all")
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    //ADMIN
    @PostMapping("/{id}/new")
    public ResponseEntity<Message> createMessage(@RequestBody Message msg, @PathVariable Integer id) {
        return messageService.createMessage(msg, id);
    }
}