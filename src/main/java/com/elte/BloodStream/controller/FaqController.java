package com.elte.BloodStream.controller;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.repository.FaqRepository;
import com.elte.BloodStream.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    //NURSE
    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @GetMapping("/{faqId}")
    public ResponseEntity<Faq> getFaq(@PathVariable Integer faqId) {
        return faqService.getFaq(faqId);
    }

    //NURSE
    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @PutMapping("/{faqID}")
    public ResponseEntity<Faq> modifyFaq(@PathVariable Integer faqID, @RequestBody Faq faq) {
        return faqService.modifyFaq(faqID, faq);
    }

    //NURSE
    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @PostMapping("")
    public ResponseEntity<Faq> createFaq(
            @RequestBody Faq faq
    ) {
        return faqService.createFaq(faq);
    }

    //NURSE
    @Secured({"ROLE_NURSE", "ROLE_ADMIN"})
    @DeleteMapping("/delete/{faqID}")
    public ResponseEntity<Faq> deleteFaq(@PathVariable Integer faqID) {
        return faqService.deleteFaq(faqID);
    }

}
