package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    FaqRepository faqRepository;

    @GetMapping("")
    public Iterable<Faq> getFaq() {
        return faqRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Faq> createGyik(
            @RequestBody Faq faq
    ) {
        Faq savedFaq = faqRepository.save(faq);
        return ResponseEntity.ok(savedFaq);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNews(
            @PathVariable Integer id
    ) {
        try {
            faqRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/modify")
    public ResponseEntity<Faq> modifyNews(@RequestBody Faq faq) {
        Optional<Faq> oldFaq = faqRepository.findById(faq.getFaqId());
        if (oldFaq.isPresent()) {
            Faq createdFaq = oldFaq.get();
            createdFaq.setQuestion(faq.getQuestion());
            createdFaq.setAnswer(faq.getAnswer());
            faqRepository.save(createdFaq);
            return ResponseEntity.ok(createdFaq);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
