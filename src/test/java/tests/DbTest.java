package tests;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

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
		System.out.println(db.executeQueryToString(SQLquery));
		
		SQLquery = "Select \r\n" + 
				"       CONCAT(employee.first_name, ' ', employee.last_name) as \"Mentor\",\r\n" + 
				"       employee.max_clients as \"Status\"\r\n" + 
				"From\r\n" + 
				"        employee\r\n" + 
				"Where \r\n" + 
				"        employee.max_clients >10\r\n" + 
				"Order By random() limit 1;"; 
		
		
		System.out.println(db.executeQueryToString(SQLquery));
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
		
		
		System.out.println(db.executeQueryToString(SQLquery));
		
		SQLquery = "SELECT " + 
				"    deal.id, " + 
				"    CONCAT(client.last_name, ' ' , client.first_name) AS \"ClientName\", " + 
				"    consultancy.name                                  AS \"Consultancy\", " + 
				"    deal.status                                       AS \"Deal Status\", " + 
				"    CONCAT(deal.open_date, ' - ', deal.close_date " + 
				")    AS \"Start date - Close date\" " + 
				"FROM " + 
				"    consultancy " + 
				"INNER JOIN " + 
				"    ( client " + 
				"INNER JOIN " + 
				"    deal " + 
				"ON " + 
				"    deal.client_id = client.id ) " + 
				"ON " + 
				"    deal.consultancy_id = consultancy.id " + 
				"GROUP BY " + 
				"    deal.id, " + 
				"    client.last_name, " + 
				"    client.first_name, " + 
				"    consultancy.name " + 
				"HAVING " + 
				"    ( " + 
				"        LOWER(CONCAT(client.last_name, ' ' , client.first_name)) LIKE LOWER('%rasp%')) " + 
				"";
		
		/*List<String> testList = new ArrayList<String>();
		testList = db.executeQueryToList(SQLquery);
		for(String s:testList){  
			System.out.println(s);  
			}
		*/
		
		SQLquery = "SELECT\r\n" + 
				"    deal.id,\r\n" + 
				"    CONCAT(client.last_name, ' ' , client.first_name) AS \"ClientName\",\r\n" + 
				"    consultancy.name                                  AS \"Consultancy\",\r\n" + 
				"    deal.status                                       AS \"Deal Status\",\r\n" + 
				"    CONCAT(deal.open_date, ' - ', deal.close_date)    AS \"Start date - Close date\"\r\n" + 
				"FROM\r\n" + 
				"    consultancy\r\n" + 
				"INNER JOIN\r\n" + 
				"    ( client\r\n" + 
				"INNER JOIN\r\n" + 
				"    deal\r\n" + 
				"ON\r\n" + 
				"    deal.client_id = client.id )\r\n" + 
				"ON\r\n" + 
				"    deal.consultancy_id = consultancy.id\r\n" + 
				"WHERE\r\n" + 
				"    deal.id > 0\r\n" + 
				"GROUP BY\r\n" + 
				"    deal.id,\r\n" + 
				"    client.last_name,\r\n" + 
				"    client.first_name,\r\n" + 
				"    consultancy.name\r\n" + 
				"HAVING\r\n" + 
				"        LOWER(CONCAT(deal.open_date, ' - ', deal.close_date)) LIKE LOWER('%2018%')\r\n" + 
				"Limit 1\r\n" + 
				"";
		
		Map<String,String> testMap = new HashMap<String,String>();
		testMap = db.executeQueryToMap(SQLquery);
		System.out.println(testMap.get("ClientName") + ":"+testMap.get("Deal Status"));  
		
		
		db.closeConnection();
	}
	
	

}
