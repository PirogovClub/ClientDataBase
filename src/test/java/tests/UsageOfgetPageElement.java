package tests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.Consultancies;
import pageObjects.LoginPage;


public class UsageOfgetPageElement {
	
private static WebDriver driver;

private String consultancyUrl;
	

	@Before
	public void setUp() {
		System.setProperty("webdriver.gecko.driver","src/main/java/resources/geckodriver.exe");
		driver = new FirefoxDriver();
		
	}
	
	@Test
	public void test() {
		try { 
			//open page
			setConsultancyUrl(utils.ReadConfigMain.getValueFromProperty("consultancyUrl"));
			
			//Check if this is login page, by default we should get to login page
			LoginPage loginPage = new LoginPage(driver,this.consultancyUrl);
			
			//Check if we need to login
			if (loginPage.isThisIsLoginPage()) {
				//if this is login page login and get to Dashboard
				loginPage.loginAs("admin", "admin");
			}
			//after login page we as left on dashboard
			//Now lets go to needed page
			Consultancies consultancies = new Consultancies(driver); 			
			consultancies.GetToPage(this.consultancyUrl);
			
			System.out.println("==End tracing====");
			System.out.println("==Printing task==");
			System.out.println(consultancies.GetPagenatorInfoValue(0));
			System.out.println(consultancies.GetConsultancyWorkloadValue(0));
			System.out.println(consultancies.GetConsultancyNameValue(0));
			
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
	
	@After
	 
	public void tearDownClass(){
		driver.quit();
	}

	/**
	 * @return the openUrl
	 */
	public String getConsultancyUrl() {
		return consultancyUrl;
	}

	/**
	 * @param openUrl the openUrl to set
	 */
	public void setConsultancyUrl(String openUrl) {
		this.consultancyUrl = openUrl;
	}

	
	
	

}
