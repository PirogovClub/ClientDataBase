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

	public void searchForNewClient() {
		// TODO Auto-generated method stub
		typeInSearchBox(newClientMap.get("firstName")+" "+newClientMap.get("lastName"));
		waitForElementToHide(showWhileDataTableIsLoading);
		
	}
	
	public void findNewClientAndOpen() {
		WaitForLoad(this.getTargetExistingPageElement());
		searchForNewClient();
		clickHrefWithText(newClientMap.get("firstName")+" "+newClientMap.get("lastName"));
	}
	

}
