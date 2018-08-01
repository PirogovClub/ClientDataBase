package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataTable {
	
	protected List<Map<String,String>> tableBody = new ArrayList<Map<String,String>>();
	
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
			    if (!value.equals((String) other.tableBody.get(index).get(key))) {
			    	System.out.println("Original");
			    	System.out.println("For key "+ key +" result is "+ value);
			    	System.out.println("Equal to");
			    	System.out.println("For key "+ key +" result is "+ other.tableBody.get(index).get(key));
			    	
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
	}
	
	public void printTable() {
		PrintOuts printOut = new PrintOuts();
		printOut.doListOfMap(tableBody);
		
	}
	
}
