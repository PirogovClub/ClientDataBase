package tests.mixed;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


import tests.deals.DealFromClientPageDbVsUI;
import tests.invoices.TestInvocesClientDataDbVsUI;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestInvocesClientDataDbVsUI.class,
	DealFromClientPageDbVsUI.class
  
})

public class TestClientFromInvoceAndHisDealsDBvsUI {
	
}
