package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebCommonActions {
	
		
	public void setTextToTestField(WebDriver driver, By fieldLocator, String textToSet, String fildShortNameToTrace)  {
		WebElement webElement = driver.findElement(fieldLocator);
		
		if (webElement.isDisplayed()) {
			webElement.sendKeys(textToSet);
		} else {
			System.out.println(fildShortNameToTrace+" field is not displayed");
		}
		
	}

}
