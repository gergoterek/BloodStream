package com.elte.BloodStream.service;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class FaqService {

    @Autowired
    FaqRepository faqRepository;

    //USER - /faq/all
    public Iterable<Faq> getAllFaq() {
        return faqRepository.findAll();
    }

    //NURSE - /faq/create
    public ResponseEntity<Faq> createFaq(Faq faq) {
        Faq newFaq = new Faq();
        newFaq.setQuestion(faq.getQuestion());
        newFaq.setAnswer(faq.getAnswer());
        return ResponseEntity.ok(faqRepository.save(newFaq));
    }

    //NURSE - faq/delete/{id}
    public ResponseEntity<Faq> deleteFaq(Integer id) {
        try {
            faqRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //NURSE - faq/{id}
    public ResponseEntity<Faq> getFaq(Integer id) {
        Optional<Faq> foundFaq = faqRepository.findByFaqId(id);
        if (foundFaq.isPresent()){
            return ResponseEntity.ok(foundFaq.get());
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    //NURSE - /faq/modify/{id}
    public ResponseEntity<Faq> modifyFaq(Faq faq, Integer faqID) {

        Optional<Faq> oldFaq = faqRepository.findById(faqID);

        if (oldFaq.isPresent()) {
            Faq createdFaq = oldFaq.get();
            createdFaq.setQuestion(faq.getQuestion());
            createdFaq.setAnswer(faq.getAnswer());

            return ResponseEntity.ok(faqRepository.save(createdFaq));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
