package tests.contracts;

import static org.junit.Assert.fail;
import org.junit.Test;

public class TestFilterConsultancy extends ContractsAllTestBase {
	
	@Test
	public void runFilterTest() {
		
		String checkFieldName = "Consultancy";
		
		logger.info("Get into Test" + this.getClass().getName());
		logger.info("Checking filter for " + checkFieldName + " field");
		initAllTestBase();
		selectRandomRecord();
		
		String forFilter = selectNeededRecord(checkFieldName);
		
		
		// Type in searchBar
		contractsMain.searchForString(forFilter);
		
		
		Boolean comparationResult = compareFilteredAndDB();
		logger.info("Tables equals? : "+ comparationResult);
		printTableToTerminal(comparationResult);

		if (!comparationResult) {
			logger.fatal("Data is not equal");
		}
		
		
	}
	

}
