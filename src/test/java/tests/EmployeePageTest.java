package tests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import pageObjects.Employees;
import utils.RandomData;

import java.util.concurrent.ThreadLocalRandom;


public class EmployeePageTest extends BaseTest {
	
	By existingPageElement = By.xpath("//button[@title='Обновить']");
		
	private void printList(List<String> NameList) {
		for (String listElement : NameList) {
			System.out.println(listElement);
		}
	}
	
	
	@Test
	public void testClients() {
		try { 
			Employees employees = new Employees(driver);
			
			RandomData RandomData = new RandomData();
			RandomData.LanguageSets LanguageSets = null;
			
			employees.setNewConsultancyParamiters(
					 utils.ReadConfigMain.getValueFromProperty("employeesTestFirstName")+RandomData.getRandomString(2, LanguageSets.ENGLISH_HIGH),
					 utils.ReadConfigMain.getValueFromProperty("employeesTestSecondName")+RandomData.getRandomString(2, LanguageSets.ENGLISH_HIGH),
					 utils.ReadConfigMain.getValueFromProperty("employeesTestMaxLoad")+RandomData.getRandomInt(10, 200)
					);
			this.setTargetExistingPageElement(employees.getTargetExistingPageElement());
			this.setTargetPageNameToTrace("Employees");
			this.setTargetPageUrl(utils.ReadConfigMain.getValueFromProperty("employeesUrl"));
			
			GetToPage(targetPageUrl);
			printList(employees.returnListFromColumn("name"));
			//Get all text in column workload
			printList(employees.returnListFromColumn("workload"));

			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = ThreadLocalRandom.current().nextInt(1, 20 + 1);
			
			employees.createEmployeeButtonClick();
			System.out.println("pushed createEmployeeButtonClick");
			employees.createNewEmployee();
			
			
			
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


