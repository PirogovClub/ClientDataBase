package pageObjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.WebCommonActions;


public class Clients extends BasePOM{
	
	
	By createClientButton = By.xpath(".//button[contains(text(),'Create Client')]");
	By FindBtn=By.xpath(".//input[@placeholder='Поиск']");
	By RefreshBtn=By.name("refresh");
	By createClientFirstNameField = By.xpath("//input[@id='firstName']");
	By createClientLastNameField = By.xpath("//input[@id='lastName']");
	By saveNewClientName = By.xpath(".//button[@type='submit'][contains(text(),'save')]");
	By searchInput = By.xpath(".//input[@placeholder='Поиск']");
	By showWhileDataTableIsLoading= By.xpath("//div[@class='fixed-table-loading']");
	
	
	
	private Map<String,String> newClientMap = new HashMap<String,String>();
	
	public Map<String, String> getNewClientMap() {
		return newClientMap;
	}

	public Clients(WebDriver driver) {
		this.driver = driver;
		setTargetExistingPageElement(By.xpath(".//button[@type='submit'][contains(text(),'save')]"));
	}
	
	public Clients createClientButtonClick() {
		driver.findElement(createClientButton).click();
		
		return this;
	}
	
	public Clients saveNewClientNameClick() {
		driver.findElement(saveNewClientName).click();
		
		return this;
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
	
	public Clients createNewClient() {
		createClientButtonClick();
		waitModalWindow(getTargetExistingPageElement());
		setTextToTestField( createClientFirstNameField, newClientMap.get("firstName"), "First Name");
		setTextToTestField( createClientLastNameField, newClientMap.get("lastName"), "Last Name");
		saveNewClientNameClick();
		
		return this;
	}
	
	public void typeInSearchBox(String stringToType) {
		setTextToTestField(searchInput,stringToType,"Search Input");
	}

	public void searchForClient(String searchString) {
		// TODO Auto-generated method stub
		WaitForLoad(this.getTargetExistingPageElement());
		typeInSearchBox(searchString);
		waitForElementToHide(showWhileDataTableIsLoading);
		
	}
	
	
	

}
