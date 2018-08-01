package dbObjects;


import java.util.Map;

public class ClientsDbOperations extends BaseDbOperations {
	
	public Map<String,String> getClientDataForCreatePage(String ClientId){
		
		
		SQLString="SELECT\r\n" + 
				"    client.id as \"Id\",\r\n" + 
				"    client.last_name as \"lastName\",\r\n" + 
				"    client.first_name as \"firstName\",\r\n" + 
				"    client.email as \"email\",\r\n" + 
				"    client.country as \"country\",\r\n" + 
				"    client.city as \"city\",\r\n" + 
				"    client.phone_number as \"phone\",\r\n" + 
				"    client.skype as \"skype\"\r\n" + 
				"FROM\r\n" + 
				"    client\r\n" + 
				"WHERE\r\n" + 
				"    client.id = " + ClientId;
		resultMap = db.executeQueryToMap(SQLString);
		return resultMap;
	}
	
	public Map<String,String> getClientDataForCreatePage(String ClientFirstName, String ClientLastName){
		SQLString="SELECT\r\n" + 
				"    client.id as \"Id\",\r\n" + 
				"    client.last_name as \"LastName\",\r\n" + 
				"    client.first_name as \"FirstName\",\r\n" + 
				"    client.email as \"Email\",\r\n" + 
				"    client.country as \"Country\",\r\n" + 
				"    client.city as \"City\",\r\n" + 
				"    client.phone_number as \"PhoneNumber\",\r\n" + 
				"    client.skype as \"Skype\"\r\n" + 
				"FROM\r\n" + 
				"    client\r\n" + 
				"WHERE\r\n" + 
				"    client.first_name = '" + ClientFirstName + "'" +
				"AND "+
				"    client.last_name = '" + ClientLastName + "'" ;
		resultMap = db.executeQueryToMap(SQLString);
		
		return resultMap;
	}

}
