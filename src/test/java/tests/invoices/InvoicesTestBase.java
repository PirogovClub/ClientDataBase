package tests.invoices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;

import pageObjects.ContractsMain;
import pageObjects.InvoicesMain;
import pageObjects.MainNavigation;
import tests.BaseTest;
import utils.ConverDates;
import utils.DataBase;
import utils.DataTable;
import utils.HtmlTable;
import utils.PrintOuts;



public class InvoicesTestBase extends BaseTest {
	
	protected List<Map<String,String>> testListOfMap = new ArrayList<Map<String,String>>();
	private DataTable filteredListOfInvoceFromDB = new DataTable();
	
	protected DataTable filteredListOfInvoceFromPage = new DataTable();
	protected DataBase db = new DataBase();
	protected String SQLquery = "";
	protected InvoicesMain invoceMain;
	protected MainNavigation mainNavigation;
	protected HtmlTable filteredInvoices;
	
	
	
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
				orderByStatment = "ORDER BY invoiceext.id";
				break;
			case "Consultancy":
				whereStatement = " Where consultancy.name = '"+value+"' ";
				havingStatment = " ";
				limitStatment = "";
				orderByStatment = "ORDER BY invoiceext.id ";
				break;	
			case "Type":
				whereStatement = " Where contract.payment_type = '"+value+"' ";
				havingStatment = " ";
				limitStatment = "";
				orderByStatment = "ORDER BY invoiceext.id";
				break;
			case "Status":
				whereStatement = " Where deal.status = '"+value+"' ";
				havingStatment = " ";
				limitStatment = "";
				orderByStatment = "ORDER BY invoiceext.id";
				break;
			case "ContractDate":
				whereStatement = " Where contract.contract_date = '"+value+"' ";
				havingStatment = " ";
				limitStatment = "";
				orderByStatment = "ORDER BY invoiceext.id";
				break;
			case "Mentor":
				whereStatement = " ";
				havingStatment = " HAVING CONCAT(employee.first_name, ' ', employee.last_name)  = '"+value+"'";
				limitStatment = "";
				orderByStatment = "ORDER BY invoiceext.id";
				break;
			default:
				whereStatement = "";
				havingStatment = "";
				limitStatment = " LIMIT 1\r\n";
				orderByStatment = " Order By RANDOM()\r\n";
				break;
				
		}
		
		SQLquery = "select \r\n" + 
				"        invoiceext.id as \"Invoice Id\",\r\n" + 
				"        CONCAT(client.first_name, ' ', client.last_name ) as \"ClientName\",\r\n" + 
				"        invoiceext.contract_id as \"Contact ID\",\r\n" + 
				"        CONCAT(money_client.currency,' ',money_client.amount) as \"Payment amount\",\r\n" +
				"        invoiceext.period_to as \"Payment date\",\r\n" + 
				"        invoiceext.period_from as \"Payment date From\",\r\n" + 
				"        invoiceext.period_to as \"Payment date To\",\r\n" +
				"        invoiceext.PaymentID as \"Payment status\",\r\n" + 
				 
				"        contract.contract_date as \"Contract Date\",\r\n" + 
				"        contract.payment_type as \"Payment Type\",\r\n" + 
				
				"        money_client.amount as \" Price\",\r\n" + 
				"        money_client.currency as \" Price Currency\",\r\n" + 
				 
				"        consultancy.name as \"Consultancy\", \r\n" + 
				"        CONCAT(employee.first_name, ' ', employee.last_name) as \"Mentor\",\r\n" + 
				"        money_employee.amount \"Employee Rate\",\r\n" + 
				"        money_employee.currency \"Employee Currency\",\r\n" + 
				"        contract.created_date as \"ContractDate\",\r\n" + 
				"        contract.payment_type as \"Type\",\r\n" + 
				"        deal.status as \"Status\"\r\n" + 
				"From    \r\n" + 
				"        (select invoice.*, COALESCE(payment.id, 0) as \"paymentid\" FROM invoice left join  payment on  invoice.id=payment.invoice_id)\r\n" + 
				" as invoiceext\r\n" + 
				"inner join (money as money_client inner join ( money as money_employee inner join (consultancy inner join (client inner join (employee inner join (Contract inner join deal \r\n" + 
				"        on (contract.deal_id = deal.id))\r\n" + 
				"        on (employee.id = contract.employee_id))\r\n" + 
				"        on (client.id = deal.client_id))\r\n" + 
				"        on (consultancy.id = deal.consultancy_id))\r\n" + 
				"        on (money_employee.id = contract.employee_rate_id))\r\n" + 
				"        on (money_client.id = contract.price_id))\r\n" + 
				"        on (invoiceext.contract_id = contract.id)\r\n" + 
				whereStatement+
				"Group by\r\n" + 
				"invoiceext.id,\r\n" + 
				"invoiceext.contract_id,\r\n" + 
				"client.first_name,\r\n" + 
				"client.last_name,\r\n" + 
				"consultancy.name,\r\n" + 
				"employee.first_name,\r\n" + 
				"employee.last_name,\r\n" + 
				"contract.created_date,\r\n" + 
				"contract.payment_type,\r\n" + 
				"deal.status,\r\n" + 
				"invoiceext.paymentid,\r\n" + 
				"contract.contract_date,\r\n" + 
				"invoiceext.period_from,\r\n" +
				"invoiceext.period_to,\r\n" + 
				"money_client.amount,\r\n" + 
				" money_client.currency,\r\n" + 
				" money_employee.amount,\r\n" + 
				" money_employee.currency"+
				
				havingStatment+orderByStatment + limitStatment
				;
				
		logger.debug("\n\rSQLquery:"+SQLquery);
		
	}
	
	
	
	protected void printTableToTerminal(Boolean comparationResult) {
		logger.debug("Comparation:"+comparationResult);
		logger.debug("FromDB");
		getFilteredListOfInvoceFromDB().printTable();
		logger.debug("FromPage");
		filteredListOfInvoceFromPage.printTable();
	}
	
	protected Boolean runTestForField(String filteringField) {
		
		// Search for all records with current filtered value in DB
		updateSqlStringWithSearch(filteringField, testListOfMap.get(0).get(filteringField));
		// Format what retrieved from DB to Map
		getFilteredListOfInvoceFromDB().setTableBody(db.executeQueryToListOfMap(SQLquery));
		// Type in searchBar
		invoceMain.searchForString(testListOfMap.get(0).get(filteringField));
		// Get table from page
		filteredInvoices = invoceMain.readTableWithInvoces();
		// Rename columns correct date format for comparation
		prepareInvoicesUITable();
		// check id sets are the same
		filteredListOfInvoceFromPage.setTableBody(filteredInvoices.getNamedTableBody());

		Boolean comparationResult = getFilteredListOfInvoceFromDB().equals(filteredListOfInvoceFromPage);

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
	
	public String selectFileredRecords(String filteringField) {
		logger.trace("Search for all records with current filtered value in DB");
		updateSqlStringWithSearch(filteringField, testListOfMap.get(0).get(filteringField));
		// Format what retrieved from DB to Map
		getFilteredListOfInvoceFromDB().setTableBody(db.executeQueryToListOfMap(SQLquery));
		return testListOfMap.get(0).get(filteringField);
	}
	
	public boolean compareFilteredAndDB(String failText) {
		return true;
		
	}
	
	protected void prepareInvoicesUITable() {
		filteredInvoices.printTable();
		filteredInvoices.renameHeader("Client", "ClientName");
		filteredInvoices.renameHeader("Id", "Invoice Id");
		//filteredInvoices.convertDateCol("Payment date","yyyy-MM-dd");
	}
	
	protected void prepareInvocesDBTable() {
		// TODO Auto-generated method stub
		getFilteredListOfInvoceFromDB().convertInt2Bool("Payment status");
		getFilteredListOfInvoceFromDB().removeCol("Payment Type");
		getFilteredListOfInvoceFromDB().removeCol("Contract Date");
		getFilteredListOfInvoceFromDB().removeCol("Payment Type");
		getFilteredListOfInvoceFromDB().removeCol("Payment Date");
		getFilteredListOfInvoceFromDB().removeCol(" Price");
		getFilteredListOfInvoceFromDB().removeCol(" Price Currency");
		getFilteredListOfInvoceFromDB().removeCol("Consultancy");
		getFilteredListOfInvoceFromDB().removeCol("Mentor");
		getFilteredListOfInvoceFromDB().removeCol("Employee Rate");
		getFilteredListOfInvoceFromDB().removeCol("Employee Currency");
		getFilteredListOfInvoceFromDB().removeCol("ContractDate");
		getFilteredListOfInvoceFromDB().removeCol("Type");
		getFilteredListOfInvoceFromDB().removeCol("Status");
		getFilteredListOfInvoceFromDB().removeCol("Payment date From");
		getFilteredListOfInvoceFromDB().removeCol("Payment date To");
	}

	@Before
	public void initAllTestBase() {

		db.connectToDb();
		invoceMain = new InvoicesMain(driver);
		mainNavigation = new MainNavigation(driver);
		mainNavigation.clickInvoicesAllMenu();

	}

	@After

	public void deInitAllTestBase() {
		testListOfMap.clear();

		db.closeConnection();

	}
	
	private void tmpStoreSQL() {
		String whereStatement = "";
		String havingStatment= "";
		String orderByStatment= "";
		String limitStatment= "";
		String SQLquery = "select \r\n" + 
				"        invoiceext.id as \"Invoice Id\",\r\n" + 
				"        CONCAT(client.first_name, ' ', client.last_name ) as \"ClientName\",\r\n" + 
				"        invoiceext.contract_id as \"Contact ID\",\r\n" + 
				"        CONCAT(money_client.currency,' ',money_client.amount) as \"Payment amount\",\r\n" +
				"        contract.payment_date as \"Payment date\",\r\n" + 
				"        invoiceext.PaymentID as \"Payment status\"\r\n" + 
				 
				"        contract.contract_date as \"Contract Date\",\r\n" + 
				"        contract.payment_type as \"Payment Type\",\r\n" + 
				"        contract.payment_date as \"Payment Date\",\r\n" + 
				"        money_client.amount as \" Price\",\r\n" + 
				"        money_client.currency as \" Price Currency\",\r\n" + 
				"        CONCAT(money_client.currency,' ',money_client.amount) as \"Payment amount\",\r\n" +
				"        invoiceext.PaymentID as \"Payment status\",\r\n" + 
				"        \r\n" + 
				"        consultancy.name as \"Consultancy\", \r\n" + 
				"        CONCAT(employee.first_name, ' ', employee.last_name) as \"Mentor\",\r\n" + 
				"        money_employee.amount \"Employee Rate\",\r\n" + 
				"        money_employee.currency \"Employee Currency\",\r\n" + 
				"        contract.created_date as \"ContractDate\",\r\n" + 
				"        contract.payment_type as \"Type\",\r\n" + 
				"        deal.status as Status\r\n" + 
				"From    \r\n" + 
				"        (select invoice.*, COALESCE(payment.id, 0) as \"paymentid\" FROM invoice left join  payment on  invoice.id=payment.invoice_id)\r\n" + 
				" as invoiceext\r\n" + 
				"inner join (money as money_client inner join ( money as money_employee inner join (consultancy inner join (client inner join (employee inner join (Contract inner join deal \r\n" + 
				"        on (contract.deal_id = deal.id))\r\n" + 
				"        on (employee.id = contract.employee_id))\r\n" + 
				"        on (client.id = deal.client_id))\r\n" + 
				"        on (consultancy.id = deal.consultancy_id))\r\n" + 
				"        on (money_employee.id = contract.employee_rate_id))\r\n" + 
				"        on (money_client.id = contract.price_id))\r\n" + 
				"        on (invoiceext.contract_id = contract.id)\r\n" + 
				whereStatement+
				"Group by\r\n" + 
				"invoiceext.id,\r\n" + 
				"invoiceext.contract_id,\r\n" + 
				"client.first_name,\r\n" + 
				"client.last_name,\r\n" + 
				"consultancy.name,\r\n" + 
				"employee.first_name,\r\n" + 
				"employee.last_name,\r\n" + 
				"contract.created_date,\r\n" + 
				"contract.payment_type,\r\n" + 
				"deal.status,\r\n" + 
				"invoiceext.paymentid,\r\n" + 
				"contract.contract_date,\r\n" + 
				"contract.payment_date,\r\n" + 
				"money_client.amount,\r\n" + 
				" money_client.currency,\r\n" + 
				" money_employee.amount,\r\n" + 
				" money_employee.currency"+
				
				havingStatment+orderByStatment + limitStatment
				;
	}

	/**
	 * @return the filteredListOfInvoceFromDB
	 */
	public DataTable getFilteredListOfInvoceFromDB() {
		return filteredListOfInvoceFromDB;
	}

	/**
	 * @param filteredListOfInvoceFromDB the filteredListOfInvoceFromDB to set
	 */
	public void setFilteredListOfInvoceFromDB(DataTable filteredListOfInvoceFromDB) {
		this.filteredListOfInvoceFromDB = filteredListOfInvoceFromDB;
	}


}
