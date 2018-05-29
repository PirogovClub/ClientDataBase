package tests;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObjects.LoginPage;



public class BaseTest {

	protected static WebDriver driver;
	
	protected String targetPageUrl;
	protected String targetPageNameToTrace;
	protected By targetExistingPageElement;
	
	public By getTargetExistingPageElement() {
		return targetExistingPageElement;
	}

	public void setTargetExistingPageElement(By targetExistingPageElement) {
		this.targetExistingPageElement = targetExistingPageElement;
	}
	
	public String getTargetPageUrl() {
		return targetPageUrl;
	}

	public void setTargetPageUrl(String targetPageUrl) {
		this.targetPageUrl = targetPageUrl;
	}

	public String getTargetPageNameToTrace() {
		return targetPageNameToTrace;
	}

	public void setTargetPageNameToTrace(String targetPageNameToTrace) {
		this.targetPageNameToTrace = targetPageNameToTrace;
	}
	

	protected static void openUrl(String pageUrl) {
		driver.get(pageUrl);
	}
	
	public void GetToPage(String targetPageUrl) {
		driver.get(targetPageUrl);
		System.out.println("Get To "+targetPageNameToTrace+", wating for load");
		WaitForLoad();
		System.out.println("loaded");
	}
	
	private boolean FindPageElement() {
		try {
			driver.findElement(targetExistingPageElement);
			return false;
		} catch (NoSuchElementException e ) {
			return true;
		}
			
	}
	
	public void WaitForLoad(By targetExistingPageElement) {
		setTargetExistingPageElement(targetExistingPageElement);
		while (FindPageElement());
	}
	
	public void WaitForLoad() {
		while (FindPageElement());
	}
	

	@Before
	//We will open browser and pass login page to be able to switch to any page we need
	public void startBrowser() throws Exception{
		String Browser=utils.ReadConfigMain.getValueFromProperty("browserToTest");
		String TestLoginUrl=utils.ReadConfigMain.getValueFromProperty("TestLoginUrl");
		String SiteLogin=utils.ReadConfigMain.getValueFromProperty("SiteLogin");
		String SitePass=utils.ReadConfigMain.getValueFromProperty("SitePass");
		switch (Browser) {
		case "Chrome":
			System.setProperty("webdriver.gecko.driver","src/main/java/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "Firefox":
			System.setProperty("webdriver.gecko.driver","src/main/java/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
		
		//Check if this is login page, by default we should get to login page
		LoginPage loginPage = new LoginPage(driver,TestLoginUrl);
		
		//Check if we need to login
		if (loginPage.isThisIsLoginPage()) {
			//if this is login page login and get to Dashboard
			loginPage.loginAs(SiteLogin, SitePass);
		}
	}
	
	
	
	@After
	public void quitBrowser(){
		driver.quit();
	}
}
