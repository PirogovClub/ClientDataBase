package tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pageObjects.Consultancies;
import pageObjects.LoginPage;

public class ConsultancyPageTest extends BaseTest {

	private String consultancyUrl;
	
	public String getConsultancyUrl() {
		return consultancyUrl;
	}

	public void setConsultancyUrl(String consultancyUrl) {
		this.consultancyUrl = consultancyUrl;
	}
	
	private void printList(List<String> NameList) {
		for (String listElement : NameList) {
			System.out.println(listElement);
		}
	}

	@Test
	public void testConultancy() {
		try { 
			//Set var page
			setConsultancyUrl(utils.ReadConfigMain.getValueFromProperty("consultancyUrl"));
			
			//Now lets go to needed page
			Consultancies consultancies = new Consultancies(driver); 
			//go to needed url, just to be sure after login page
			consultancies.GetToPage(this.consultancyUrl);
			//Get all text in column Name
			printList(consultancies.returnListFromColumn("name"));
			//Get all text in column workload
			printList(consultancies.returnListFromColumn("workload"));
			
			//Get Total number of pages
			System.out.println("Total Pages="+consultancies.getTotalPagesValue());
			
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


