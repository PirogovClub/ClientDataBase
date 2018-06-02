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

public class Consultancies {
	
	private  WebDriver driver;
	
	By NewConsultancy=By.xpath(".//a[@href='/admin/consultancies/new']");
	By Find=By.xpath(".//input[@placeholder='Поиск']");
	By pagenatorInfo=By.xpath("//span[@class='pagination-info']");
	By RefreshButton=By.xpath("//button[@title='Обновить']");
	By ConsultancyTitle = By.xpath(".//input[@id='name']");
	
	By ConsultancyDescription = By.xpath(".//textarea[@id='description']");
	By typePriceUAH = By.xpath(".//input[@id='prices[0].amount']");
	By PriceEUR = By.xpath(".//input[@id='prices[1].amount']");
	By PriceUSD = By.xpath(".//input[@id='prices[2].amount']");
	By EmployeeRate = By.xpath(".//input[@id='employeeRate.amount']");
	By saveButton = By.xpath(".//button[@type='submit'][contains(text(),'Save')]");
	By deleteButton= By.xpath(".//button[@type='button'][contains(text(),'Delete')]");
	By yesOnDeleteButton= By.xpath(".//button[@type='submit'][contains(text(),'Yes')]");
	
	private By toBeVisiablePageElement;
	private By targetExistingPageElement = By.xpath("//a[@class='btn btn-primary'][contains(text(),'consultancy')]");
	
	private Map<String, String> newConsultancyMap = new HashMap<String, String>();
	
	public By getConsultancyTitle() {
		return ConsultancyTitle;
	}
	
	private void setTargetExistingPageElement(By targetExistingPageElement) {
		this.targetExistingPageElement = targetExistingPageElement;
	}



	public By getTargetExistingPageElement() {
		return targetExistingPageElement;
	}
	
	

	public By getToBeVisiablePageElement() {
		return toBeVisiablePageElement;
	}

	public void setToBeVisiablePageElement(By toBeVisiablePageElement) {
		this.toBeVisiablePageElement = toBeVisiablePageElement;
	}

	private Boolean TypeIntoTextInput(WebElement webElement, String textToType, String traceText) {
		if (webElement.isDisplayed()) {
			webElement.sendKeys(textToType);
			System.out.println("Typed into "+ traceText +" field:"+textToType);
		} else {
			System.out.println(traceText+" field is not displayed");
		}
		return true;
	}
	
	public Consultancies(WebDriver driver) {
		this.driver = driver;
		
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
	
	public Consultancies clickHrefWithText(String hrefToClink) {
		By hrefToClinkOn = By.xpath(".//a[contains(text(),'"+hrefToClink+"')]");
		WebElement webElement = driver.findElement(hrefToClinkOn);
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
		waitModalDelete();
		clickYesOnDeleteModalButton();
		return this;
	}
	
	

	public Consultancies waitModalDelete() {
		setToBeVisiablePageElement(yesOnDeleteButton);
		WaitForModalOpen();
		return this;
	}

	private boolean findVisiblePageElement() {
		try {
			return !driver.findElement(toBeVisiablePageElement).isDisplayed();
		} catch (ElementNotVisibleException e) {
			return true;
		}

	}

	private void WaitForModalOpen() {
		while (findVisiblePageElement());
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
	
	
	
	
		


}
