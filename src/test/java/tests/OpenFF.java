package tests;


import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import utils.WorkWithMainConfig;


public class OpenFF {
	
	private static WebDriver driver;
	private WorkWithMainConfig config = new WorkWithMainConfig();
	protected static Logger logger = LogManager.getLogger();

	@BeforeEach
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
			logger.error("in "+this.getClass().getName()+" caught" + e); 
		} 
	}
	
	@AfterEach
	 
	public void tearDownClass(){
		driver.quit();
	}
	

}
