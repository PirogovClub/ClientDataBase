package tests.deals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;

import pageObjects.InvoicesMain;
import pageObjects.MainNavigation;
import tests.BaseTest;
import utils.DataBase;
import utils.DataTable;

public class DealsTestBase extends BaseTest{
	DataTable filteredListOfDealsFromDB = new DataTable();
	DataTable filteredListOfDealsFromUI = new DataTable();
	protected List<Map<String,String>> testListOfMap = new ArrayList<Map<String,String>>();
	protected String SQLquery = "";
	protected DataBase db = new DataBase();
	
	protected String selectFileredRecords(String filteringField, String forFilter) {
		// TODO Auto-generated method stub
		logger.trace("Search for all records with current filtered value in DB");
		
		updateSqlStringWithSearch(filteringField, forFilter);
		// Format what retrieved from DB to Map
		filteredListOfDealsFromDB.setTableBody(db.executeQueryToListOfMap(SQLquery));
		return forFilter;
		
	}
	
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
				orderByStatment = "ORDER BY  CONCAT(employee.first_name, ' ', employee.last_name)";
				break;
			
			default:
				whereStatement = "";
				havingStatment = "";
				limitStatment = " LIMIT 1\r\n";
				orderByStatment = " Order By RANDOM()\r\n";
				break;
				
		}
		
		SQLquery = "select \r\n" + 
				"        deal.id as \"Id\",\r\n" + 
				"        consultancy.name as \"Consultancy\", \r\n" + 
				"        deal.status as \"Deal status\",\r\n" + 
				"        deal.open_date as \"Open date\",\r\n" + 
				"        CONCAT(employee.first_name, ' ', employee.last_name) as \"Mentor\"\r\n" + 
				"        \r\n" + 
				"        \r\n" + 
				"From    deal inner join client on (client.id = deal.client_id) \r\n" + 
				"            inner join consultancy on (consultancy.id = deal.consultancy_id)\r\n" + 
				"            left join (contract inner join employee  on (employee.id = contract.employee_id))  on (contract.deal_id = deal.id)" + 
				whereStatement+
				"Group by\r\n" + 
				"deal.id,\r\n" + 
				"        consultancy.name,\r\n" + 
				"        deal.status,\r\n" + 
				"        deal.open_date,\r\n" + 
				"        client.first_name,\r\n" + 
				"        client.last_name,\r\n" + 
				"        employee.first_name,\r\n" + 
				"        employee.last_name"+
				
				havingStatment+orderByStatment + limitStatment
				;
				
		logger.debug("\n\rSQLquery:"+SQLquery);
		
	}
	
	protected void prepareDealsDBTable() {
		
		
	}
	
	@Before
	public void initAllTestBase() {

		db.connectToDb();
	}

	@After

	public void deInitAllTestBase() {
		db.closeConnection();

	}
	
	
}
