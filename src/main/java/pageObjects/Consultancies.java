package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Consultancies {
	
	private  WebDriver driver;
	
	By NewConsultancy=By.xpath(".//a[@href='/admin/consultancies/new']");
	By Find=By.xpath(".//input[@placeholder='Поиск']");
	By PagenatorInfo=By.xpath("//span[@class='pagination-info']");
	By ConsultancyName=By.xpath("//th[@data-field='name']");
	By ConsultancyWorkload=By.xpath("//th[@data-field='workload']");
	By RefreshButton=By.xpath("//button[@title='Обновить']");
	
	
	public Consultancies(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public String GetConsultancyNameValue(int rowNumber) {
		return this.driver.findElement(ConsultancyName).getText();
	}
	
	public String GetConsultancyWorkloadValue(int rowNumber) {
		return this.driver.findElement(ConsultancyWorkload).getText();
	}
	
	public String GetPagenatorInfoValue(int rowNumber) {
		return this.driver.findElement(PagenatorInfo).getText();
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
