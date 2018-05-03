package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Consultancies {
	WebDriver driver;
	
	By NewConsultancy=By.xpath(".//a[@href='/admin/consultancies/new']");
	By Find=By.xpath(".//input[@placeholder='Поиск']");
	By RefreshBtn=By.xpath(".//i[@class='glyphicon glyphicon-refresh icon-refresh']");


}
