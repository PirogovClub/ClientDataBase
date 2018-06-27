package tests;

import org.junit.Test;

import utils.DataBase;

public class DbTest {
	
	@Test
	public void CheckString() {
		DataBase db = new DataBase();
		db.connectToDb();
		String SQLquery = "";
		SQLquery = "Select \r\n" + 
				"       CONCAT(client.first_name, ' ', client.last_name ) as \"ClientName\",\r\n" + 
				"       deal.status as \"Status\"\r\n" + 
				"From\r\n" + 
				"        deal inner join client on deal.client_id=client.id\r\n" + 
				"Where \r\n" + 
				"        deal.status = 'ACTIVE'\r\n" + 
				"Order By random() limit 1;"; 
		System.out.println(db.executeQuery(SQLquery));
		
		SQLquery = "Select \r\n" + 
				"       CONCAT(employee.first_name, ' ', employee.last_name) as \"Mentor\",\r\n" + 
				"       employee.max_clients as \"Status\"\r\n" + 
				"From\r\n" + 
				"        employee\r\n" + 
				"Where \r\n" + 
				"        employee.max_clients >10\r\n" + 
				"Order By random() limit 1;"; 
		
		
		System.out.println(db.executeQuery(SQLquery));
		SQLquery = "select " + 
				"        " + 
				"        contract.contract_date as \"Contract Date\"," + 
				"        CONCAT(client.first_name, ' ', client.last_name ) as \"ClientName\"" + 
				"        " + 
				"From    " + 
				"        consultancy inner join (client inner join (employee inner join (Contract inner join deal " + 
				"        on (contract.deal_id = deal.id))" + 
				"        on (employee.id = contract.employee_id))" + 
				"        on (client.id = deal.client_id))" + 
				"        on (consultancy.id = deal.consultancy_id)" + 
				"Where" + 
				"        extract(month from contract.contract_date) BETWEEN 6 and 10" + 
				"Order by random() limit 1;"; 
		
		
		System.out.println(db.executeQuery(SQLquery));
		
		db.closeConnection();
	}
	
	

}
