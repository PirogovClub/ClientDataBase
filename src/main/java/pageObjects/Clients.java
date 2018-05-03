package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Clients {
	WebDriver driver;
	
	By createClientButton = By.xpath(".//button[@type='button'][contains(text(),'Create Client')]");
	By FindBtn=By.xpath(".//input[@placeholder='Поиск']");
	By RefreshBtn=By.xpath(".//i[@class='glyphicon glyphicon-refresh icon-refresh']");

}
