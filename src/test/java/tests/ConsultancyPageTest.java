package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


import org.openqa.selenium.By;
import pageObjects.Consultancies;
import utils.RandomData;

public class ConsultancyPageTest extends BaseTest {
	
	
	
	
	

	@Test
	public void testConsultancy() {
		try {
			setChieldTestModuleName(this.getClass().getName());
			logger.info("Get into Test" + this.getClass().getName());
			Consultancies consultancies = new Consultancies(driver);
			
			this.setTargetPageUrl(config.getConfigProp("consultanciesUrl"));
			this.setTargetPageNameToTrace("Consltancies");
			this.setTargetExistingPageElement(consultancies.getTargetExistingPageElement());
			
			RandomData RandomData = new RandomData();
			RandomData.LanguageSets LanguageSets = null;
			
			consultancies.setNewConsultancyParamiters(
					config.getConfigProp("consultanciesTitle")+RandomData.getRandomString(50, LanguageSets.ENGLISH_HIGH),
					 "",
					 config.getConfigProp("consultanciesPriceUAH")+RandomData.getRandomInt(10, 200),
					 config.getConfigProp("consultanciesPriceEUR")+RandomData.getRandomInt(10, 200),
					 config.getConfigProp("consultanciesUSD")+RandomData.getRandomInt(10, 200),
					 config.getConfigProp("consultanciesEmployeeRate")+RandomData.getRandomInt(10, 200)
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
			logger.error("in "+this.getClass().getName()+" caught:\r\n" + e);
			fail("Test Failed");
		}
	}
}
