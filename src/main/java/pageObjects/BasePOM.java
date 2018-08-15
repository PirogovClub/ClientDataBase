package pageObjects;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.JSWaiter;

public class BasePOM {
	protected  WebDriver driver;
	protected static Logger logger = LogManager.getLogger();
	private By toBeVisiablePageElement;
	private By targetExistingPageElement;
	
	public By getTargetExistingPageElement() {
		return targetExistingPageElement;
	}

	protected void setTargetExistingPageElement(By targetExistingPageElement) {
		this.targetExistingPageElement = targetExistingPageElement;
	}

	protected WebDriver getDriver() {
		return driver;
	}

	protected void setDriver(WebDriver driver) {
		this.driver = driver;
		
	}

	
	public By getToBeVisiablePageElement() {
		return toBeVisiablePageElement;
	}

	public void setToBeVisiablePageElement(By toBeVisiablePageElement) {
		this.toBeVisiablePageElement = toBeVisiablePageElement;
	}

	public void waitModalWindow(By waitForElement) {
		setToBeVisiablePageElement(waitForElement);
		logger.trace("Wait for" + waitForElement);
		waitForModalOpen();
		
	}

	private boolean findVisiblePageElement() {
		try {
			return !driver.findElement(toBeVisiablePageElement).isDisplayed();
		} catch (ElementNotVisibleException e) {
			return true;
		}

	}
	

	private void waitForModalOpen() {
		while (findVisiblePageElement());
	}
	
	private void waitForElementHidden() {
		while (!findVisiblePageElement());
	}
	
	public void setTextToTestField(By fieldLocator, String textToSet, String fildShortNameToTrace) {
		setTextToTestField(fieldLocator,textToSet, fildShortNameToTrace, true,0 );
	}
	
	public void setTextToTestFieldAndWait(By fieldLocator, String textToSet, String fildShortNameToTrace, Integer waitForSecAfterInput) {
		setTextToTestField(fieldLocator,textToSet, fildShortNameToTrace, true, waitForSecAfterInput*1000 );
	}
	
	public void setTextToTestField(By fieldLocator, String textToSet, String fildShortNameToTrace, boolean Overwrite, Integer waitForMiliSecAfterInput  )  {
		logger.trace("waitForSecAfterInput:"+waitForMiliSecAfterInput);
		if (driver.findElement(fieldLocator).isDisplayed()) {
			
			if (Overwrite) {
				driver.findElement(fieldLocator).clear();
			}
			driver.findElement(fieldLocator).sendKeys(textToSet);
			JSWaiter.setDriver(driver);
			JSWaiter.waitJQueryAngular();
		} else {
			System.out.println(fildShortNameToTrace+" field is not displayed");
		}
		
		if (waitForMiliSecAfterInput>0) {
			JSWaiter.sleep(waitForMiliSecAfterInput);
		}
		
	}
	
	public void waitForElementToHide(By waitForElement) {
		setToBeVisiablePageElement(waitForElement);
		waitForElementHidden();
		
	}
	
	public boolean checkIfElementNotDisplayed(By elementToCheck) {
		try {
			return !driver.findElement(elementToCheck).isDisplayed();
		} catch (ElementNotVisibleException e) {
			return true;
		}
		
	}
	
	public void clickHrefWithText(String hrefToClink) {
		By hrefToClinkOn = By.xpath(".//a[contains(text(),'"+hrefToClink+"')]");
		logger.info("Wait For " + hrefToClinkOn );
		WaitForLoad(hrefToClinkOn);
		if (driver.findElement(hrefToClinkOn).isDisplayed()) {
			driver.findElement(hrefToClinkOn).click();
		} else {
			logger.debug(hrefToClinkOn+" field is not displayed");
		}
	}
	
	public void clickHrefWithLink(String hrefToClink) {
		By hrefToClinkOn = By.xpath(".//a[@href='"+hrefToClink+"']");
		logger.info("Wait For " + hrefToClinkOn );
		WaitForLoad(hrefToClinkOn);
		if (driver.findElement(hrefToClinkOn).isDisplayed()) {
			driver.findElement(hrefToClinkOn).click();
		} else {
			logger.debug(hrefToClinkOn+" field is not displayed");
		}
	}
	
	
	public void WaitForLoad(By targetExistingPageElement) {
		logger.info("Wait For " + targetExistingPageElement );
		setTargetExistingPageElement(targetExistingPageElement);
		while (FindPageElement());
	}
	
	protected void WaitForLoad() {
		while (FindPageElement());
	}
	
	private boolean FindPageElement() {
		try {
			driver.findElement(targetExistingPageElement);
			return false;
		} catch (NoSuchElementException e ) {
			return true;
		}
			
	}
	
	
	
	
	

}
