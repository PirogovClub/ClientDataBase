package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Clients {
	WebDriver driver;
	
	By createClientButton = By.xpath(".//button[@type='button'][contains(text(),'Create Client')]");
	By FindBtn=By.xpath(".//input[@placeholder='Поиск']");
	By RefreshBtn=By.xpath(".//i[@class='glyphicon glyphicon-refresh icon-refresh']");
	By createClientFirstNameField = By.xpath("//input[@id='firstName']");
	By createClientLastNameField = By.xpath("//input[@id='lastName']");
	By saveNewClientName = By.xpath(".//button[@type='submit'][contains(text(),'save')]");
	
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
	
	public Clients typeNewClientName(String newClientName) {
		WebElement webElement = driver.findElement(createClientFirstNameField);
		if (webElement.isDisplayed()) {
			webElement.sendKeys(newClientName);
		} else {
			System.out.println("First Name field is not displayed");
		}
		
		return this;
	}
	
	public Clients typeNewClientLastName(String newClientName) {
		WebElement webElement = driver.findElement(createClientLastNameField);
		if (webElement.isDisplayed()) {
			webElement.sendKeys(newClientName);
		} else {
			System.out.println("Last Name field is not displayed");
		}
		
		return this;
	}
	

}
