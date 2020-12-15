package com.elte.BloodStream.service;

import com.elte.BloodStream.model.*;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageService {


    @Autowired
    MessageRepository messageRepository;

    @Autowired
    DonorRepository donorRepository;


    //NURSE - /message/create/{msgID}
    public ResponseEntity<Message> createMessage(Message msg, Integer donorID) {
        Optional<Donor> targetDonor = donorRepository.findById(donorID);
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


    //USER - NURSE - /message/donor/{donorID}
    public Iterable<Message> getDonorMessages( Integer donorId) { return messageRepository.findAllByDonorId(donorId); }

    //USER - NURSE - /message/{msgId}
    public ResponseEntity<Message> getMessage (Integer msgId) {
        Optional<Message> optionalMessage = messageRepository.findByMsgId(msgId);
        if (optionalMessage.isPresent()) {
            return ResponseEntity.ok(optionalMessage.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //NURSE - /message/all
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }


    //NURSE - /application/set/appeared/{id}
    public ResponseEntity<Message> setSeen(Integer msgID, Message msg) {
        Optional<Message> optionalMsg = messageRepository.findByMsgId(msgID);
        if( optionalMsg.isPresent() ){
            Message modifiedMsg = optionalMsg.get();
            modifiedMsg.setSeen(msg.getSeen());

            return ResponseEntity.ok(messageRepository.save(modifiedMsg));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    public void registrationMessage(Donor donor){
        Message msg = new Message();
        msg.setTitle("Registration");
        msg.setMessage("Thank you, for registrate to our website! Apply for a donation now!");
        msg.setApplication(null);
        msg.setSendDate(LocalDateTime.now());
        msg.setDonor(donor);

        donor.setMessages(Stream.of( msg ).collect(Collectors.toList()));
        messageRepository.save(msg);
    }

    public void transportNewMessage(Application application){
        Message msg = new Message();
        msg.setTitle("Feedback about your previous donation");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        msg.setMessage("Thank you for your previous donation has been succesfully transported on ("
                + formatter.format(new Date()) + ") to the destionation hospital! Have a nice day!");
        msg.setApplication(application);
        msg.setSendDate(LocalDateTime.now());
        msg.setDonor(application.getDonor());
        application.getDonor().getMessages().add(msg);
        messageRepository.save(msg);
    }
}
