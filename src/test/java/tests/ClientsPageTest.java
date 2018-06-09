package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.*;

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
	
	public void setClientMap(String firstName, String lastName, String email, String country, String  city, String phone, String skype) {
		ClientMap.put("firstName",firstName);
		ClientMap.put("lastName",lastName);
		ClientMap.put("email",email);
		ClientMap.put("country",country);
		ClientMap.put("city",city);
		ClientMap.put("phone",phone);
		ClientMap.put("skype",skype);
	}
	
	
	
	protected Map<String, String> getClientMap() {
		return ClientMap;
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
			
			String firstName	= utils.ReadConfigMain.getValueFromProperty("clientsTestFirstName")	+RandomData.getRandomInt(0, 10);
			String lastName		= utils.ReadConfigMain.getValueFromProperty("clientsTestSecondName") 	+RandomData.getRandomInt(0, 10);
			String email		= RandomData.getRandomString(5, LanguageSets.ENGLISH)+"@"+RandomData.getRandomString(5, LanguageSets.ENGLISH)+".com";
			String country		= RandomData.getRandomString(1, LanguageSets.ENGLISH_HIGH)+RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_LOW);
			String city			= RandomData.getRandomString(1, LanguageSets.ENGLISH_HIGH)+RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_LOW);
			String phone		= RandomData.getRandomString(10, LanguageSets.NUMBERS);
			String skype		= RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_AND_NUMBERS);
			
			
			clients.setNewClientMap(firstName,lastName,email,country,city,phone,skype);
			clientProperties.setNewClientMap(firstName,lastName,email,country,city,phone,skype);
			this.setClientMap(firstName,lastName,email,country,city,phone,skype);
			
			//Run actions
			
			mainNavigation.clickClient();
			clients.createNewClient();
			clientProperties.setSecondaryFieldsAndSave();
			clients.searchForClient(firstName+" "+lastName);
			clients.clickHrefWithText(firstName+" "+lastName);
			//clientProperties.readClientParamiters(false,"fake");
			//clientProperties.compareClientParamiters();
			try {
				assertEquals(getClientMap(),clientProperties.readClientParamiters());
			}
			catch (Throwable e) {
				System.out.println("Report Error" + e);
			}
			
			//Print comparation result
			//printOutMap(clientProperties.compareClientParamiters());
		
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


