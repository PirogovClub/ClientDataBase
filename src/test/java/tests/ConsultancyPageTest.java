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
import utils.RandomData;

public class ConsultancyPageTest extends BaseTest {
	
	
	
	
	

	@Test
	public void testConsultancy() {
		try {
			Consultancies consultancies = new Consultancies(driver);
			
			this.setTargetPageUrl(utils.ReadConfigMain.getValueFromProperty("consultanciesUrl"));
			this.setTargetPageNameToTrace("Consltancies");
			this.setTargetExistingPageElement(consultancies.getTargetExistingPageElement());
			
			RandomData RandomData = new RandomData();
			RandomData.LanguageSets LanguageSets = null;
			
			consultancies.setNewConsultancyParamiters(
					utils.ReadConfigMain.getValueFromProperty("consultanciesTitle")+RandomData.getRandomString(2, LanguageSets.ENGLISH_HIGH),
					 utils.ReadConfigMain.getValueFromProperty("consultanciesDescription")+RandomData.getRandomString(2, LanguageSets.ENGLISH_HIGH),
					 utils.ReadConfigMain.getValueFromProperty("consultanciesPriceUAH")+RandomData.getRandomInt(10, 200),
					 utils.ReadConfigMain.getValueFromProperty("consultanciesPriceEUR")+RandomData.getRandomInt(10, 200),
					 utils.ReadConfigMain.getValueFromProperty("consultanciesUSD")+RandomData.getRandomInt(10, 200),
					 utils.ReadConfigMain.getValueFromProperty("consultanciesEmployeeRate")+RandomData.getRandomInt(10, 200)
					);
			
			//Goto Consultancies page
			GetToPage(targetPageUrl);
			// Go to new page
			consultancies.createConsultancyButtonClick();
			WaitForLoad(consultancies.getConsultancyTitle());
			
			consultancies.createNewConsultancy();
			WaitForLoad(consultancies.getTargetExistingPageElement());
			
			consultancies.clickTitleHref();
			
			WaitForLoad(consultancies.getConsultancyTitle());
			
			consultancies.deleteConsultancyRecord();
			WaitForLoad(consultancies.getTargetExistingPageElement());
			
			
		} catch (Throwable e) {
			System.out.println("caught:\r\n" + e);
			fail("Test Failed");
		}
	}
}
