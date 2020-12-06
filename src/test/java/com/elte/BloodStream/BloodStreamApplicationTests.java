package com.elte.BloodStream;

import com.elte.BloodStream.repository.ApplicationRepository;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.service.ApplicationService;
import com.elte.BloodStream.service.DonorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class BloodStreamApplicationTests {

	@Autowired
	private ApplicationService applicationService;

	@MockBean
	private ApplicationRepository applicationRepository;


//	public void getAllApplicationsTest() {
//		when(applicationRepository.findAll()).thenReturn());
//	}

	@Test
	void contextLoads() {
	}

}
