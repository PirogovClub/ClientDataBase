package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.HtmlTable;

public class ContractsMain extends BasePOM {
	
	By tableWithContractsBy=By.xpath(".//table[@class='table table-hover']");
	By waitForElement = By.xpath("//input[@placeholder='Поиск']");
	By searchInput = By.xpath(".//input[@placeholder='Поиск']");
	By showWhileDataTableIsLoading= By.xpath("//div[@class='fixed-table-loading']");
	HtmlTable tableWithContracts;
	
	public ContractsMain (WebDriver driver){
		this.driver = driver;
	}
	
	public HtmlTable readTableWithContracts() {
		WaitForLoad(waitForElement);
		this.tableWithContracts = new  HtmlTable(tableWithContractsBy,driver);
		
		return tableWithContracts;
	}
	
	public void typeInSearchBox(String stringToType) {
		System.out.println("typing" );
		setTextToTestFieldAndWait(searchInput,stringToType,"Search Input",1);
	}
	
	public void searchForString(String searchString) {
		// TODO Auto-generated method stub
		System.out.println("looking for " +searchString+" \r\n" );
		WaitForLoad(waitForElement);
		typeInSearchBox(searchString);
		
		waitForElementToHide(showWhileDataTableIsLoading);
		
		
		
	}

	
	
	

}
