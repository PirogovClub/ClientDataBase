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
	
	
	public Map<String, String> getNewClientMap() {
		return newClientMap;
	}

	public void setNewClientMap(Map<String, String> newClientMap) {
		this.newClientMap = newClientMap;
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
	
	public void setNewClientMap(String firstName, String lastName, String email, String country, String city, String phone, String skype) {
		newClientMap.put("FirstName",firstName);
		newClientMap.put("lastName",lastName);
		newClientMap.put("email",email);
		newClientMap.put("country",country);
		newClientMap.put("city",city);
		newClientMap.put("phone",phone);
		newClientMap.put("skype",skype);
	}
	
	public void readClientParamiters() {
		readClientMap.put("FirstName",driver.findElement(clientFirstName).getAttribute("value"));
		readClientMap.put("lastName",driver.findElement(clientLastName).getAttribute("value"));
		readClientMap.put("email",driver.findElement(clientEmail).getAttribute("value"));
		readClientMap.put("country",driver.findElement(clientCountry).getAttribute("value"));
		readClientMap.put("city",driver.findElement(clientCity).getAttribute("value"));
		readClientMap.put("phone",driver.findElement(clientPhoneNumber).getAttribute("value"));
		readClientMap.put("skype",driver.findElement(clientSkype).getAttribute("value"));
	}
	
	public Boolean makeAssert(String paramiter) {
		try {
			assertEquals(paramiter,getParamiterValue("created",paramiter),getParamiterValue("readed",paramiter));
			return true;
		}
		catch (Throwable e) {
			System.out.println("Report Error" + e);
			return false;
		}
		
		
	}
	
	public void readClientParamiters(Boolean withErrors, String stringToAdd) {
		if (withErrors) {
			readClientMap.put("FirstName", driver.findElement(clientFirstName).getAttribute("value")+stringToAdd);
			readClientMap.put("lastName", driver.findElement(clientLastName).getAttribute("value")+stringToAdd);
			readClientMap.put("email", driver.findElement(clientEmail).getAttribute("value")+stringToAdd);
			readClientMap.put("country", driver.findElement(clientCountry).getAttribute("value")+stringToAdd);
			readClientMap.put("city", driver.findElement(clientCity).getAttribute("value")+stringToAdd);
			readClientMap.put("phone", driver.findElement(clientPhoneNumber).getAttribute("value")+stringToAdd);
			readClientMap.put("skype", driver.findElement(clientSkype).getAttribute("value")+stringToAdd);
		} else {
			readClientParamiters();
		}
	}

	public ClientProperties(WebDriver driver) {
		this.driver = driver;
		setTargetExistingPageElement(By.xpath(".//button[@type='button'][contains(text(),'Go back')]"));
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
		setSecondaryFields();
		clickSaveButton();
	}
	

	
	

}
