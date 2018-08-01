package pageObjects;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainNavigation extends BasePOM {
	
	String lookForElementOnLoad = "";
	By clientPageLookForElement;
	
	By clientLink = By.xpath(".//a[@href='/admin/clients']");
	By contractsMenu = By.xpath(".//span[contains(text(),'Договора')]");
	By contractsMenuLookForElement = By.xpath(".//a[@href='/admin/contracts']");
	By contractsAllLookForElement = By.xpath(".//table[@class='table table-hover']");
			
	public MainNavigation(WebDriver driver){
		setDriver( driver);
		try {
			lookForElementOnLoad = ".//button[contains(text(),'Create Client')]";
			clientPageLookForElement = By.xpath(lookForElementOnLoad);
		} catch (Throwable e) {  
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
	
	
	public void clickContractsMenu() {
		System.out.println("click ContractsMenu begin");
		driver.findElement(contractsMenu).click();
		WaitForLoad(contractsMenuLookForElement);
		System.out.println("click ContractsMenu end");
	}
	
	public void clickContractsAllMenu() {
		System.out.println("click ContractsMenuAll begin");
		clickContractsMenu();
		
		driver.findElement(contractsMenuLookForElement).click();
		WaitForLoad(contractsAllLookForElement);
		System.out.println("click ContractsMenuAll end");
	}
	
	
	public void clickClient() {
		System.out.println("click Client begin");
		driver.findElement(clientLink).click();
		WaitForLoad(clientPageLookForElement);
		System.out.println("click Client end");
	}
	

}
