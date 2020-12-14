package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Application;
import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.repository.MessageRepository;
import com.elte.BloodStream.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;


    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/donor/{donorId}")
    public Iterable<Message> getDonorMessages(@PathVariable Integer donorId) {
        return messageService.getDonorMessages(donorId);
    }

    @Secured({"ROLE_DONOR", "ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Integer id) {
        return messageService.getMessage(id);
    }

    @GetMapping("/all")
    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    public Iterable<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @PostMapping("send/{donorId}")
    public ResponseEntity<Message> createMessage(@RequestBody Message msg, @PathVariable Integer donorId) {
        return messageService.createMessage(msg, donorId);
    }

    @Secured({"ROLE_DONOR"})
    @PatchMapping("seen/{id}")
    public ResponseEntity<Message> setSeen(@PathVariable Integer id, @RequestBody Message msg){
        return messageService.setSeen(id, msg);
    }
}