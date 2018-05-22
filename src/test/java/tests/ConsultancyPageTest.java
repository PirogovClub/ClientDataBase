package tests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.openqa.selenium.By;
import pageObjects.Consultancies;

public class ConsultancyPageTest extends BaseTest {

	By existingPageElement = By.xpath("//a[@class='btn btn-primary'][contains(text(),'consultancy')]");

	@Test
	public void testConsultancy() {
		try {
			try {
				this.setTargetPageUrl(utils.ReadConfigMain.getValueFromProperty("consultanciesUrl"));
				this.setTargetPageNameToTrace("Consltancies");
				this.setExistingPageElement(existingPageElement);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			GetToPage();
			Consultancies consultancies = new Consultancies(driver);
			// Get all text in column Name
			consultancies.createConsultancyButtonClick();
			existingPageElement = By.xpath("//input[@id='employeeRate.amount']");
			setExistingPageElement(existingPageElement);
			WaitForLoad();
			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = ThreadLocalRandom.current().nextInt(1, 20 + 1);

			consultancies.typeConsultancyTitle(utils.ReadConfigMain.getValueFromProperty("consultanciesTitle")+randomNum);
			consultancies.typeConsultancyDescription(utils.ReadConfigMain.getValueFromProperty("consultanciesDescription")+randomNum);
			consultancies.typePriceUAH(utils.ReadConfigMain.getValueFromProperty("consultanciesPriceUAH")+randomNum);
			consultancies.typePriceEUR(utils.ReadConfigMain.getValueFromProperty("consultanciesPriceEUR")+randomNum);
			consultancies.typePriceUSD(utils.ReadConfigMain.getValueFromProperty("consultanciesUSD")+randomNum);
			consultancies.typeEmployeeRate(utils.ReadConfigMain.getValueFromProperty("consultanciesEmployeeRate")+randomNum);
			consultancies.submitSaveButton();
			consultancies.clickHref(utils.ReadConfigMain.getValueFromProperty("consultanciesTitle")+randomNum);
			WaitForLoad();
			consultancies.clickdeleteButton();
			consultancies.waitModalDelete();
			consultancies.clickYesOnDeleteModalButton();
			existingPageElement = By.xpath("//a[@class='btn btn-primary'][contains(text(),'consultancy')]");
			setExistingPageElement(existingPageElement);
			WaitForLoad();
			
		} catch (Throwable e) {
			System.out.println("caught:\r\n" + e);
			fail("Test Failed");
		}
	}
}
