package com.elte.BloodStream.service;

import com.elte.BloodStream.model.Application;
import com.elte.BloodStream.model.Donation;
import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class MessageService {


    @Autowired
    MessageRepository messageRepository;

    @Autowired
    DonorRepository donorRepository;


    //NURSE - /message/create/{msgID}
    public ResponseEntity<Message> createMessage(Message msg, Integer msgID) {

        Optional<Donor> targetDonor = donorRepository.findById(msgID);
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
    public Message getMessage (Integer msgId) { return messageRepository.findByMsgId(msgId).get(); }


    //NURSE - /message/all
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }


    //NURSE - /application/set/appeared/{id}
    public ResponseEntity<Message> setSeen(Integer msgID, Message msg) {
        Optional<Message> optionalMsg = messageRepository.findByMsgId(msgID);
        //System.out.println("MOOOOOOOOOOST" + donorID + msg.getDonor().getId()+ optionalMsg.isPresent());
        if(
                optionalMsg.isPresent()
        ){
            Message modifiedMsg = optionalMsg.get();
            modifiedMsg.setSeen(msg.getSeen());

            return ResponseEntity.ok(messageRepository.save(modifiedMsg));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    void transportNewMsg(Application application){
        Message msg = new Message();
        msg.setTitle("Visszajelzes a veradasarol");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        msg.setMessage("Köszönjük, hogy véradàsàval hozzàjàrult egy beteg gyògyulàsàhoz! Az Ön àltal adott vért a mai napon ("
                + formatter.format(new Date()) + ") szàllìtottàk ki a kòrhàzba! Szép napot! OVSZ");
        msg.setSendDate(LocalDateTime.now());
        msg.setApplication(application);
        msg.setDonor(application.getDonor());
        application.getDonor().getMessages().add(msg);
        messageRepository.save(msg);
    }
}
