/**
 * 
 */
package pageObjects;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



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
	private Map<String,String> readClientMap = new HashMap<String,String>();
	
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
			return readClientMap.get(paramiter);
		case "created":
			return newClientMap.get(paramiter);
		default:
			return "";
		}
	}
	
	public Map<String, String> compareClientParamiters(){
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("firstName",makeAssert("firstName"));
		resultMap.put("lastName",makeAssert("lastName"));
		resultMap.put("email",makeAssert("email"));
		resultMap.put("country",makeAssert( "country"));
		resultMap.put("city",makeAssert("city"));
		resultMap.put("phone",makeAssert("phone"));
		resultMap.put("skype",makeAssert("skype"));
		return resultMap;
	}
	
	
	
	public Map<String, String> readClientParamiters() {
		readClientMap.put("firstName",driver.findElement(clientFirstName).getAttribute("value"));
		readClientMap.put("lastName",driver.findElement(clientLastName).getAttribute("value"));
		readClientMap.put("email",driver.findElement(clientEmail).getAttribute("value"));
		readClientMap.put("country",driver.findElement(clientCountry).getAttribute("value"));
		readClientMap.put("city",driver.findElement(clientCity).getAttribute("value"));
		readClientMap.put("phone",driver.findElement(clientPhoneNumber).getAttribute("value"));
		readClientMap.put("skype",driver.findElement(clientSkype).getAttribute("value"));
		return readClientMap;
	}
	
	public String makeAssert(String paramiter) {
		try {
			assertEquals(paramiter,getParamiterValue("created",paramiter),getParamiterValue("readed",paramiter));
			return "OK";
		}
		catch (Throwable e) {
			return("Report Error" + e);
		}
		
		
	}
	
	public  Map<String, String> readClientParamiters(Boolean withErrors, String stringToAdd) {
		WaitForLoad(this.getTargetExistingPageElement());
		if (withErrors) {
			readClientMap.put("firstName", driver.findElement(clientFirstName).getAttribute("value")+stringToAdd);
			readClientMap.put("lastName", driver.findElement(clientLastName).getAttribute("value")+stringToAdd);
			readClientMap.put("email", driver.findElement(clientEmail).getAttribute("value")+stringToAdd);
			readClientMap.put("country", driver.findElement(clientCountry).getAttribute("value")+stringToAdd);
			readClientMap.put("city", driver.findElement(clientCity).getAttribute("value")+stringToAdd);
			readClientMap.put("phone", driver.findElement(clientPhoneNumber).getAttribute("value")+stringToAdd);
			readClientMap.put("skype", driver.findElement(clientSkype).getAttribute("value")+stringToAdd);
			return readClientMap;
		} else {
			return readClientParamiters();
		}
	}

	

	public void setSecondaryFields() {
		
		// TODO Auto-generated method stub
		setTextToTestField( clientEmail, newClientMap.get("email"), "email");
		setTextToTestField( clientCountry , newClientMap.get("country"), "country");
		setTextToTestField( clientCity , newClientMap.get("city"), "city");
		setTextToTestField( clientPhoneNumber , newClientMap.get("phone"), "phone");
		setTextToTestField( clientSkype, newClientMap.get("skype"), "skype");
	}

	public void clickSaveButton() {
		// TODO Auto-generated method stub
		driver.findElement(saveClientParam).click();
	}
	
	public void setSecondaryFieldsAndSave() {
		WaitForLoad(this.getTargetExistingPageElement());
		setSecondaryFields();
		clickSaveButton();
	}
	

	
	

}
