package pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage {
	
	
	
	
	By loginFormLocator=By.xpath(".//form[@id='login-form']");
	By userNameFldLocator=By.xpath("//input[@id='username']");
	By paswFldLocator=By.xpath("//input[@id='password']");
	By submitButtonLocator=By.xpath("//input[@id='login-submit']");
	By submitForm = By.xpath("//form[@id='login-form']");
	By sideBarMenu= By.xpath("//ul[@class='sidebar-menu']");
	
	boolean thisIsLoginPage = false;
	
	private WebDriver driver;
	
	public LoginPage (WebDriver driver){
		this.driver = driver;
		this.thisIsLoginPage = this.NeedToLogin();
		if (!this.thisIsLoginPage) {
			throw new IllegalStateException("This is not the login page");
		}
	}
	
	public boolean isThisIsLoginPage() {
		return thisIsLoginPage;
	}
		
	// Set User Name into text Box
	public LoginPage typeUserName(String strUserName) {
		driver.findElement(userNameFldLocator).sendKeys(strUserName);
		return this;
	}
	
	// Set Password into text Box
	public LoginPage typePassword(String strUserPassword) {
		driver.findElement(paswFldLocator).sendKeys(strUserPassword);
		return this;

	}

	// PressSubmit
	public Consultancies pressSubmitButton() {
		driver.findElement(paswFldLocator).sendKeys(Keys.RETURN);
		WaitForLoad();
		return new Consultancies(driver);
	}
	
	//LoginAs
	
	public Consultancies loginAs(String strUserName, String strUserPassword) {
		typeUserName(strUserName);
		typePassword(strUserPassword);
		
		return pressSubmitButton();
	}
	
	private boolean FindNotLoginPageElement() {
		try {
			driver.findElement(sideBarMenu);
			return false;
		} catch (NoSuchElementException e ) {
			return true;
		}
			
	}
	
	public void WaitForLoad() {
		while (FindNotLoginPageElement());
	}
		
	// Check if this is login page by looking for login form
	public Boolean NeedToLogin() {

		List<WebElement> elems = driver.findElements(loginFormLocator);
		if (0 == elems.size()) {
			System.out.println("No login window on page");
			return false;
		} else {
			System.out.println("Found login form on the page, need to login");
			return true;
		}

	}
	

}
