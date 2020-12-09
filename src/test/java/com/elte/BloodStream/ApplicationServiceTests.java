package com.elte.BloodStream;

import com.elte.BloodStream.model.*;
import com.elte.BloodStream.repository.*;
import com.elte.BloodStream.service.ApplicationService;
import com.elte.BloodStream.service.FaqService;
import com.elte.BloodStream.service.MessageService;
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
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServiceTests {

        @Autowired
        private ApplicationService applicationService;

        @MockBean
        private MessageService messageService;

        @MockBean
        private ApplicationRepository applicationRepository;

        @MockBean
        private DonorRepository donorRepository;

        @MockBean
        private PlaceRepository placeRepository;

        @MockBean
        private DonationRepository donationRepository;

        @MockBean
        private  MessageRepository messageRepository;


        Application app;
        Application app2;
        Application app3;

        @Before
        public void setUp() {
            app = Mockito.mock(Application.class);
            app2 = Mockito.mock(Application.class);
            app3 = Mockito.mock(Application.class);
        }

        @After
        public void cleanUp() {
            app = null;
            app2 = null;
            app3 = null;
        }

        @Test
        public void getAllApplicationsTest() {
            //given
            //when
            when(applicationRepository.findAll()).thenReturn(
                    Stream.of( app, app2 ).collect(Collectors.toList())
            );
            //then
            assertEquals(2, applicationRepository.findAll().size());
        }

       @Test
       public void getApplicationTest() {
           //given
           //when
           Optional<Application> optionalApplication = Optional.of(app);
           when(applicationRepository.findByApplyId( app.getApplyId() ))
                   .thenReturn(optionalApplication);
           //then
           assertEquals(new ResponseEntity(app, HttpStatus.OK),
                   applicationService.getApplication(app.getApplyId()));
           verify(applicationRepository, times(1)).findByApplyId(app.getApplyId());
       }

        @Test
        public void getDonorPastApplicationsTest() {
            //given
            int donorID = 1;
            Donor donor = Mockito.mock(Donor.class);
            when(donor.getId()).thenReturn( donorID );

            //when
            when(applicationRepository.findAllByDonorIdAndDonationIsNotNull( donorID ))
                    .thenReturn(
                            Stream.of( app ).collect(Collectors.toList())
            );
            //then
            assertEquals(1, applicationRepository.findAllByDonorIdAndDonationIsNotNull(donor.getId()).size());
            verify(applicationRepository, times(1)).findAllByDonorIdAndDonationIsNotNull( donorID );
        }

    @Test
    public void getDonorNextApplicationTest() {
        //given
        Donor donor = Mockito.mock(Donor.class);
        int donorID = 1;
        when(donor.getId()).thenReturn( donorID );

        //when
        when(applicationRepository.findByDonorIdAndDonationIsNull( donorID ))
                .thenReturn( app );
        //then
        assertEquals(app, applicationRepository.findByDonorIdAndDonationIsNull(donor.getId()));
        verify(applicationRepository, times(1)).findByDonorIdAndDonationIsNull( donorID );
    }
//
//        @Test
//        public void modifyFaqTest() {
//            //given
//            Faq faq = new Faq( 1, "What?", "Who?");
//            Faq modifiedFaq = new Faq( 1, "What2?", "Who2?");
//            //when
//            Optional<Faq> oFaq = Optional.of(faq);
//            when(faqRepository.findByFaqId(faq.getFaqId())).thenReturn(oFaq);
//            //then
//            assertEquals(new ResponseEntity(HttpStatus.OK), faqService.modifyFaq(modifiedFaq.getFaqId(), modifiedFaq));
//        }
//
        @Test
        public void deleteApplicationTest() {
            //given
            int applyID = 1;
            int donorID = 1;
            Donor donor = Mockito.mock( Donor.class);
            //when
            when(app.getDonor()).thenReturn( donor );
            when( app.getApplyId()).thenReturn(applyID);
            Mockito.when(applicationRepository.findById( applyID )).thenReturn( Optional.of( app ));
            //then
            assertEquals(new ResponseEntity( HttpStatus.OK), applicationService.deleteDonorApplication( applyID ));
            verify(applicationRepository, times(1)).deleteById(applyID);
        }


        @Test
        public void newApplicationTest() {
            //given
            Donor donor = Mockito.mock( Donor.class);
            Place place = Mockito.mock( Place.class);

            //when
            when( app.getDonor()).thenReturn( donor );
            when( app.getPlace()).thenReturn( place );

            Mockito.when(placeRepository.findById( app.getPlace().getId())).thenReturn( Optional.of ( place ));
            Mockito.when(donorRepository.findById( app.getDonor().getId())).thenReturn( Optional.of ( donor ));
            when( app.getAppliedDate()).thenReturn(new Date());
            when( donor.getNextDonationDate()).thenReturn(LocalDateTime.now().minusDays(1));

            Mockito.when(applicationRepository.save(any(Application.class))).thenReturn(app);
            //then
            assertEquals(new ResponseEntity(app, HttpStatus.OK), applicationService.newApplication(app));
            verify(applicationRepository, times(1)).save(any(Application.class));
            verify(donorRepository, times(1)).findById(app.getDonor().getId());
            verify(placeRepository, times(1)).findById(app.getPlace().getId());
        }

        @Test
        public void setDonationTest() {
            //given
            Donor donor = Mockito.mock( Donor.class);
            //when
            when( app.getDonor()).thenReturn( donor );
            Mockito.when(applicationRepository.findByApplyId( app.getApplyId())).thenReturn( Optional.of (app));
            when( donor.getBloodType()).thenReturn(A_POZ);
            //then
            assertEquals(new ResponseEntity(HttpStatus.OK), applicationService.setDonation(app.getApplyId(), app));
            verify(applicationRepository, times(1)).findByApplyId(app.getApplyId());
            verify( applicationRepository, times(1)).save(any(Application.class));
        }


        @Test
        public void setDonationTransportTest() {
            //given
            Donor donor = Mockito.mock( Donor.class);
            Donation don = Mockito.mock(Donation.class);
            Message msg = Mockito.mock(Message.class);

            //when
            when( app.getDonor()).thenReturn( donor );
            Mockito.when(donationRepository.findById( app.getApplyId())).thenReturn( Optional.of (don));
            Mockito.when(applicationRepository.findByApplyId( app.getApplyId())).thenReturn( Optional.of (app));
            Mockito.when(messageRepository.save( msg)).thenReturn(msg);

            //then
            assertEquals(new ResponseEntity(HttpStatus.OK), applicationService.setDonationTransport(app.getApplyId(), app));
            verify(applicationRepository, times(1)).findByApplyId(app.getApplyId());
            verify( applicationRepository, times(1)).save(any(Application.class));
            verify(messageService, times(1)).transportNewMsg(any(Application.class));
        }
}
