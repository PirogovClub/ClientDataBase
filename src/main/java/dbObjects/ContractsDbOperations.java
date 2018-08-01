package dbObjects;


import java.util.Map;


public class ContractsDbOperations extends BaseDbOperations {
	
	
	public Map<String,String> selectClientName(String ClientName){
		
		SQLString="SELECT\r\n" + 
				"    deal.id,\r\n" + 
				"    CONCAT(client.last_name, ' ' , client.first_name) AS \"ClientName\",\r\n" + 
				"    consultancy.name                                  AS \"Consultancy\",\r\n" + 
				"    deal.status                                       AS \"Deal Status\",\r\n" + 
				"    CONCAT(deal.open_date, ' - ', deal.close_date\r\n" + 
				")    AS \"Start date - Close date\"\r\n" + 
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
				"GROUP BY\r\n" + 
				"    deal.id,\r\n" + 
				"    client.last_name,\r\n" + 
				"    client.first_name,\r\n" + 
				"    consultancy.name\r\n" + 
				"HAVING\r\n" + 
				"    (\r\n" + 
				"        LOWER(CONCAT(client.last_name, ' ' , client.first_name)) LIKE LOWER('"+ClientName+"'))\r\n";
		resultMap = db.executeQueryToMap(SQLString);
		return resultMap;
	}
	
	public Map<String,String> selectConsultancy(String ConsultancyName){
		SQLString="SELECT\r\n" + 
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
				"    ((consultancy.name) LIKE LOWER('"+ConsultancyName+"'))\r\n";
		resultMap = db.executeQueryToMap(SQLString);
		return resultMap;
	}
	
	public Map<String,String> selectDealStatus(String DealStatus){
		SQLString="SELECT\r\n" + 
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
				"AND LOWER(deal.status) LIKE LOWER('"+DealStatus+"')\r\n" + 
				"";
		resultMap = db.executeQueryToMap(SQLString);
		return resultMap;
	}

}
