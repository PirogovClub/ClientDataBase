package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataTable {
	
	protected List<Map<String,String>> tableBody = new ArrayList<Map<String,String>>();
	protected static Logger logger = LogManager.getLogger();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tableBody == null) ? 0 : tableBody.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		//Sorting is SIGNIFICANT taken into account
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataTable other = (DataTable) obj;
		if (tableBody == null) {
			if (other.tableBody != null)
				return false;
		}
		if (tableBody.size() != other.tableBody.size()) 
			return false;
		Map<String,String> tblRow;
		for (int index=0;index<tableBody.size();index++) {
			tblRow =  tableBody.get(index);
			for (Map.Entry<String, String> entry : tblRow.entrySet()) {
				String key = (String) entry.getKey();
			    String value = (String) entry.getValue();
			    PrintOuts.doString("key:"+key);
			    PrintOuts.doString("value:"+value);
			    PrintOuts.doString(other.tableBody.get(index).get(key));
			    if (!value.equals((String) other.tableBody.get(index).get(key))) {
			    	logger.debug("Original");
			    	logger.debug("For key "+ key +" result is "+ value);
			    	logger.debug("Equal to");
			    	logger.debug("For key "+ key +" result is "+ other.tableBody.get(index).get(key));
			    	
			    	return false;
			    }
			    	
			}
		}
		
		
		return true;
	}

	

	public List<Map<String, String>> getTableBody() {
		return tableBody;
	}

	public void setTableBody(List<Map<String, String>> tableBody) {
		this.tableBody = tableBody;
		for (Map<String, String> tblRow : tableBody) {
			for (Map.Entry<String, String> entry : tblRow.entrySet()) {
				if (entry.getValue() == null) {
					entry.setValue("not specified");
				}
			}
		}
	}

	
	public void printTable() {
		
		PrintOuts.doListOfMap(tableBody);
	}
	
	public void renameKeys(String fromColName, String toColName) {
		PrintOuts.doString("Enter rename from:" + fromColName + " to:" + toColName);
		for (Map<String, String> row : tableBody) {
			if (row.containsKey(fromColName)) {
				// PrintOuts.doString("Before Rename");
				// PrintOuts.doMap(tableHeaderBaseOnValue);
				// PrintOuts.doMap(tableHeaderBaseOnCol);
				row.put(toColName, row.get(fromColName));
				row.remove(fromColName);
				// PrintOuts.doString("After Rename");
				// PrintOuts.doMap(tableHeaderBaseOnValue);
				// PrintOuts.doMap(tableHeaderBaseOnCol);
			}
		}
	}
	
	public void removeCol(String ColName) {
		for (Map<String, String> row : tableBody) {
			if (row.containsKey(ColName)) {
				row.remove(ColName);
			}
		}
	}
	
}
