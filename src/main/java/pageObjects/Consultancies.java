package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Consultancies {
	
	private  WebDriver driver;
	
	By NewConsultancy=By.xpath(".//a[@href='/admin/consultancies/new']");
	By Find=By.xpath(".//input[@placeholder='Поиск']");
	By pagenatorInfo=By.xpath("//span[@class='pagination-info']");
	By consultancyNameHead=By.xpath("//th[@data-field='name']");
	//By consultancyNameRows = By.xpath("//td[contains(@style,'text-align: left;')]//a[contains(@href,'/admin/employees')]");
	By consultancyWorkloadHead=By.xpath("//th[@data-field='workload']");
	By RefreshButton=By.xpath("//button[@title='Обновить']");
	By consultancyNameRows = By.xpath("//tr[@data-index]//td[2]");
	By consultancyWorkload = By.xpath("//tr[@data-index]//td[3]"); 
	
	
	public Consultancies(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public ArrayList<String> returnListFromColumn(String columnName){
		
		By lookAt = null ;		
		switch (columnName.toLowerCase()) {
		case "name":
			lookAt = consultancyNameRows;
			break;
		case "workload":
			lookAt = consultancyWorkload;
			break;
		}
		
		ArrayList<String> newStrings = new ArrayList<String>();
		ArrayList<WebElement> newList = (ArrayList<WebElement>) this.driver.findElements(lookAt);
				
		for (WebElement listElement : newList) {
			newStrings.add(listElement.getText());
		}
		 
		
		return newStrings;
		
	}
	
	
	
	public String GetConsultancyNameValue(int rowNumber) {
		return this.driver.findElement(consultancyNameHead).getText();
	}
	
	public String GetConsultancyWorkloadValue(int rowNumber) {
		return this.driver.findElement(consultancyWorkloadHead).getText();
	}
	
	public String getTotalPagesValue() {
		String wholeText = this.driver.findElement(pagenatorInfo).getText();
		String[] arrText = wholeText.split(" ");
		return arrText[arrText.length-1];
	}
	public void GetToPage(String url) {
		this.driver.get(url);
		System.out.println("Get To employees, wating for load");
		WaitForLoad();
		System.out.println("loaded");
	}
	
	private boolean FindNotConsultancyElement() {
		try {
			driver.findElement(RefreshButton);
			return false;
		} catch (NoSuchElementException e ) {
			return true;
		}
			
	}
	
	public void WaitForLoad() {
		while (FindNotConsultancyElement());
	}
	
	


}
