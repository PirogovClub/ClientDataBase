package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class AssertWarp {
	protected static Logger logger = LogManager.getLogger();

	public static void assertToLog(boolean isTestPassed) {
		if (!isTestPassed) {
			logger.error("TEST FAILED");
			Assert.fail();
		}
		logger.info("TEST SUCCESSFULLY COMPLETED");
		// return isTestPassed;

	}

}
