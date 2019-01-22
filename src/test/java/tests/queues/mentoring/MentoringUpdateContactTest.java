package tests.queues.mentoring;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pageObjects.InvoicesMain;
import pageObjects.MainNavigation;
import pageObjects.Queues;
import tests.BaseTest;
import utils.DataBase;
import utils.HtmlTable;

public class MentoringUpdateContactTest extends BaseTest {
	
	protected Queues queues; 
	protected DataBase db = new DataBase();
	protected MainNavigation mainNavigation;
	protected HtmlTable mentoringContracts;
	
	@Before
	public void initAllTestBase() {

		db.connectToDb();
		mainNavigation = new MainNavigation(driver);
		mainNavigation.clickQueuesMenu();
		queues = new Queues(driver);
		queues.clickMetnoringTab();

	}
	
	public void getToContract(Integer contractId) {
		
	}
	
	public void getToContract() {
		getToContract(-1);
		
	}
	
	@Test
	public void runTest() {
		setChieldTestModuleName(this.getClass().getName());
		logger.info("Get into Test" + this.getClass().getName());
		
		
	}
	
	@After

	public void deInitAllTestBase() {
		
		db.closeConnection();

	}
	

}
