package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import utils.TestJUnit;

public class TestJunit001 {
	public static void main(String[] arg) {
		
		Result result = JUnitCore.runClasses(TestJUnit.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		
		System.out.println(result.wasSuccessful());
		
	}

}
