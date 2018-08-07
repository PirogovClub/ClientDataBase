package tests.contracts;

import static org.junit.Assert.fail;
import org.junit.Test;

public class TestFilterClient extends ContractsAllTestBase {
	
	
	
	
	@Test
	public void runFilterTest() {
		
		initAllTestBase();
		selectRandomRecord();
		String forFilter = selectNeededRecord("ClientName");
		
		// Type in searchBar
		contractsMain.searchForString(forFilter);
		
		
		Boolean comparationResult = compareFilteredAndDB();
		
		printTableToTerminal(comparationResult);

		if (!comparationResult) {
			fail("Data is not equal");
		}
		
	}
	

}
