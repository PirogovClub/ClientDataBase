package pageObjects;

import static org.junit.Assert.fail;

import org.apache.logging.log4j.message.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.TraceHelper;

public class MainNavigation extends BasePOM {
	
	String lookForElementOnLoad = "";
	By clientPageLookForElement;
	
	By clientLink = By.xpath(".//a[@href='/admin/clients']");
	By contractsMenu = By.xpath(".//span[contains(text(),'Договора')]");
	By invoicesMenu = By.xpath(".//span[contains(text(),'Счета')]");
	By invoicesAllMenu = By.xpath(".//a[@href='/admin/invoices']");
	
	By contractsMenuLookForElement = By.xpath(".//a[@href='/admin/contracts']");
	By contractsAllLookForElement = By.xpath(".//table[@class='table table-hover']");
	
			
	public MainNavigation(WebDriver driver){
		setDriver( driver);
		try {
			lookForElementOnLoad = ".//button[contains(text(),'Create Client')]";
			clientPageLookForElement = By.xpath(lookForElementOnLoad);
		} catch (Throwable e) {  
			logger.error("in "+this.getClass().getName()+" caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
	
	
	public void clickContractsMenu() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		Message m = logger.traceEntry(methodName);
		// ------------- body-----------
		logger.info("Clicking on to " + contractsMenu);
		driver.findElement(contractsMenu).click();
		waitForElement2BeVisible(contractsMenuLookForElement);

		// ------------ exit-----------
		logger.traceExit(methodName);
	}

	public void clickContractsAllMenu() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		Message m = logger.traceEntry(methodName);
		
		logger.info("Clicking on to " + contractsMenuLookForElement);
		// ------------- body-----------

		clickContractsMenu();
		driver.findElement(contractsMenuLookForElement).click();
		waitForElement2BeVisible(contractsAllLookForElement);
		
		// ------------ exit-----------
		logger.traceExit(methodName);
	}
	
	public void clickInvoicesMenu() {
		clickOnElement(invoicesMenu);

	}

	public void clickInvoicesAllMenu() {
		clickOnElement(invoicesAllMenu);

	}

	public void clickClient() {
		clickOnElement(clientLink);
		waitForElement2BeVisible(clientPageLookForElement);
	}

}
