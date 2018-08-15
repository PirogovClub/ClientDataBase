package tests.contracts;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;

import pageObjects.ContractsMain;
import pageObjects.MainNavigation;
import tests.BaseTest;
import utils.ConverDates;
import utils.DataBase;
import utils.DataTable;
import utils.HtmlTable;
import utils.PrintOuts;



public class ContractsAllTestBase extends BaseTest {
	
	protected List<Map<String,String>> testListOfMap = new ArrayList<Map<String,String>>();
	protected DataTable filteredListOfContractsFromDB = new DataTable();
	protected DataTable filteredListOfContractsFromPage = new DataTable();
	protected DataBase db = new DataBase();
	protected String SQLquery = "";
	protected ContractsMain contractsMain;
	protected MainNavigation mainNavigation;
	protected HtmlTable filteredContracts;
	PrintOuts printOut = new PrintOuts();
	
	
	protected void updateSqlStringWithSearch() {
		updateSqlStringWithSearch("","");
	}
	
	protected void updateSqlStringWithSearch(String field, String value) {
		String whereStatement, havingStatment, limitStatment, orderByStatment;
		
		switch (field) {
			case "ClientName":
				whereStatement = "";
				havingStatment = " HAVING CONCAT(client.first_name, ' ', client.last_name ) = '"+value+"' ";
				limitStatment = "";
				orderByStatment = "ORDER BY contract.id";
				break;
			case "Consultancy":
				whereStatement = " Where consultancy.name = '"+value+"' ";
				havingStatment = " ";
				limitStatment = "";
				orderByStatment = "ORDER BY contract.id ";
				break;	
			case "Type":
				whereStatement = " Where contract.payment_type = '"+value+"' ";
				havingStatment = " ";
				limitStatment = "";
				orderByStatment = "ORDER BY contract.id";
				break;
			case "Status":
				whereStatement = " Where deal.status = '"+value+"' ";
				havingStatment = " ";
				limitStatment = "";
				orderByStatment = "ORDER BY contract.id";
				break;
			case "ContractDate":
				whereStatement = " Where contract.contract_date = '"+value+"' ";
				havingStatment = " ";
				limitStatment = "";
				orderByStatment = "ORDER BY contract.id";
				break;
			case "Mentor":
				whereStatement = " ";
				havingStatment = " HAVING CONCAT(employee.first_name, ' ', employee.last_name)  = '"+value+"'";
				limitStatment = "";
				orderByStatment = "ORDER BY contract.id";
				break;
			default:
				whereStatement = "";
				havingStatment = "";
				limitStatment = " LIMIT 1\r\n";
				orderByStatment = " Order By RANDOM()\r\n";
				break;
				
		}
		
		SQLquery = "select \r\n" + 
				"        contract.id as \"Contract\",\r\n" + 
				"        CONCAT(client.first_name, ' ', client.last_name ) as \"ClientName\",\r\n" + 
				"        consultancy.name as \"Consultancy\", \r\n" + 
				"        CONCAT(employee.first_name, ' ', employee.last_name) as \"Mentor\",\r\n" + 
				"        contract.contract_date as \"ContractDate\",\r\n" + 
				"        contract.payment_type as \"Type\",\r\n" + 
				"        deal.status as \"Status\"\r\n" + 
				"From \r\n" + 
				"        consultancy inner join (client inner join (employee inner join (Contract inner join deal \r\n" + 
				"        on (contract.deal_id = deal.id))\r\n" + 
				"        on (employee.id = contract.employee_id))\r\n" + 
				"        on (client.id = deal.client_id))\r\n" + 
				"        on (consultancy.id = deal.consultancy_id)\r\n" + 
				whereStatement+
				"Group by\r\n" + 
				"contract.id,\r\n" + 
				"client.first_name,\r\n" + 
				"client.last_name,\r\n" + 
				"consultancy.name,\r\n" + 
				"employee.first_name,\r\n" + 
				"employee.last_name,\r\n" + 
				"contract.created_date,\r\n" + 
				"contract.payment_type,\r\n" + 
				"deal.status\r\n"+
				havingStatment+orderByStatment + limitStatment
				;
		logger.debug("\n\rSQLquery:"+SQLquery);
		
	}
	
	protected void prepareTable() {
		filteredContracts.printTable();
		filteredContracts.renameHeader("Client", "ClientName");
		filteredContracts.renameHeader("Contract date", "ContractDate");
		filteredContracts.convertDateCol("ContractDate","yyyy-MM-dd");
	}
	
	protected void printTableToTerminal(Boolean comparationResult) {
		logger.debug("Comparation:"+comparationResult);
		logger.debug("FromDB");
		filteredListOfContractsFromDB.printTable();
		logger.debug("FromPage");
		filteredListOfContractsFromPage.printTable();
	}
	
	protected Boolean runTestForField(String filteringField) {
		
		// Search for all records with current filtered value in DB
		updateSqlStringWithSearch(filteringField, testListOfMap.get(0).get(filteringField));
		// Format what retrieved from DB to Map
		filteredListOfContractsFromDB.setTableBody(db.executeQueryToListOfMap(SQLquery));
		// Type in searchBar
		contractsMain.searchForString(testListOfMap.get(0).get(filteringField));
		// Get table from page
		filteredContracts = contractsMain.readTableWithContracts();
		// Rename columns correct date format for comparation
		prepareTable();
		// check id sets are the same
		filteredListOfContractsFromPage.setTableBody(filteredContracts.getNamedTableBody());

		Boolean comparationResult = filteredListOfContractsFromDB.equals(filteredListOfContractsFromPage);

		return comparationResult;
	}
	
	public void selectRandomRecord() {
		// Select initial record to test
				updateSqlStringWithSearch();
				testListOfMap.clear();
				testListOfMap = db.executeQueryToListOfMap(SQLquery);
				
				logger.trace("Initials selected contract from DB");
				logger.trace(PrintOuts.getListOfMap(testListOfMap));
				
		
	}
	
	public String selectNeededRecord(String filteringField) {
		
		// Search for all records with current filtered value in DB
		updateSqlStringWithSearch(filteringField, testListOfMap.get(0).get(filteringField));
		// Format what retrieved from DB to Map
		filteredListOfContractsFromDB.setTableBody(db.executeQueryToListOfMap(SQLquery));
		return testListOfMap.get(0).get(filteringField);
	}
	
	public boolean compareFilteredAndDB() {
		// Get table from page
		filteredContracts = contractsMain.readTableWithContracts();
		// Rename columns correct date format for comparation
		prepareTable();
		// check id sets are the same
		filteredListOfContractsFromPage.setTableBody(filteredContracts.getNamedTableBody());

		return filteredListOfContractsFromDB.equals(filteredListOfContractsFromPage);
	}
	
	
	
	public void initAllTestBase() {
		
		db.connectToDb();

		
		contractsMain = new ContractsMain(driver);
		mainNavigation = new MainNavigation(driver);
		mainNavigation.clickContractsAllMenu();
		
		
	}
	
	@After
	
	public void deInitAllTestBase() {
		testListOfMap.clear();
		
		db.closeConnection();
		
	}
	


}
