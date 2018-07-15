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


public class ClientUpdateTest extends BaseTest {
	
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
			Map<String,String> resultMap = new HashMap<String,String>();
			Map<String,String> existingClientMap = new HashMap<String,String>();
			Clients clients = new Clients(driver);
			MainNavigation mainNavigation = new MainNavigation(driver);
			ClientProperties clientProperties = new ClientProperties(driver);
			
			//Setup data
			RandomData RandomData = new RandomData();
			RandomData.LanguageSets LanguageSets = null;
			
			String firstName	= config.getTestDataProp("clientsTestFirstName");
			String lastName		= config.getTestDataProp("clientsTestSecondName");
			String email		= "";
			String country		= "";
			String city			= "";
			String phone		= "";
			String skype		= "";
			
			
			//Run actions
			
			mainNavigation.clickClient();
			clients.searchForClient(firstName+" "+lastName);
			clients.clickHrefWithText(firstName+" "+lastName);
			existingClientMap=clientProperties.readClientParamitersFromPage();
			
			firstName	= config.getTestDataProp("clientsTestFirstName")+RandomData.getRandomInt(0, 10);
			lastName	= existingClientMap.get("lastName");
			email		= existingClientMap.get("email");;
			country		= RandomData.getRandomString(1, LanguageSets.ENGLISH_HIGH)+RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_LOW);
			city		= RandomData.getRandomString(1, LanguageSets.ENGLISH_HIGH)+RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_LOW);
			phone		= RandomData.getRandomString(10, LanguageSets.NUMBERS);
			skype		= RandomData.getRandomString(RandomData.getRandomInt(1, 49), LanguageSets.ENGLISH_AND_NUMBERS);
			
			clientProperties.setNewClientMap(firstName,lastName,email,country,city,phone,skype);
			clientProperties.setSecondaryFieldsAndSave();
			//mainNavigation.clickClient();
			clients.searchForClient(firstName+" "+lastName);
			clients.clickHrefWithText(firstName+" "+lastName);
			existingClientMap=clientProperties.readClientParamitersFromPage();
			clientProperties.readClientParamitersFromDb();
			//Compare with what find on page, can throw TestFail here
			resultMap= clientProperties.compareClientParamitersWithPage();
			//Trace result of compare
			//printOutMap(resultMap,"Comapre With Page");
			//Compare with Db, can throw TestFail here
			resultMap= clientProperties.compareClientParamitersWithDb();
			//Trace result of compare
			printOutMap(resultMap,"Comapre With Db");
			
			//Save Set FirstName and SecondName to propfile
			config.setTestDataProp("clientsTestFirstName",firstName);
			config.setTestDataProp("clientsTestSecondName",lastName);
			config.saveTestDataPropToFile();
			
		
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


