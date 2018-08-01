package utils;



import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HtmlTable {
	
	
	protected WebDriver driver;
	protected Map<String,String> tableHeaderBaseOnCol = new HashMap<String,String>();
	protected Map<String,String> tableHeaderBaseOnValue = new HashMap<String,String>();
	protected Map<String,String> tableRow = new HashMap<String,String>();
	protected List<Map<String,String>> tableBody = new ArrayList<Map<String,String>>();
	 
	
	public List<Map<String,String>> getNamedTableBody() {
		// Return table body but with column names instead of column number
		List<Map<String,String>> tableBodyToReturn = new ArrayList<Map<String,String>>();
		Map<String,String> workingtableRow = new HashMap<String,String>();
		
		for(Map<String,String> mapElement : tableBody) {
			for (Map.Entry<String, String> pair : mapElement.entrySet()) {
				workingtableRow.put(tableHeaderBaseOnCol.get(pair.getKey()), pair.getValue());
			}
			tableBodyToReturn.add(new HashMap<String,String>(workingtableRow));
		}
		
		return tableBodyToReturn;
		
	}
	
	public List<Map<String, String>> getTableBody() {
		return tableBody;
	}

	protected void setTableBody(List<Map<String, String>> tableBody) {
		this.tableBody = tableBody;
	}

	public HtmlTable(By tablesToParse, WebDriver driver){
		this.driver = driver;
		Integer row_num,col_num;
		
		WebElement table_element = driver.findElement(tablesToParse);
		
		String tmpString= "./thead/tr/th";
		//System.out.println("tmpString = "+tmpString);
		List<WebElement> th_collection=table_element.findElements(By.xpath(tmpString));
		
		col_num=1;
		tableHeaderBaseOnCol.clear();
		tableHeaderBaseOnValue.clear();
		for(WebElement tdElement : th_collection)
        {
            this.tableHeaderBaseOnCol.put(col_num.toString(), tdElement.getText());
            this.tableHeaderBaseOnValue.put(tdElement.getText(), col_num.toString());
            //System.out.println(" col # "+col_num+ "header="+tdElement.getText());
            //System.out.println(" col # "+col_num+ "header="+tableHeader.get(col_num));
            col_num++;
        }
		
		tmpString= "./tbody/tr";
		//System.out.println("tmpString = "+tmpString);
        List<WebElement> tr_collection=table_element.findElements(By.xpath(tmpString));
        
        //System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
        row_num=1;
        tableBody.clear();
        for(WebElement trElement : tr_collection)
        {
        	
            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
            //System.out.println("NUMBER OF COLUMNS="+td_collection.size());
            col_num=1;
            tableRow.clear();
            for(WebElement tdElement : td_collection)
            {
            	tableRow.put(col_num.toString(), tdElement.getText());
            //    System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
                col_num++;
            }
            tableBody.add(new HashMap<String,String>(tableRow));
            //System.out.println(getRowString(row_num));
            row_num++;
        } 
        System.out.println("Read table "+tablesToParse.toString());
	}
	
	public int getRows() {
		return tableBody.size();
	}
	
	public int getCols() {
		return tableBody.get(0).size();
	}
	
	public String getCellValue(int col, int row) {
		String returnString="";
		returnString = tableBody.get(row-1).get(col);
		return returnString;
	}
	
	public String getCellValue(String col, int row) {
		String returnString="";
		
		returnString=tableBody.get((Integer)row-1).get(tableHeaderBaseOnValue.get(col));
		return returnString;
	}
	public String getRowString(int row) {
		String returnString="";
		for (int j=1;j<getCols()+1;j++) {
			returnString=returnString+getCellValue(j, row)+"|";
		}
		
		return returnString;
	}
	
	public void printTable() {
		
		PrintOuts.doMap(tableHeaderBaseOnCol);
		PrintOuts.doMap(tableHeaderBaseOnValue);
		PrintOuts.doListOfMap(tableBody);
		
	}
	
	public void renameHeader(String fromColName, String toColName) {
		PrintOuts.doString("Enter rename from:"+fromColName+" to:"+toColName);
		if(tableHeaderBaseOnValue.containsKey(fromColName)) {
			//PrintOuts.doString("Before Rename");
			//PrintOuts.doMap(tableHeaderBaseOnValue);
			//PrintOuts.doMap(tableHeaderBaseOnCol);
			
			tableHeaderBaseOnCol.put(tableHeaderBaseOnValue.get(fromColName), toColName);
			tableHeaderBaseOnValue.put(toColName, tableHeaderBaseOnValue.get(fromColName));
			tableHeaderBaseOnValue.remove(fromColName);
			//PrintOuts.doString("After Rename");
			//PrintOuts.doMap(tableHeaderBaseOnValue);
			//PrintOuts.doMap(tableHeaderBaseOnCol);
		}
	}
	
	public void convertDateCol(String colName, String toFormat) {
		printTable();
		for (Map<String,String> tableRow : tableBody) {
			PrintOuts.doString("Convert date from "+colName+": "+tableRow.get(tableHeaderBaseOnValue.get(colName)));
			String newDate = ConverDates.readDateFromNonRULocaleToString(tableRow.get(tableHeaderBaseOnValue.get(colName)),toFormat,Locale.ENGLISH);
			tableRow.put(tableHeaderBaseOnValue.get(colName),newDate);
			PrintOuts.doString("Converted date:"+tableRow.get(tableHeaderBaseOnValue.get(colName)));
		}
	}
	
	

}