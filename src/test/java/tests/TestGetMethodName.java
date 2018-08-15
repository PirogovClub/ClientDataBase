package tests;

import java.lang.reflect.Method;

import org.junit.Test;

import utils.TraceHelper;

public class TestGetMethodName {
	
	@Test
	public void testMethodNameGetting() {
		
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
	}

}
