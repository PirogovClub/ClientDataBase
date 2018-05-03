package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cashflow {
	WebDriver driver;
	
	By beginDateField=By.xpath(".//input[@id='beginDateField']");
	By endDateField=By.xpath(".//input[@id='endDateField']");
	By submitButton=By.xpath(".//button[@type='submit']");
	By selectedConsultancyField=By.xpath(".//select[@id='selectedConsultancyField']");
	

}
