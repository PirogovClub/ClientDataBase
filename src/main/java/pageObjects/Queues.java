/**
 * 
 */
package pageObjects;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.HtmlTable;

/**
 * @author pirog
 *
 */
public class Queues extends BasePOM{
	
	By mentoringTab = By.xpath(".//p[contains(text(),'Mentoring')]");
	By tableWithContractsOnMentoring = By.xpath(".//div[@id='tab-1']//table[@class='table table-hover']");
	protected HtmlTable mentoringContracts;
	
	public Queues(WebDriver driver) {
		// TODO Auto-generated constructor stub
			setDriver( driver);
	}

	public void clickMetnoringTab() {
		clickOnElement(mentoringTab);
	}
	
	public HtmlTable readTableWithContractsOnMentoring() {
		mentoringContracts = new HtmlTable(tableWithContractsOnMentoring, driver);
		return mentoringContracts;
	}
	
}
