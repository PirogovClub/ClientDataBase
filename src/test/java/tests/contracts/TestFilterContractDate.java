package tests.contracts;

import static org.junit.Assert.fail;
import org.junit.Test;

import utils.ConverDates;

public class TestFilterContractDate extends ContractsAllTestBase {
	
	
	
	
	@Test
	public void runFilterTest() {
		
		String filteringField = "ContractDate";
		
		// Search for all records with current filtered value in DB
				updateSqlStringWithSearch(filteringField, testListOfMap.get(0).get(filteringField));
				// Format what retrieved from DB to Map
				filteredListOfContractsFromDB.setTableBody(db.executeQueryToListOfMap(SQLquery));
				// Type in searchBar
				
				ConverDates.setFieldsFromUSLocale(testListOfMap.get(0).get(filteringField));
				contractsMain.searchForString(ConverDates.getOutputImproper());
				
				// Get table from page
				filteredContracts = contractsMain.readTableWithContracts();
				// Rename columns correct date format for comparation
				prepareTable();
				// check id sets are the same
				filteredListOfContractsFromPage.setTableBody(filteredContracts.getNamedTableBody());

				Boolean comparationResult = filteredListOfContractsFromDB.equals(filteredListOfContractsFromPage);
				
		printTableToTerminal(comparationResult);
		
		if (!comparationResult) {
			fail("Data is not equal");
		}
		
	}
	

}
