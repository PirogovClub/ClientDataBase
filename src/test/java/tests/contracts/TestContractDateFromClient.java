package tests.contracts;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import pageObjects.ContractsDetail;
import utils.DataTable;

public class TestContractDateFromClient extends ContractsAllTestBase {

	/**
	 * 
	 */
	@Test
	public void runTest() {

		String filteringField = "ContractsDate";
		setChieldTestModuleName(this.getClass().getName());
		logger.info("Get into Test" + this.getClass().getName());

		// Search for all records with current filtered value in DB
		updateSqlStringWithSearch(filteringField, testListOfMap.get(0).get(filteringField));
		// Format what retrieved from DB to Map
		filteredListOfContractsFromDB.setTableBody(db.executeQueryToListOfMap(SQLquery));
		// Type in searchBar
		String contractToWorkWith = filteredListOfContractsFromDB.getTableBody().get(0).get("Contract");
		contractsMain.searchForString(testListOfMap.get(0).get(filteringField));
		contractsMain.clickHrefWithLink("/admin/contracts/"+contractToWorkWith);
		
		ContractsDetail contractsDetail = new ContractsDetail(driver);
		contractsDetail.waitForElement2BeVisible(contractsDetail.getWaitForElement());
		DataTable contractData = new DataTable();
		contractData.setTableBody(contractsDetail.readContractData());
		contractData.printTable();
		contractData.renameKeys("Contract Date:", "Contract Date");
		contractData.renameKeys("Payment type:", "Payment Type");
		contractData.renameKeys("Payment date:", "Payment Date");
		contractData.renameKeys("Price:", "Price");
		contractData.renameKeys("Employee:", "Mentor");
		contractData.renameKeys("Deal:", "Deal");
		contractData.renameKeys("Employee rate:", "Employee Rate");
		contractData.printTable();
		// Search for all records with current filtered value in DB
		contractsDetail.updateSqlStringWithContractId(contractToWorkWith);
		filteredListOfContractsFromDB.setTableBody(db.executeQueryToListOfMap(contractsDetail.getSQLquery()));
		// Format what retrieved from DB to Map
		filteredListOfContractsFromDB.printTable();
		
		//Boolean comparationResult = true;
		Boolean comparationResult = filteredListOfContractsFromDB.equals(contractData);
		//printTableToTerminal(comparationResult);

		if (!comparationResult) {
			fail("Data is not equal");
		}

	}

}
