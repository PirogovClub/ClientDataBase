package tests.contracts;

import static org.junit.Assert.fail;
import org.junit.Test;

public class TestFilterConsultancy extends ContractsAllTestBase {
	
	@Test
	public void runFilterTest() {
		
		Boolean comparationResult = runTestForField("Consultancy");
		printTableToTerminal(comparationResult);
		
		if (!comparationResult) {
			fail("Data is not equal");
		}
		
	}
	

}
