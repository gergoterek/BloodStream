package com.elte.BloodStream;

import com.elte.BloodStream.model.*;
import com.elte.BloodStream.repository.ApplicationRepository;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.security.AuthenticatedUser;
import com.elte.BloodStream.service.ApplicationService;
import com.elte.BloodStream.service.DonorService;
import org.apache.tomcat.jni.Local;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.elte.BloodStream.model.Donor.BloodTypes.A_POZ;
import static com.elte.BloodStream.model.Donor.Role.ROLE_DONOR;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DonorServiceTest {

        @Autowired
        private DonorService donorService;


        @MockBean
        private DonorRepository donorRepository;



        Donor donor;
        Donor donor2;
        Donor donor3;

        @Before
        public void setUp() {
            donor =  Mockito.mock(Donor.class);
            donor2 = Mockito.mock(Donor.class);
            donor3 = Mockito.mock(Donor.class);
        }

        @After
        public void cleanUp() {
            donor = null;
            donor2 = null;
            donor3 = null;
        }

        @Test
        public void getAllDonorsTest() {
            //given
            //when
            when(donorRepository.findAll()).thenReturn(Stream
                    .of( donor, donor2, donor3 ).collect(Collectors.toList()
            ));
            //then
            assertEquals(3, donorRepository.findAll().size());
            verify(donorRepository, times(1)).findAll();
        }


        @Test
        public void changeDonorDataTest() {
            //given
            int donorID = 1;
            //when
            when(donor.getId()).thenReturn(donorID);
            when(donor2.getId()).thenReturn(donorID);
            when(donorRepository.findById(donor.getId())).thenReturn( Optional.of( donor ));
            //then
            assertEquals(new ResponseEntity(HttpStatus.OK), donorService.changeDonorDataByAdmin(donor2.getId(), donor2));
            verify(donorRepository, times(1)).findById(donor.getId());
            verify( donorRepository, times(1)).save(any(Donor.class));
        }


        @Test
        public void getDonorProfileTest() {
            //given
            int donorID = 1;
            //when
            when(donor.getId()).thenReturn(donorID);
            when(donorRepository.findById(donor.getId())).thenReturn(Optional.of( donor));
            //then
            assertEquals(new ResponseEntity(donor, HttpStatus.OK), donorService.getDonorProfile(donor.getId()));
            verify(donorRepository, times(1)).findById(donor.getId());
        }

        @Test
        public void registerTest() {
            //given
            String pw = "password";
            String name = "Balázs";
            String username = "balazs";
            String idCard = "1q2w3e";
            //when
            when(donor.getBirthDate()).thenReturn(new Date());
            when(donor.getDonorName()).thenReturn( name );
            when( donor.getIdCard()).thenReturn( idCard );
            when( donor.getUsername()).thenReturn( username );
            when( donor.getPassword()).thenReturn( pw );

            Mockito.when(donorRepository.findByUsername( donor.getUsername())).thenReturn( Optional.empty());
            //then
            assertEquals(new ResponseEntity(true, HttpStatus.OK), donorService.register( donor ));
            verify(donorRepository, times(1)).save(any(Donor.class));
        }

}
