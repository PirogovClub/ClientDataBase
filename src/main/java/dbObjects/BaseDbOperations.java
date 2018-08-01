package dbObjects;

import java.util.HashMap;
import java.util.Map;

import utils.DataBase;

public class BaseDbOperations {
	protected DataBase db = new DataBase();
	protected Map<String,String> resultMap = new HashMap<String,String>();
	protected String SQLString;
	
	
	public BaseDbOperations() {
		db.connectToDb();
	}
	
	public void closeDbConnection(){
		db.closeConnection();
	}
}
