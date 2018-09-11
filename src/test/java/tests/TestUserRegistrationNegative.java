package tests;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObjects.LoginPage;
import utils.AssertWarp;

public class TestUserRegistrationNegative extends BaseTest {
	
	@Override
	@Before
	
	public void startBrowser() throws Exception{
		logger.info("--------------------Strarting Test------------");
		System.setProperty("log4j2.debug","INFO");
		
		String Browser=config.getConfigProp("browserToTest");
		String TestLoginUrl=config.getConfigProp("TestLoginUrl");
		String SiteLogin=config.getConfigProp("SiteLogin");
		String SitePass=config.getConfigProp("SitePass");
		logger.info("Starting browser");
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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Check if this is login page, by default we should get to login page
		
		
		//Check if we need to login
		/*
		if (loginPage.isThisIsLoginPage()) {
			//if this is login page login and get to Dashboard
			loginPage.loginAs(SiteLogin, SitePass);
		}
		*/
	}
	
	@Test
	
	public void runtest() {
		String TestLoginUrl=config.getConfigProp("TestLoginUrl");
		LoginPage loginPage = new LoginPage(driver,TestLoginUrl);
		loginPage.clickRegister();
		loginPage.enterRegisterUserName("username");
		loginPage.enterRegisterUserEmail("admin");
		loginPage.enterRegisterUserPassword("password");
		loginPage.clickRegisterButton();
	
		boolean isTestPassed = true;
		isTestPassed = isTestPassed & loginPage.checkErrorEmailValidationMessage("Please include an '@' in the email address");
		AssertWarp.assertToLog(isTestPassed);
		
	}

}
