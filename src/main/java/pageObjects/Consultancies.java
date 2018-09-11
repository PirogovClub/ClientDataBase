package pageObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Consultancies extends BasePOM {
	
	
	
	By NewConsultancy=By.xpath(".//a[@href='/admin/consultancies/new']");
	By Find=By.xpath(".//input[@placeholder='�����']");
	By pagenatorInfo=By.xpath("//span[@class='pagination-info']");
	By RefreshButton=By.xpath("//button[@title='��������']");
	String consultancyId = "input[@id='name']";
	By ConsultancyTitle = By.xpath(".//"+consultancyId);
	String consultancyDescriptionId = "textarea[@id='description']";
	By ConsultancyDescription = By.xpath(".//"+consultancyDescriptionId);
	By typePriceUAH = By.xpath(".//input[@id='prices[0].amount']");
	By PriceEUR = By.xpath(".//input[@id='prices[1].amount']");
	By PriceUSD = By.xpath(".//input[@id='prices[2].amount']");
	By EmployeeRate = By.xpath(".//input[@id='employeeRate.amount']");
	By saveButton = By.xpath(".//button[@type='submit'][contains(text(),'Save')]");
	By deleteButton= By.xpath(".//button[@type='button'][contains(text(),'Delete')]");
	By yesOnDeleteButton= By.xpath(".//button[@type='submit'][contains(text(),'Yes')]");
	
	
	private By targetExistingPageElement = By.xpath("//a[@class='btn btn-primary'][contains(text(),'consultancy')]");
	
	private Map<String, String> newConsultancyMap = new HashMap<String, String>();
	
	public By getConsultancyTitle() {
		return ConsultancyTitle;
	}
	
	public By getTargetExistingPageElement() {
		return targetExistingPageElement;
	}
	
	private Boolean TypeIntoTextInput(WebElement webElement, String textToType, String traceText) {
		if (webElement.isDisplayed()) {
			logger.info("Typing into "+ traceText +" field:"+textToType);
			webElement.sendKeys(textToType);
			
		} else {
			logger.error(traceText+" field is not displayed");
		}
		return true;
	}
	
	public Consultancies(WebDriver driver) {
		setDriver(driver);
		setTargetExistingPageElement(targetExistingPageElement);
		
	}
	
	public Consultancies createConsultancyButtonClick() {
		driver.findElement(NewConsultancy).click();
		
		return this;
	}
	
	public Consultancies typeConsultancyTitle(String textToType) {
		Boolean typed = TypeIntoTextInput(driver.findElement(ConsultancyTitle), textToType, "ConsultancyTitle");
		return this;
	}
	public Consultancies typeConsultancyDescription(String textToType) {
		Boolean typed = TypeIntoTextInput(driver.findElement(ConsultancyDescription), textToType, "ConsultancyDescription");
		return this;
	}
	public Consultancies typePriceUAH(String textToType) {
		Boolean typed = TypeIntoTextInput(driver.findElement(typePriceUAH), textToType, "typePriceUAH");
		return this;
	}
	
	public Consultancies typePriceEUR(String textToType) {
		Boolean typed = TypeIntoTextInput(driver.findElement(PriceEUR), textToType, "PriceEUR");
		return this;
	}
	public Consultancies typePriceUSD(String textToType) {
		Boolean typed = TypeIntoTextInput(driver.findElement(PriceUSD), textToType, "PriceUSD");
		return this;
	}
	public Consultancies typeEmployeeRate(String textToType) {
		Boolean typed = TypeIntoTextInput(driver.findElement(EmployeeRate), textToType, "EmployeeRate");
		return this;
	}
		
	public Consultancies submitSaveButton() {
		WebElement webElement = driver.findElement(saveButton);
		webElement.click();		
		return this;
	}
	
	
	public Consultancies clickdeleteButton() {
		
		WebElement webElement = driver.findElement(deleteButton);
		webElement.click();		
		return this;
	}
	public Consultancies clickYesOnDeleteModalButton() {
		
		WebElement webElement = driver.findElement(yesOnDeleteButton);
		webElement.click();		
		return this;
	}
	
	public Consultancies createNewConsultancy() {
		
		
		typeConsultancyTitle(newConsultancyMap.get("Title"));
		typeConsultancyDescription(newConsultancyMap.get("Description"));
		typePriceUAH(newConsultancyMap.get("PriceUAH"));
		typePriceEUR(newConsultancyMap.get("PriceEUR"));
		typePriceUSD(newConsultancyMap.get("USD"));
		typeEmployeeRate(newConsultancyMap.get("EmployeeRate"));
		submitSaveButton();
				
		return this;
	}
	
	public Consultancies deleteConsultancyRecord() {
		clickdeleteButton();
		waitModalWindow(yesOnDeleteButton);
		clickYesOnDeleteModalButton();
		return this;
	}
	
	public void setNewConsultancyParamiters(String Title, String Description, String PriceUAH, String PriceEUR, String USD, String EmployeeRate) {
		newConsultancyMap.put("Title", Title);
		newConsultancyMap.put("Description", Description);
		newConsultancyMap.put("PriceUAH", PriceUAH);
		newConsultancyMap.put("PriceEUR", PriceEUR);
		newConsultancyMap.put("USD", USD);
		newConsultancyMap.put("EmployeeRate", EmployeeRate);
	}

	public void clickTitleHref() {
		// TODO Auto-generated method stub
		clickHrefWithText(newConsultancyMap.get("Title"));
		
	}

	public boolean checkErrorTitleMessage(String string) {
		// TODO Auto-generated method stub
		return checkOutPutErrorMessage(string,consultancyId);
		
	}
	
	public boolean checkErrorDescriptionMessage(String string) {
		// TODO Auto-generated method stub
		return checkOutPutErrorMessage(string,consultancyDescriptionId);
		
	}

	public boolean checkOutPutErrorMessage(String string, String precedingBy) {
		// TODO Auto-generated method stub
		//размер должен быть между 2 и 50
		By outputText = By.xpath(".//output[contains(text(),'"+string+"') and preceding::"+precedingBy+"]");
		logger.info("Looking for Error Message:"+string+" in "+outputText);
		try {
			driver.findElement(outputText);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
		
		
	}

	
	
	
	
	
		


}
