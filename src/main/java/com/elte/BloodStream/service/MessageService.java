package com.elte.BloodStream.service;

import com.elte.BloodStream.model.Application;
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


    //NURSE - /message/create/{msgID}
    public ResponseEntity<Message> createMessage(Message msg, Integer msgID) {

        Optional<Donor> targetDonor = donorRepository.findByID(msgID);
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



    //USER - NURSE - /message/{donorID}
    public Iterable<Message> getDonorMessages( Integer donorID) { return messageRepository.findAllByDonorID(donorID); }

    //NURSE - /message/all
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }


    void transportNewMsg(Application application){
        Message msg = new Message();
        msg.setTitle("Visszajelzes a veradasarol");
        msg.setMessage("Köszönjük, hogy véradàsàval hozzàjàrult egy beteg gyògyulàsàhoz! Az Ön àltal adott vért a mai napon ("
                + application.getDonation().getTransportDate() + ") szàllìtottàk ki a kòrhàzba! Szép napot! OVSZ");
        msg.setSendDate(LocalDateTime.now());
        msg.setApplication(application);
        msg.setDonor(application.getDonor());
        application.getDonor().getMessages().add(msg);
        messageRepository.save(msg);
    }
}
