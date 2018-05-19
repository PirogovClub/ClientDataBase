package tests;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObjects.LoginPage;


public class BaseTest {

	protected static WebDriver driver;
	

	protected static void openUrl(String pageUrl) {
		driver.get(pageUrl);
	}
	

	@Before
	//We will open broweser and pass login page to be able to switch to any page we need
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
