package tests.contracts;

import static org.junit.Assert.fail;


import org.junit.Test;

import utils.AssertWarp;

public class TestFilterClient extends ContractsAllTestBase {
	
	
	
	
	@Test
	public void runFilterTest() {
		
		String checkFieldName = "ClientName";
		
		setChieldTestModuleName(this.getClass().getName());
		logger.info("Get into Test" + this.getClass().getName());
		logger.info("Checking filter for " + checkFieldName + " field");
		
		initAllTestBase();
		selectRandomRecord();
		String forFilter = selectNeededRecord(checkFieldName);
		
		// Type in searchBar
		contractsMain.searchForString(forFilter);
		prepareFilteredAndDB();
		
		boolean isTestPassed = true;
		isTestPassed = isTestPassed & contractsMain.checkPageTableDbAndUI(getFilteredListOfContractsFromDB(), getFilteredListOfContractsFromPage());
		AssertWarp.assertToLog(isTestPassed);
		
		
	}
	

}
