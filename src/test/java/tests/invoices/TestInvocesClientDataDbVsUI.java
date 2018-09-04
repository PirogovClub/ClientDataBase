package tests.invoices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pageObjects.ClientProperties;
import utils.AssertWarp;
import utils.DataTable;
import utils.HtmlTable;

public class TestInvocesClientDataDbVsUI extends InvoicesTestBase {
	
	public void searchForString(String forFilter) {
		// TODO Auto-generated method stub
		invoceMain.searchForString(forFilter);
	}

	public void clickHrefWithText(String forFilter) {
		// TODO Auto-generated method stub
		invoceMain.clickHrefWithText(forFilter);
	}
	
	@Test
	public void runTest() {
		
		
		
		setChieldTestModuleName(this.getClass().getName());
		logger.info("Get into Test" + this.getClass().getName());
		String checkFieldName = "ClientName";
		selectRandomRecord();
		String forFilter = selectFileredRecords(checkFieldName);
		
		
		
		// Type in searchBar
		
		invoceMain.searchForString(forFilter);

		// Get table from page
		filteredInvoices = invoceMain.readTableWithInvoces();
		// Rename columns correct date format for comparation
		prepareInvoicesUITable();
		
		// check id sets are the same
		filteredListOfInvoceFromPage.setTableBody(filteredInvoices.getNamedTableBody());
		
		prepareInvocesDBTable();
		invoceMain.checkPageTableDbAndUI(getFilteredListOfInvoceFromDB(), filteredListOfInvoceFromPage);
		
		boolean isTestPassed = true;
		isTestPassed = isTestPassed & invoceMain.checkPageTableDbAndUI(getFilteredListOfInvoceFromDB(), filteredListOfInvoceFromPage);
		AssertWarp.assertToLog(isTestPassed);
		
		
	}

	

}
