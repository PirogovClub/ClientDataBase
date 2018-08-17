/**
 * 
 */
package pageObjects;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import dbObjects.ClientsDbOperations;



/**
 * @author pirog
 *
 */
public class ClientProperties  extends BasePOM{
	
	By clientEmail=By.id("email");
	By clientFirstName=By.id("firstName");
	By clientLastName=By.id("lastName");
	By clientSkype=By.id("skype");
	By clientPhoneNumber=By.id("phoneNumber");
	By clientCity=By.id("city");
	By clientCountry=By.id("country");
	By saveClientParam=By.xpath(".//button[@type='submit'][contains(text(),'Save')]");
	
	private Map<String,String> newClientMap = new HashMap<String,String>();
	private Map<String,String> readClientMapFromPage = new HashMap<String,String>();
	private Map<String,String> readClientMapFromDb = new HashMap<String,String>();
	
	public ClientProperties(WebDriver driver) {
		this.driver = driver;
		setTargetExistingPageElement(By.xpath(".//button[@type='button'][contains(text(),'Go back')]"));
		
	}
	
	
	public Map<String, String> getNewClientMap() {
		return newClientMap;
	}

	public void setNewClientMap(Map<String, String> ClientMap) {
		this.newClientMap = ClientMap;
	}
	
	public void setNewClientMap(String firstName, String lastName, String email, String country, String  city, String phone, String skype) {
		newClientMap.put("firstName",firstName);
		newClientMap.put("lastName",lastName);
		newClientMap.put("email",email);
		newClientMap.put("country",country);
		newClientMap.put("city",city);
		newClientMap.put("phone",phone);
		newClientMap.put("skype",skype);
	}
	
	public String getParamiterValue(String chooseObject, String paramiter) {
		switch (chooseObject){
		case "readed":
			return readClientMapFromPage.get(paramiter);
		case "created":
			return newClientMap.get(paramiter);
		case "fromDb":
			return readClientMapFromDb.get(paramiter);
		default:
			return "";
		}
	}
	
	public Map<String, String> compareClientParamitersWithPage(){
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("firstName",makeAssertToPage("firstName"));
		resultMap.put("lastName",makeAssertToPage("lastName"));
		resultMap.put("email",makeAssertToPage("email"));
		resultMap.put("country",makeAssertToPage( "country"));
		resultMap.put("city",makeAssertToPage("city"));
		resultMap.put("phone",makeAssertToPage("phone"));
		resultMap.put("skype",makeAssertToPage("skype"));
		return resultMap;
	}
	
	public Map<String, String> compareClientParamitersWithDb(){
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("firstName",makeAssertToDb("firstName"));
		resultMap.put("lastName",makeAssertToDb("lastName"));
		resultMap.put("email",makeAssertToDb("email"));
		resultMap.put("country",makeAssertToDb( "country"));
		resultMap.put("city",makeAssertToDb("city"));
		resultMap.put("phone",makeAssertToDb("phone"));
		resultMap.put("skype",makeAssertToDb("skype"));
		return resultMap;
	}
	
	
	
	public String makeAssertToPage(String paramiter) {
		try {
			assertEquals(paramiter,getParamiterValue("created",paramiter),getParamiterValue("readed",paramiter));
			return "OK";
		}
		catch (Throwable e) {
			return("Report Error" + e);
		}
	}
	
	public String makeAssertToDb(String paramiter) {
		try {
			assertEquals(paramiter,getParamiterValue("created",paramiter),getParamiterValue("fromDb",paramiter));
			return "OK";
		}
		catch (Throwable e) {
			return("Report Error" + e);
		}
	}
	
	
	
	public Map<String, String> readClientParamitersFromDb(String ClientId) {
		ClientsDbOperations ClientsDbOperations = new ClientsDbOperations();
		readClientMapFromDb = ClientsDbOperations.getClientDataForCreatePage(ClientId);
		
		ClientsDbOperations.closeDbConnection();
		return readClientMapFromPage;
	}
	
	public Map<String, String> readClientParamitersFromDb() {
		ClientsDbOperations ClientsDbOperations = new ClientsDbOperations();
		readClientMapFromDb = ClientsDbOperations.getClientDataForCreatePage(getClientIdFromCurrentURL());
		ClientsDbOperations.closeDbConnection();
		return readClientMapFromPage;
	}
	
	
	public Map<String, String> readClientParamitersFromPage() {
		readClientMapFromPage.put("firstName",driver.findElement(clientFirstName).getAttribute("value"));
		readClientMapFromPage.put("lastName",driver.findElement(clientLastName).getAttribute("value"));
		readClientMapFromPage.put("email",driver.findElement(clientEmail).getAttribute("value"));
		readClientMapFromPage.put("country",driver.findElement(clientCountry).getAttribute("value"));
		readClientMapFromPage.put("city",driver.findElement(clientCity).getAttribute("value"));
		readClientMapFromPage.put("phone",driver.findElement(clientPhoneNumber).getAttribute("value"));
		readClientMapFromPage.put("skype",driver.findElement(clientSkype).getAttribute("value"));
		return readClientMapFromPage;
	}
	
	public  Map<String, String> readClientParamitersFromPage(Boolean withErrors, String stringToAdd) {
		WaitForLoad(this.getTargetExistingPageElement());
		if (withErrors) {
			readClientMapFromPage.put("firstName", driver.findElement(clientFirstName).getAttribute("value")+stringToAdd);
			readClientMapFromPage.put("lastName", driver.findElement(clientLastName).getAttribute("value")+stringToAdd);
			readClientMapFromPage.put("email", driver.findElement(clientEmail).getAttribute("value")+stringToAdd);
			readClientMapFromPage.put("country", driver.findElement(clientCountry).getAttribute("value")+stringToAdd);
			readClientMapFromPage.put("city", driver.findElement(clientCity).getAttribute("value")+stringToAdd);
			readClientMapFromPage.put("phone", driver.findElement(clientPhoneNumber).getAttribute("value")+stringToAdd);
			readClientMapFromPage.put("skype", driver.findElement(clientSkype).getAttribute("value")+stringToAdd);
			return readClientMapFromPage;
		} else {
			return readClientParamitersFromPage();
		}
	}
	
	

	

	public void setSecondaryFields() {
		
		// TODO Auto-generated method stub
		setTextToTestField( clientFirstName, newClientMap.get("firstName"), "firstName");
		setTextToTestField( clientLastName, newClientMap.get("lastName"), "lastName");
		setTextToTestField( clientEmail, newClientMap.get("email"), "email");
		setTextToTestField( clientCountry , newClientMap.get("country"), "country");
		setTextToTestField( clientCity , newClientMap.get("city"), "city");
		setTextToTestField( clientPhoneNumber , newClientMap.get("phone"), "phone");
		setTextToTestField( clientSkype, newClientMap.get("skype"), "skype");
	}

	public void clickSaveButton() {
		// TODO Auto-generated method stub
		logger.info("Clicking Save");
		driver.findElement(saveClientParam).click();
		
	}
	
	public void setSecondaryFieldsAndSave() {
		WaitForLoad(this.getTargetExistingPageElement());
		logger.error("Typing fields");
		setSecondaryFields();
		clickSaveButton();
		
	}
	
	public String getClientIdFromCurrentURL() {
		String strToReturn = "";
		strToReturn = driver.getCurrentUrl();
		strToReturn = strToReturn.substring(strToReturn.lastIndexOf('/') + 1);
		return strToReturn;
	}
	
	

	
	

}
