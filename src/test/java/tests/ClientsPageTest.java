package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import pageObjects.ClientProperties;
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
					utils.ReadConfigMain.getValueFromProperty("clientsTestFirstName")	+RandomData.getRandomInt(0, 10),
					utils.ReadConfigMain.getValueFromProperty("clientsTestSecondName") 	+RandomData.getRandomInt(0, 10),
					RandomData.getRandomString(5, LanguageSets.ENGLISH)+"@"+RandomData.getRandomString(5, LanguageSets.ENGLISH)+".com",
					RandomData.getRandomString(1, LanguageSets.ENGLISH_HIGH)+RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_LOW),
					RandomData.getRandomString(1, LanguageSets.ENGLISH_HIGH)+RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_LOW),
					RandomData.getRandomString(10, LanguageSets.NUMBERS),
					RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_AND_NUMBERS));
			
			GetToPage(targetPageUrl);
			//Creating new client
			clients.createNewClient();
			//Fill all paramiters for new client
			ClientProperties clientProperties = new ClientProperties(driver);
			clientProperties.setNewClientMap(clients.getNewClientMap());
			WaitForLoad(clientProperties.getTargetExistingPageElement());
			clientProperties.setSecondaryFieldsAndSave();
			WaitForLoad(clients.getTargetExistingPageElement());
			//find client and open properties
			clients.findNewClientAndOpen();
			WaitForLoad(clientProperties.getTargetExistingPageElement());
			//read paramiters
			clientProperties.readClientParamiters(true,"fake");
			
			clientProperties.makeAssert("FirstName");
			clientProperties.makeAssert("lastName");
			clientProperties.makeAssert("email");
			clientProperties.makeAssert( "country");
			clientProperties.makeAssert("city");
			clientProperties.makeAssert("phone");
			clientProperties.makeAssert("skype");
			
			
		
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


