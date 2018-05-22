package tests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;
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

	@Test
	public void testClients() {
		try { 
			try {
				this.setTargetPageUrl(utils.ReadConfigMain.getValueFromProperty("employeesUrl"));
				this.setTargetPageNameToTrace("Employees");
				this.setExistingPageElement(existingPageElement);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GetToPage();
			
			Employees employees = new Employees(driver);
			
			printList(employees.returnListFromColumn("name"));
			//Get all text in column workload
			printList(employees.returnListFromColumn("workload"));

			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = ThreadLocalRandom.current().nextInt(1, 20 + 1);
			
			employees.createEmployeeButtonClick();
			employees.typeNewEmployeeName(utils.ReadConfigMain.getValueFromProperty("employeesTestFirstName")+randomNum);
			employees.typeNewEmployeeLastName(utils.ReadConfigMain.getValueFromProperty("employeesTestSecondName")+randomNum);
			employees.typeNewEmployeeMaxLoad(utils.ReadConfigMain.getValueFromProperty("employeesTestMaxLoad")+randomNum);
			employees.saveNewEmployeeClick();
			
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


