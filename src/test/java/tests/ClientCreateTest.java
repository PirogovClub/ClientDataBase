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
import utils.PrintOuts;
import utils.RandomData;
import utils.WorkWithMainConfig;

import java.util.concurrent.ThreadLocalRandom;


public class ClientCreateTest extends BaseTest {
	
	private Map<String,String> ClientMap = new HashMap<String,String>();
		
	private void printList(List<String> NameList) {
		for (String listElement : NameList) {
			logger.debug(listElement);
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
	public void createClient() {
		try {
			setChieldTestModuleName(this.getClass().getName());
			logger.info("Get into Test" + this.getClass().getName());
			//Setup Objects
			Map<String,String> resultMap = new HashMap<String,String>();
			
			Clients clients = new Clients(driver);
			MainNavigation mainNavigation = new MainNavigation(driver);
			ClientProperties clientProperties = new ClientProperties(driver);
			
			
			//Setup data
			RandomData RandomData = new RandomData();
			RandomData.LanguageSets LanguageSets = null;
			
			String firstName	= config.getTestDataProp("clientsTestFirstName")	+RandomData.getRandomInt(0, 10);
			String lastName		= config.getTestDataProp("clientsTestSecondName") 	+RandomData.getRandomInt(0, 10);
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
			clientProperties.readClientParamitersFromPage(false,"fake");
			clientProperties.readClientParamitersFromDb();
			//Compare with what find on page, can throw TestFail here
			resultMap= clientProperties.compareClientParamitersWithPage();
			PrintOuts printOut = new PrintOuts();
			//Trace result of compare
			printOut.doMap(resultMap,"Comapre With Page");
			//Compare with Db, can throw TestFail here
			resultMap= clientProperties.compareClientParamitersWithDb();
			
			checkIsTestPassed(clientProperties.checkIfComparationIsNotOK(resultMap));
			//Trace result of compare
			printOut.doMap(resultMap,"Compare With Db");
			
			//Save Set FirstName and SecondName to propfile
			config.setTestDataProp("clientsTestFirstName",firstName);
			config.setTestDataProp("clientsTestSecondName",lastName);
			config.saveTestDataPropToFile();
			
			
		
		} catch (Throwable e) { 
			logger.error("in "+this.getClass().getName()+" caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


