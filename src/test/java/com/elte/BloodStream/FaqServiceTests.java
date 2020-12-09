package com.elte.BloodStream;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Faq;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.repository.FaqRepository;
import com.elte.BloodStream.service.DonorService;
import com.elte.BloodStream.service.FaqService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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

    Faq faq = null;
    Faq faq2 = null;
    Faq faq3 = null;

    @Before
    public void init() {
        faq = Mockito.mock(Faq.class);
        faq2 = Mockito.mock(Faq.class);
        faq3 = Mockito.mock(Faq.class);
    }

    @After
    public void cleanUp() {
        System.out.println(faq);
        faq = null;
        faq2 = null;
        faq3 = null;
    }

    @Test
    public void getFaqsTest() {
        //given
        //when
        when(faqRepository.findAll()).thenReturn(
                Stream.of( faq, faq2, faq3 ).collect(Collectors.toList())
        );
        //then
        assertEquals(3, faqRepository.findAll().size());
        verify(faqRepository, times(1)).findAll();
    }

    @Test
    public void getFaqTest() {
        //given
        int faqID = 1;
        Optional<Faq> oFaq = Optional.of(faq);
        //when
        when(faq.getFaqId()).thenReturn(faqID);
        when(faqRepository.findByFaqId(faq.getFaqId())).thenReturn(oFaq);
        //then
        assertEquals(new ResponseEntity(faq, HttpStatus.OK), faqService.getFaq(faq.getFaqId()));
        verify(faqRepository, times(1)).findByFaqId(faq.getFaqId());
    }

    @Test
    public void createFaqTest() {
        //given
        //when
        Mockito.when(faqRepository.save(any(Faq.class))).thenReturn(faq);
        //then
        assertEquals(new ResponseEntity(faq, HttpStatus.OK), faqService.createFaq(faq));
        verify(faqRepository, times(1)).save(any(Faq.class));
    }

    @Test
    public void modifyFaqTest() {
        //given
        int faqID = 1;
        Optional<Faq> oFaq = Optional.of(faq);
        //when
        when(faq.getFaqId()).thenReturn(faqID);
        when(faq2.getFaqId()).thenReturn(faqID);
        when(faqRepository.findByFaqId(faq.getFaqId())).thenReturn(oFaq);
        //then
        assertEquals(new ResponseEntity(HttpStatus.OK), faqService.modifyFaq(faq2.getFaqId(), faq2));
        verify(faqRepository, times(1)).findByFaqId(faq.getFaqId());
    }

//    @Test
//    public void deleteFaqTest() {
//        //given
//        int faqID = 1;
//        Optional<Faq> oFaq = Optional.of(faq);
//        //when
//        when(faq.getFaqId()).thenReturn(faqID);
//        Mockito.when(faqRepository.findByFaqId( faqID )).thenReturn(oFaq);
//        faqService.deleteFaq(faq);
//        //then
//        verify(faqRepository, times(1)).delete(faq);
//    }
}
