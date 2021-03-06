package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.openqa.selenium.By;
import pageObjects.Employees;
import utils.RandomData;

import java.util.concurrent.ThreadLocalRandom;


public class EmployeePageTest extends BaseTest {
	
	By existingPageElement = By.xpath("//button[@title='��������']");
		
	private void printList(List<String> NameList) {
		for (String listElement : NameList) {
			logger.debug(listElement);
		}
	}
	
	
	@Test
	public void testClients() {
		try { 
			setChieldTestModuleName(this.getClass().getName());
			logger.info("Get into Test" + this.getClass().getName());
			Employees employees = new Employees(driver);
			
			RandomData RandomData = new RandomData();
			RandomData.LanguageSets LanguageSets = null;
			
			employees.setNewConsultancyParamiters(
					config.getConfigProp("employeesTestFirstName")+RandomData.getRandomString(2, LanguageSets.ENGLISH_HIGH),
					config.getConfigProp("employeesTestSecondName")+RandomData.getRandomString(2, LanguageSets.ENGLISH_HIGH),
					config.getConfigProp("employeesTestMaxLoad")+RandomData.getRandomInt(10, 200)
					);
			this.setTargetExistingPageElement(employees.getTargetExistingPageElement());
			this.setTargetPageNameToTrace("Employees");
			this.setTargetPageUrl(config.getConfigProp("employeesUrl"));
			
			GetToPage(targetPageUrl);
			printList(employees.returnListFromColumn("name"));
			//Get all text in column workload
			printList(employees.returnListFromColumn("workload"));

			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = ThreadLocalRandom.current().nextInt(1, 20 + 1);
			logger.info("pushing createEmployeeButton");
			employees.createEmployeeButtonClick();
			
			employees.createNewEmployee();
			
			
			
		} catch (Throwable e) { 
			logger.error("in "+this.getClass().getName()+" caught:\\r\\n\" + e");
	         
	          fail("Test Failed");
		} 
	}
}


