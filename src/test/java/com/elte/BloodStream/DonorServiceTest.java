package com.elte.BloodStream;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.repository.ApplicationRepository;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.service.ApplicationService;
import com.elte.BloodStream.service.DonorService;
import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DonorServiceTest {

        @Autowired
        private DonorService donorService;

        @MockBean
        private DonorRepository donorRepository;

        @Test
        public void getAllApplicationsTest() {
            when(donorRepository.findAll()).thenReturn(Stream
                    .of( new Donor()).collect(Collectors.toList()
            ));
            assertEquals(donorRepository.findAll().size(), 1);
        }

        //"user", "password", "User", true, "A_POZ", 123456789, "1Q2W3E", new Date(), LocalDateTime.now(), "ROLE_DONOR"



}
