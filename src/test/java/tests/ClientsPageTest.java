package tests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import pageObjects.Clients;
import utils.RandomData;

import java.util.concurrent.ThreadLocalRandom;


public class ClientsPageTest extends BaseTest {
	
	
	
		
	private void printList(List<String> NameList) {
		for (String listElement : NameList) {
			System.out.println(listElement);
		}
	}

	@Test
	public void testClients() {
		try { 
			Clients clients = new Clients(driver);
			this.setTargetExistingPageElement(clients.getTargetExistingPageElement());
			this.setTargetPageNameToTrace("Clients");
			this.setTargetPageUrl(utils.ReadConfigMain.getValueFromProperty("clientsUrl"));
			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			RandomData RandomData = new RandomData();
			RandomData.LanguageSets LanguageSets = null;
			clients.setNewClientParamiters(
					utils.ReadConfigMain.getValueFromProperty("clientsTestFirstName")	+RandomData.getRandomInt(0, 10)+RandomData.getRandomString(2, LanguageSets.RUSSIAN),
					utils.ReadConfigMain.getValueFromProperty("clientsTestSecondName") 	+RandomData.getRandomInt(0, 10)+RandomData.getRandomString(2, LanguageSets.ENGLISH_HIGH),
					"",
					"",
					"",
					"",
					"");
			
			GetToPage(targetPageUrl);
			clients.createNewClient();
			
			clients.setNewClientParamiters(
					utils.ReadConfigMain.getValueFromProperty("clientsTestFirstName")	+RandomData.getRandomInt(0, 10)+RandomData.getRandomString(5, LanguageSets.RUSSIAN),
					utils.ReadConfigMain.getValueFromProperty("clientsTestSecondName") 	+RandomData.getRandomInt(0, 10)+RandomData.getRandomString(5, LanguageSets.ENGLISH_HIGH),
					"",
					"",
					"",
					"",
					"");
			GetToPage(targetPageUrl);
			clients.createNewClient();
		
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


