package pageObjects;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainNavigation extends BasePOM {
	
	String lookForElementOnLoad = "";
	By clientPageLookForElement;
	
	By clientLink = By.xpath(".//a[@href='/admin/clients']");
	 
			
	public MainNavigation(WebDriver driver){
		setDriver( driver);
		try {
			lookForElementOnLoad = utils.ReadConfigMain.getValueFromProperty("clientPageLookForElement");
			clientPageLookForElement = By.xpath(lookForElementOnLoad);
		} catch (Throwable e) {  
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
	
	public void clickClient() {
		driver.findElement(clientLink).click();
		WaitForLoad(clientPageLookForElement);
	}

}
