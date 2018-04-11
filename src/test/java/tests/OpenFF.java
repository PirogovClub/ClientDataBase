package tests;


import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OpenFF {
	
	private static WebDriver driver;
	

	@Before
	public void setUp() {
		System.setProperty("webdriver.gecko.driver","C:\\SeleniumDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
	}
	
	@Test
	public void test() {
		utils.ReadConfigMain.SetCurrentParamName("openurl");
		driver.get(utils.ReadConfigMain.GetParamFromProperties());
	}
	
	@After
	
	public void tearDownClass(){
		driver.quit();
	}
	

}
