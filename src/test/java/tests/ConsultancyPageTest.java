package tests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.openqa.selenium.By;
import pageObjects.Consultancies;

public class ConsultancyPageTest extends BaseTest {
	
	private Map<String, String> newConsultancyMap = new HashMap<String, String>();
	
	private void setParam() {
		try {
			
			//this.setExistingPageElement(existingPageElement);
			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = ThreadLocalRandom.current().nextInt(1, 20 + 1);
			
			newConsultancyMap.put("consultanciesTitle", utils.ReadConfigMain.getValueFromProperty("consultanciesTitle")+randomNum);
			newConsultancyMap.put("consultanciesDescription", utils.ReadConfigMain.getValueFromProperty("consultanciesDescription")+randomNum);
			newConsultancyMap.put("consultanciesPriceUAH", utils.ReadConfigMain.getValueFromProperty("consultanciesPriceUAH")+randomNum);
			newConsultancyMap.put("consultanciesPriceEUR", utils.ReadConfigMain.getValueFromProperty("consultanciesPriceEUR")+randomNum);
			newConsultancyMap.put("consultanciesUSD", utils.ReadConfigMain.getValueFromProperty("consultanciesUSD")+randomNum);
			newConsultancyMap.put("consultanciesEmployeeRate", utils.ReadConfigMain.getValueFromProperty("consultanciesEmployeeRate")+randomNum);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	@Test
	public void testConsultancy() {
		try {
			Consultancies consultancies = new Consultancies(driver);
			this.setParam();
			this.setTargetPageUrl(utils.ReadConfigMain.getValueFromProperty("consultanciesUrl"));
			this.setTargetPageNameToTrace("Consltancies");
			this.setTargetExistingPageElement(consultancies.getTargetExistingPageElement());
			//Goto Consultancies page
			GetToPage(targetPageUrl);
			// Go to new page
			consultancies.createConsultancyButtonClick();
			WaitForLoad(consultancies.getConsultancyTitle());
			
			consultancies.createNewConsultancy(newConsultancyMap);
			WaitForLoad(consultancies.getTargetExistingPageElement());
			
			consultancies.clickHrefWithText(newConsultancyMap.get("consultanciesTitle"));
			WaitForLoad(consultancies.getConsultancyTitle());
			
			consultancies.deleteConsultancyRecord();
			WaitForLoad(consultancies.getTargetExistingPageElement());
			
			
		} catch (Throwable e) {
			System.out.println("caught:\r\n" + e);
			fail("Test Failed");
		}
	}
}
