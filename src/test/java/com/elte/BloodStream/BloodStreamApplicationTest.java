package com.elte.BloodStream;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BloodStreamApplicationTest {
	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(
				DonorServiceTest.class,
				FaqServiceTest.class,
				NewsServiceTest.class,
				ApplicationServiceTest.class,
				PlaceServiceTest.class,
				MessageServiceTest.class
		);
 		int countTests = 0;
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("Tests passed: " + result.getRunCount() + ",   " + result.wasSuccessful());
	}



}
