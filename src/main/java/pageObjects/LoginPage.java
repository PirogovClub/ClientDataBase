package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePOM {

	By loginFormLocator = By.xpath(".//form[@id='login-form']");
	By userNameFldLocator = By.xpath("//input[@id='username']");
	By paswFldLocator = By.xpath("//input[@id='password']");
	By submitButtonLocator = By.xpath("//input[@id='login-submit']");
	By submitForm = By.xpath("//form[@id='login-form']");
	By sideBarMenu = By.xpath("//ul[@class='sidebar-menu']");
	By registerHref = By.xpath(".//a[@id='register-form-link']");
	By registerName = By.xpath(".//input[@id='register-username']");
	By registerEmail = By.xpath(".//input[@id='email']");
	By registerPassword = By.xpath(".//input[@id='register-password']");
	By registerSubmit = By.xpath(".//input[@id='register-submit']");
	By registerFailNotification2 = By.xpath(".//*[contains(text(),'Please include')]");
	By registerFailNotification = By.xpath(".//*[contains(text(),'Пожалуйста')]");

	private String pageUrl;// describe what is address for this page

	boolean thisIsLoginPage = false;

	

	public LoginPage(WebDriver driver, String PageUrl) {
		this.driver = driver;
		setPageUrl(PageUrl);
		openPageUrl();
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
	public LoginPage pressSubmitButton() {
		driver.findElement(paswFldLocator).sendKeys(Keys.RETURN);
		logger.info("pressed Submit");
		WaitForLoad();
		return this;
	}

	// LoginAs

	public LoginPage loginAs(String strUserName, String strUserPassword) {
		logger.info("logining as " + strUserName + "");
		typeUserName(strUserName);
		typePassword(strUserPassword);

		return pressSubmitButton();
	}

	private boolean FindNotLoginPageElement() {
		try {
			driver.findElement(sideBarMenu);
			return false;
		} catch (NoSuchElementException e) {
			return true;
		}

	}

	public void WaitForLoad() {
		while (FindNotLoginPageElement())
			;
	}

	// Check if this is login page by looking for login form
	public Boolean NeedToLogin() {

		List<WebElement> elems = driver.findElements(loginFormLocator);
		if (0 == elems.size()) {
			logger.error("No login window on page");
			return false;
		} else {
			logger.info("Found login form on the page, need to login");
			return true;
		}

	}

	/**
	 * @return the pageUrl
	 */
	public String getPageUrl() {
		return pageUrl;
	}

	/**
	 * @param pageUrl
	 *            the pageUrl to set
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	private void openPageUrl() {
		this.driver.get(pageUrl);
	}

	public void clickRegister() {
		// TODO Auto-generated method stub
		this.clickOnElement(registerHref);
	}

	public void enterRegisterUserName(String string) {
		// TODO Auto-generated method stub
		this.setTextToTestField(registerName, string, "UserName");
		
	}

	public void enterRegisterUserEmail(String string) {
		// TODO Auto-generated method stub
		this.setTextToTestField(registerEmail, string,"UserEmail");
	}

	public void enterRegisterUserPassword(String string) {
		// TODO Auto-generated method stub
		this.setTextToTestField(registerPassword, string, "UserEmail");
	}

	public void clickRegisterButton() {
		// TODO Auto-generated method stub
		this.clickOnElement(registerSubmit);
	}

	public boolean checkErrorEmailValidationMessage(String string) {
		// TODO Auto-generated method stub
		logger.info("Check if validation message contain: " +string);
		try {
			if(driver.findElement(registerEmail).getAttribute("validationMessage").contains(string)) {
				return true;
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
		
	}

}
