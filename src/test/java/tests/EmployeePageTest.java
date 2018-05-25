package tests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import pageObjects.Employees;
import java.util.concurrent.ThreadLocalRandom;


public class EmployeePageTest extends BaseTest {
	
	By existingPageElement = By.xpath("//button[@title='Обновить']");
		
	private void printList(List<String> NameList) {
		for (String listElement : NameList) {
			System.out.println(listElement);
		}
	}
	
	private Map<String, String> newEmplMap = new HashMap<String, String>();
	
	private void setParam() {
		try {
			
			//this.setExistingPageElement(existingPageElement);
			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = ThreadLocalRandom.current().nextInt(1, 20 + 1);
			
			newEmplMap.put("employeesTestFirstName", utils.ReadConfigMain.getValueFromProperty("employeesTestFirstName")+randomNum);
			newEmplMap.put("employeesTestSecondName", utils.ReadConfigMain.getValueFromProperty("employeesTestSecondName")+randomNum);
			newEmplMap.put("employeesTestMaxLoad", utils.ReadConfigMain.getValueFromProperty("employeesTestMaxLoad")+randomNum);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testClients() {
		try { 
			Employees employees = new Employees(driver);
			setParam();
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
			employees.createNewEmployee(newEmplMap);
			
			
			
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


