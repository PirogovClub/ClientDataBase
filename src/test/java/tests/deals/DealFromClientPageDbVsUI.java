package tests.deals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Locale;

import org.junit.Test;

import pageObjects.ClientProperties;
import tests.invoices.TestInvocesClientDataDbVsUI;
import utils.AssertWrapper;
import utils.DataTable;
import utils.HtmlTable;

public class DealFromClientPageDbVsUI extends DealsTestBase{
	
	@Test
	public void runTest() {
		setChieldTestModuleName(this.getClass().getName());
		
		logger.info("Get into Test" + this.getClass().getName());
		logger.info("Going through invoces");
		
		TestInvocesClientDataDbVsUI testInvocesClientDataDbVsUI = new TestInvocesClientDataDbVsUI();
		testInvocesClientDataDbVsUI.initAllTestBase();
		String checkFieldName = "ClientName";
		testInvocesClientDataDbVsUI.selectRandomRecord();
		String forFilter = testInvocesClientDataDbVsUI.selectFileredRecords(checkFieldName);
		testInvocesClientDataDbVsUI.searchForString(forFilter);
		testInvocesClientDataDbVsUI.clickHrefWithText(forFilter);
		logger.info("Get into a client page");
		ClientProperties clientProperties = new ClientProperties(driver);
		

		HtmlTable clientDeals = clientProperties.readClientDeals();
		clientDeals.convertDateColEng2Eng("Open date","dd-MMM-yyyy","yyyy-MM-dd");
		clientDeals.convertStub("Mentor", "----", "");
		filteredListOfDealsFromUI.setTableBody(clientDeals.getNamedTableBody());
		this.selectFileredRecords(checkFieldName,forFilter);
		
		boolean isTestPassed = true;
		isTestPassed = isTestPassed & clientProperties.checkPageTableDbAndUI(filteredListOfDealsFromDB, filteredListOfDealsFromUI);
		AssertWrapper.assertToLog(isTestPassed,"ClientPage");

		testInvocesClientDataDbVsUI.deInitAllTestBase();
	}

	

}
	