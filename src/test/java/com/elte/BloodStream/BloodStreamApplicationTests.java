package com.elte.BloodStream;

import com.elte.BloodStream.repository.ApplicationRepository;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.service.ApplicationService;
import com.elte.BloodStream.service.DonorService;
import com.elte.BloodStream.service.NewsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.System.exit;
import static org.mockito.Mockito.when;


@SpringBootTest
class BloodStreamApplicationTests {
	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(
				DonorServiceTests.class,
				FaqServiceTests.class,
				NewsServiceTests.class,
				ApplicationServiceTests.class,
				PlaceServiceTests.class,
				MessageServiceTests.class
		);
		int countTests = 0;
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("Tests passed: " + result.getRunCount() + ",   " + result.wasSuccessful());
	}

}
