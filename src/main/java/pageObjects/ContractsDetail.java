package pageObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tests.BaseTest;

public class ContractsDetail  extends BasePOM{
	
	By fieldNames = By.xpath(".//div[@class='col-sm-3']");
	By fieldValues = By.xpath(".//div[@class='col-sm-9']");
	By waitForElement = By.xpath("//button[contains(text(),'Go back')]");
	protected String SQLquery="";
	
	public String getSQLquery() {
		return SQLquery;
	}

	public By getWaitForElement() {
		return waitForElement;
	}

	public ContractsDetail (WebDriver driver){
		this.driver = driver;
	}
	
	public List<Map<String,String>> readContractData(){
		
		List<Map<String,String>> listToReturn = new ArrayList<Map<String,String>>();
		Map<String,String> mapToReturn = new HashMap<String,String>();
		
		List<WebElement> fieldsNamesValues = driver.findElements(fieldNames);
		List<WebElement> fieldsValuesValues = driver.findElements(fieldValues);
		
		for (Integer i=0;i<fieldsNamesValues.size();i++) {
			mapToReturn.put(fieldsNamesValues.get(i).getText(), fieldsValuesValues.get(i).getText());
		}
		listToReturn.add(mapToReturn);
		
		return listToReturn;
		
	}
	
	public void updateSqlStringWithContractId(String value) {
		
		SQLquery = "select \r\n" + 
				//"        contract.id as \"Contract\",\r\n" + 
				"        to_char(contract.contract_date,'YYYY-MM-DD') as \"Contract Date\",\r\n" + 
				"        contract.payment_type as \"Payment Type\",\r\n" + 
				"        to_char(contract.payment_date,'YYYY-MM-DD') as \"Payment Date\",\r\n" + 
				"        CONCAT( money_client.amount, ' ', money_client.currency) as \"Price\",\r\n" + 
				"        CONCAT(consultancy.name,' from ', deal.open_date) as \"Deal\", \r\n" + 
				"        CONCAT(employee.first_name, ' ', employee.last_name) as \"Mentor\",\r\n" + 
				"        CONCAT(money_employee.amount,' ', money_employee.currency) as \"Employee Rate\"\r\n" + 
				//"        deal.status as Status\r\n" + 
				"From    \r\n" + 
				"        money as money_client inner join ( money as money_employee inner join (consultancy inner join (client inner join (employee inner join (Contract inner join deal \r\n" + 
				"        on (contract.deal_id = deal.id))\r\n" + 
				"        on (employee.id = contract.employee_id))\r\n" + 
				"        on (client.id = deal.client_id))\r\n" + 
				"        on (consultancy.id = deal.consultancy_id))\r\n" + 
				"        on (money_employee.id = contract.employee_rate_id))\r\n" + 
				"        on (money_client.id = contract.price_id)\r\n" + 
				"Where\r\n" + 
				"        contract.id="+value;
		
		
	}

}
