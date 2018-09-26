package tests;


import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObjects.LoginPage;
import utils.AssertWrapper;
import utils.ScreenShots;
import utils.WorkWithMainConfig;



public class BaseTest {
	protected boolean isTestPassed = true;
	protected static WebDriver driver;
	protected static Logger logger = LogManager.getLogger();
	protected String targetPageUrl;
	protected String targetPageNameToTrace;
	protected By targetExistingPageElement;
	protected String chieldTestModuleName = "";
	protected WorkWithMainConfig config = new WorkWithMainConfig();
	
	public String getChieldTestModuleName() {
		return chieldTestModuleName;
	}

	public void setChieldTestModuleName(String chieldTestModuleName) {
		this.chieldTestModuleName = chieldTestModuleName;
	}
	
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
		logger.trace("Go To "+targetPageNameToTrace+", wating for load");
		driver.get(targetPageUrl);
		WaitForLoad();
		
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
	
	protected void checkIsTestPassed(boolean checkPageTableDbAndUI) {
		// TODO Auto-generated method stub
		isTestPassed = isTestPassed & checkPageTableDbAndUI;
		AssertWrapper.assertToLog(isTestPassed);
	}

	@Before
	//We will open browser and pass login page to be able to switch to any page we need
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
			System.setProperty("webdriver.chrome.driver","src/main/java/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "Firefox":
			System.setProperty("webdriver.gecko.driver","src/main/java/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
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
		ScreenShots screenShot = new ScreenShots();
		screenShot.makeSimpleShot(driver, getChieldTestModuleName());
		logger.info("Closing browser");
		driver.quit();
	}
	
}
