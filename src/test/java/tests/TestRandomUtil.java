package tests;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.RandomData;

public class TestRandomUtil {
	protected static Logger logger = LogManager.getLogger();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomData RandomGenerator = new RandomData();
		RandomData.LanguageSets LanguageSets = null;
		logger.debug(RandomGenerator.getRandomString(10, LanguageSets.ENGLISH_HIGH));
		logger.debug(RandomGenerator.getRandomInt(-1000, 10));
		logger.debug(RandomGenerator.getRandomBoolean());
		logger.debug(RandomGenerator.getRandomFloat(2, 10, 2));
	}

}
