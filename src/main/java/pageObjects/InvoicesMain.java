package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.DataTable;
import utils.HtmlTable;

public class InvoicesMain extends BasePOM{
	
	By tableWithInvoicesBy=By.xpath(".//table[@class='table table-hover']");
	By searchInput = By.xpath(".//input[@placeholder='Поиск']");
	By waitForElement = By.xpath("//input[@placeholder='Поиск']");
	By showWhileDataTableIsLoading= By.xpath("//div[@class='fixed-table-loading']");
	
	HtmlTable tableWithContracts;
	
	public InvoicesMain (WebDriver driver){
		this.driver = driver;
		
	}
	
	public HtmlTable readTableWithInvoces() {
		WaitForLoad(waitForElement);
		logger.info("Reading table from page");
		this.tableWithContracts = new  HtmlTable(tableWithInvoicesBy,driver);
		
		return tableWithContracts;
	}
	
	public void typeInSearchBox(String stringToType) {
		//logger.info("Typing "+stringToType + " into " + searchInput);
		setTextToTestFieldAndWait(searchInput,stringToType,"Search Input",1);
	}
	
	public void searchForString(String searchString) {
		// TODO Auto-generated method stub
		logger.trace("looking for " +searchString+" \r\n" );
		WaitForLoad(waitForElement);
		typeInSearchBox(searchString);
		
		waitForElementToHide(showWhileDataTableIsLoading);
		
		
	
	}
	
	

}
