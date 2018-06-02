package pageObjects;

import java.util.HashMap;
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
	
	private Map<String,String> newClientMap = new HashMap<String,String>();
	
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
	
	public void setNewClientParamiters(String firstName, String lastName, String email, String country, String city, String phone, String skype) {
		newClientMap.put("FirstName",firstName);
		newClientMap.put("lastName",lastName);
		newClientMap.put("email",email);
		newClientMap.put("country",country);
		newClientMap.put("city",city);
		newClientMap.put("phone",phone);
		newClientMap.put("skype",skype);
	}
	
	public Clients createNewClient() {
		createClientButtonClick();
		webCommonActions.setTextToTestField(driver, createClientFirstNameField, newClientMap.get("FirstName"), "First Name");
		webCommonActions.setTextToTestField(driver, createClientLastNameField, newClientMap.get("lastName"), "Last Name");
		saveNewClientNameClick();
		return this;
	}
	

}
