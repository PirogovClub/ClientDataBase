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
import pageObjects.MainNavigation;
import utils.RandomData;

import java.util.concurrent.ThreadLocalRandom;


public class ClientsPageTest extends BaseTest {
	
	private Map<String,String> ClientMap = new HashMap<String,String>();
		
	private void printList(List<String> NameList) {
		for (String listElement : NameList) {
			System.out.println(listElement);
		}
	}
	
	
	@Test
	public void testClients() {
		try {
			
			//Setup Objects
			
			Clients clients = new Clients(driver);
			MainNavigation mainNavigation = new MainNavigation(driver);
			ClientProperties clientProperties = new ClientProperties(driver);
			
			//Setup data
			RandomData RandomData = new RandomData();
			RandomData.LanguageSets LanguageSets = null;
			
			ClientMap.put("firstName",utils.ReadConfigMain.getValueFromProperty("clientsTestFirstName")	+RandomData.getRandomInt(0, 10));
			ClientMap.put("lastName",utils.ReadConfigMain.getValueFromProperty("clientsTestSecondName") 	+RandomData.getRandomInt(0, 10));
			ClientMap.put("email",RandomData.getRandomString(5, LanguageSets.ENGLISH)+"@"+RandomData.getRandomString(5, LanguageSets.ENGLISH)+".com");
			ClientMap.put("country",RandomData.getRandomString(1, LanguageSets.ENGLISH_HIGH)+RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_LOW));
			ClientMap.put("city",RandomData.getRandomString(1, LanguageSets.ENGLISH_HIGH)+RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_LOW));
			ClientMap.put("phone",RandomData.getRandomString(10, LanguageSets.NUMBERS));
			ClientMap.put("skype",RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_AND_NUMBERS));
			
			clients.setNewClientMap(ClientMap);
			clientProperties.setNewClientMap(ClientMap);
			
			//Run actions
			
			mainNavigation.clickClient();
			clients.createNewClient();
			clientProperties.setSecondaryFieldsAndSave();
			clients.findNewClientAndOpen();
			clientProperties.readClientParamiters(false,"fake");
			clientProperties.compareClientParamiters();
			
			
			//Print comparation result
			//printOutMap(clientProperties.compareClientParamiters());
		
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


