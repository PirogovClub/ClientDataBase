package pageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.WebCommonActions;


public class Clients {
	WebDriver driver;
	
	By createClientButton = By.xpath(".//button[contains(text(),'Create Client')]");
	By FindBtn=By.xpath(".//input[@placeholder='Поиск']");
	By RefreshBtn=By.name("refresh");
	By createClientFirstNameField = By.xpath("//input[@id='firstName']");
	By createClientLastNameField = By.xpath("//input[@id='lastName']");
	By saveNewClientName = By.xpath(".//button[@type='submit'][contains(text(),'save')]");
	private By targetExistingPageElement = By.xpath(".//button[@type='submit'][contains(text(),'save')]");
	private WebCommonActions webCommonActions= new WebCommonActions();
	
	public By getTargetExistingPageElement() {
		return targetExistingPageElement;
	}

	public Clients(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public Clients createClientButtonClick() {
		driver.findElement(createClientButton).click();
		
		return this;
	}
	
	public Clients saveNewClientNameClick() {
		driver.findElement(saveNewClientName).click();
		
		return this;
	}
	
	public Clients createNewClient(Map<String,String> newClientMap) {
		createClientButtonClick();
		webCommonActions.setTextToTestField(driver, createClientFirstNameField, newClientMap.get("clientsTestFirstName"), "First Name");
		webCommonActions.setTextToTestField(driver, createClientLastNameField, newClientMap.get("clientsTestSecondName"), "Last Name");
		saveNewClientNameClick();
		return this;
	}
	

}
