package tests;


import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReadConfigMain;

public class OpenFF {
	
	private static WebDriver driver;
	private static ReadConfigMain config;

	@Before
	public void setUp() {
		System.setProperty("webdriver.gecko.driver","C:\\SeleniumDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		config = new ReadConfigMain();
	}
	
	@Test
	public void test() {
		config.SetCurrentParamName("openurl");
		driver.get(config.GetParamFromProperties());
	}
	
	@After
	
	public static void tearDownClass(){
		driver.quit();
	}
	

}
