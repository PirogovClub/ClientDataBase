package pageObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Employees {
	WebDriver driver;

	By createEmployeeButton = By.xpath(".//button[@type='button'][contains(text(),'Employee')]");
	By createEmployeeFirstNameField = By.xpath("//input[@id='firstName']");
	By createEmployeeLastNameField = By.xpath("//input[@id='lastName']");
	By createEmployeeMaxLoadField = By.xpath("//input[@id='maxClients']");
	By saveNewEmployeeName = By.xpath(".//button[@type='submit'][contains(text(),'save')]");
	By employeeNameRows = By.xpath("//tr[@data-index]//td[2]");
	By EmployeeWorkload = By.xpath("//tr[@data-index]//td[3]"); 
	By EmployeeNameHead=By.xpath("//th[@data-field='name']");
	By EmployeeWorkloadHead=By.xpath("//th[@data-field='workload']");
	By pagenatorInfo=By.xpath("//span[@class='pagination-info']");
	
	private By targetExistingPageElement = By.xpath(".//button[@type='button'][contains(text(),'Employee')]");
	private Map<String, String> newEmplMap = new HashMap<String, String>();
	
	public By getTargetExistingPageElement() {
		return targetExistingPageElement;
	}
	
	public Employees(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public Employees createEmployeeButtonClick() {
		driver.findElement(createEmployeeButton).click();
		
		return this;
	}
	
	public Employees saveNewEmployeeClick() {
		driver.findElement(saveNewEmployeeName).click();
		
		return this;
	}
	
	public Employees typeNewEmployeeName(String newEmployeeName) {
		WebElement webElement = driver.findElement(createEmployeeFirstNameField);
		if (webElement.isDisplayed()) {
			webElement.sendKeys(newEmployeeName);
		} else {
			System.out.println("First Name field is not displayed");
		}
		
		return this;
	}
	
	public Employees typeNewEmployeeLastName(String newEmployeeLastName) {
		WebElement webElement = driver.findElement(createEmployeeLastNameField);
		if (webElement.isDisplayed()) {
			webElement.sendKeys(newEmployeeLastName);
		} else {
			System.out.println("Last Name field is not displayed");
		}
		
		return this;
	}
	
	public Employees typeNewEmployeeMaxLoad(String employeeMaxLoad) {
		WebElement webElement = driver.findElement(createEmployeeMaxLoadField);
		if (webElement.isDisplayed()) {
			webElement.sendKeys(employeeMaxLoad);
		} else {
			System.out.println("Last Name field is not displayed");
		}
		
		return this;
	}
	
	public Employees createNewEmployee() {
		
		typeNewEmployeeName(newEmplMap.get("FirstName"));
		typeNewEmployeeLastName(newEmplMap.get("SecondName"));
		typeNewEmployeeMaxLoad(newEmplMap.get("MaxLoad"));
		System.out.println("Entered values");
		saveNewEmployeeClick();
		System.out.println("pushed save");
		return this;
	}
	
public List<String> returnListFromColumn(String columnName){
		
		By lookAt = null ;	
		ArrayList<String> ListFromColumn = new ArrayList<String>();
		//choose what column to get
		switch (columnName.toLowerCase()) {
		case "name":
			lookAt = employeeNameRows;
			break;
		case "workload":
			lookAt = EmployeeWorkload;
			break;
		}
		
		List<WebElement> myList = driver.findElements(lookAt);
				
		for (WebElement cell : myList) {
			ListFromColumn.add(cell.getText());
		}
		
		return ListFromColumn;
		
	}
	
	public String GetEmployeeNameValue(int rowNumber) {
		return this.driver.findElement(EmployeeNameHead).getText();
	}
	
	public String GetEmployeeWorkloadValue(int rowNumber) {
		return this.driver.findElement(EmployeeWorkloadHead).getText();
	}
	
	public String getTotalPagesValue() {
		String wholeText = this.driver.findElement(pagenatorInfo).getText();
		String[] arrText = wholeText.split(" ");
		return arrText[arrText.length-1];
	}

	public void setNewConsultancyParamiters(String FirstName, String SecondName, String MaxLoad) {
		// TODO Auto-generated method stub
		newEmplMap.put("FirstName", FirstName);
		newEmplMap.put("SecondName", SecondName);
		newEmplMap.put("MaxLoad", MaxLoad);
		
	}
	

}
