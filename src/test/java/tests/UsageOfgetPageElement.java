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
private Consultancies page;
	
public Consultancies getPage() {
	return page;
}

public void setPage(Consultancies page) {
	this.page = page;
	System.out.println("Page loaded");
}

	@Before
	public void setUp() {
		System.setProperty("webdriver.gecko.driver","src/main/java/resources/geckodriver.exe");
		driver = new FirefoxDriver();
		
	}
	
	@Test
	public void test() {
		try { 
			//open page
			driver.get(utils.ReadConfigMain.getValueFromProperty("openurl"));
			LoginPage loginPage = new LoginPage(driver);
			setPage(loginPage.loginAs("admin", "admin"));
			page.GetToPage(utils.ReadConfigMain.getValueFromProperty("openurl"));
			System.out.println("==End tracing====");
			System.out.println("==Printing task==");
			System.out.println(page.GetPagenatorInfoValue(0));
			System.out.println(page.GetConsultancyWorkloadValue(0));
			System.out.println(page.GetConsultancyNameValue(0));
			
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
	
	@After
	 
	public void tearDownClass(){
		driver.quit();
	}

	
	
	

}
