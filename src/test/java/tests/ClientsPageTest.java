package tests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import pageObjects.Clients;
import java.util.concurrent.ThreadLocalRandom;


public class ClientsPageTest extends BaseTest {
	
	By existingPageElement = By.xpath("//button[@title='Обновить']");
		
	private void printList(List<String> NameList) {
		for (String listElement : NameList) {
			System.out.println(listElement);
		}
	}

	@Test
	public void testClients() {
		try { 
			try {
				this.setTargetPageUrl(utils.ReadConfigMain.getValueFromProperty("clientsUrl"));
				this.setTargetPageNameToTrace("Clients");
				this.setExistingPageElement(existingPageElement);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GetToPage();
			
			Clients clients = new Clients(driver);
			
			

			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
			
			clients.createClientButtonClick();
			clients.typeNewClientName(utils.ReadConfigMain.getValueFromProperty("clientsTestFirstName")+randomNum);
			clients.typeNewClientLastName(utils.ReadConfigMain.getValueFromProperty("clientsTestSecondName")+randomNum);
			clients.saveNewClientNameClick();
			
		} catch (Throwable e) { 
	          System.out.println("caught:\r\n" + e);
	          fail("Test Failed");
		} 
	}
}


