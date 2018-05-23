package tests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import pageObjects.Clients;
import java.util.concurrent.ThreadLocalRandom;


public class ClientsPageTest extends BaseTest {
	
	By existingPageElement = By.xpath("//button[@title='Обновить']");
	private Map<String,String> newClientMap = new HashMap<String,String>();
		
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
			int randomNum = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
			newClientMap.put("clientsTestFirstName",
					utils.ReadConfigMain.getValueFromProperty("clientsTestFirstName") + randomNum);
			newClientMap.put("clientsTestSecondName",
					utils.ReadConfigMain.getValueFromProperty("clientsTestSecondName") + randomNum);
			GetToPage(targetPageUrl);
			clients.createNewClient(newClientMap);
		
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


