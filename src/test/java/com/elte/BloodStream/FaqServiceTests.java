package com.elte.BloodStream;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.repository.FaqRepository;
import com.elte.BloodStream.service.DonorService;
import com.elte.BloodStream.service.FaqService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.elte.BloodStream.model.Donor.BloodTypes.A_POZ;
import static com.elte.BloodStream.model.Donor.Role.ROLE_DONOR;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaqServiceTests {

    @Autowired
    private FaqService faqService;

    @MockBean
    private FaqRepository faqRepository;

    @Test
    public void getFaqsTest() {
        //given
        Faq faq1 = new Faq( 10, "What?", "Who?");
        Faq faq2 = new Faq( 10, "What?", "Who?");

        //when
        when(faqRepository.findAll()).thenReturn(
                Stream.of( faq1, faq2 ).collect(Collectors.toList())
        );

        //then
        assertEquals(faqRepository.findAll().size(), 1);
    }


    @Test
    public void getFaqTest() {
        //given
        final Faq faq = new Faq( 1, "What?", "Who?");

        //when
        Optional<Faq> oFaq = Optional.of(faq);
        when(faqRepository.findById(1)).thenReturn(oFaq);

        //then
        assertEquals(faqRepository.findByFaqId(faq.getFaqId()), faq);
    }

    @Test
    public void deleteFaqTest() {

        //given
        final Faq faq = new Faq( 1, "What?", "Who?");

        Optional<Faq> oFaq = Optional.of(faq);
        Mockito.when(faqRepository.findById(1)).thenReturn(oFaq);

        //when
        faqService.deleteFaq(faq);

        //then
        Mockito.verify(faqRepository, times(1)).deleteById(faq.getFaqId());





//        final ResponseEntity<Faq> result = faqService.deleteFaq(faq.getFaqId());
//        verify(faqRepository, times(1)).deleteById(faq.getFaqId());
//
//        assertEquals(result, new ResponseEntity(true, HttpStatus.OK));







    }


}
