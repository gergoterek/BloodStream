package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.repository.FaqRepository;
import com.elte.BloodStream.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    FaqService faqService;


    //USER
    @GetMapping("")
    public Iterable<Faq> getAllFaq() {
        return faqService.getAllFaq();
    }

    //ADMIN
    @PostMapping("/create")
    public ResponseEntity<Faq> createFaq(
            @RequestBody Faq faq
    ) {
        return faqService.createFaq(faq);
    }

    //ADMIN
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Faq> deleteFaq(@PathVariable Integer id) {
        return faqService.deleteFaq(id);
    }

    //ADMIN
    @PatchMapping("/modify")
    public ResponseEntity<Faq> modifyFaq(@RequestBody Faq faq) {
        return faqService.modifyFaq(faq);
    }

}
