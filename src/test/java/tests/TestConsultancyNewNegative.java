package tests;

import org.junit.Test;

import pageObjects.Consultancies;
import utils.AssertWarp;
import utils.RandomData;

public class TestConsultancyNewNegative extends BaseTest {
	
	@Test
	public void makeTest() {
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
		
		boolean isTestPassed = true;
		//размер должен быть между 2 и 50
		//size must be between 2 and 50
		isTestPassed = isTestPassed & consultancies.checkErrorTitleMessage("size must be between 2 and 50");
		AssertWarp.assertToLog(isTestPassed);
		isTestPassed = isTestPassed & consultancies.checkErrorDescriptionMessage("It is required field");
		AssertWarp.assertToLog(isTestPassed);
	}

}
