package tests;


import java.io.IOException;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WorkWithMainConfig;


public class OpenFF {
	
	private static WebDriver driver;
	private WorkWithMainConfig config = new WorkWithMainConfig();
	

	@Before
	public void setUp() {
		System.setProperty("webdriver.gecko.driver","src/main/java/resources/geckodriver.exe");
		driver = new FirefoxDriver();
		
	}
	
	@Test
	public void test() {
		try { 
			//comment on this
			driver.get(config.getConfigProp("openurl")); 
		} catch (Throwable e) { 
	          System.out.println("caught" + e); 
		} 
	}
	
	@After
	 
	public void tearDownClass(){
		driver.quit();
	}
	

}
