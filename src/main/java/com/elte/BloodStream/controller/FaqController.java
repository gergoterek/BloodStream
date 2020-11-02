package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.repository.FaqRepository;
import com.elte.BloodStream.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    FaqService faqService;

    //Fv-ek leellenőrizve 10.14.

    //GUEST
    @GetMapping("/all")
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
    @DeleteMapping("/delete/{faqID}")
    public ResponseEntity<Faq> deleteFaq(@PathVariable Integer faqID) {
        return faqService.deleteFaq(faqID);
    }

    //ADMIN
    @PatchMapping("/modify/{faqID}")
    public ResponseEntity<Faq> modifyFaq(@RequestBody Faq faq, @PathVariable Integer faqID) {
        return faqService.modifyFaq(faq, faqID);
    }

}
