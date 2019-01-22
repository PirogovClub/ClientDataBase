package tests;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import utils.TraceHelper;

public class TestGetMethodName {
	
	@Test
	public void testMethodNameGetting() {
		
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
	}

}
