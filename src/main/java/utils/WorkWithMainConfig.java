package utils;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorkWithMainConfig {
	
	private  String currentPropName= null;
	private  final String configPropFile = "src/main/resources/config.properties";
	private  final String dataPropFile = "src/main/resources/TestsData.properties";
	private  final String dbPropFile = "src/main/resources/Db.properties";
	private  Properties configProp = new Properties();
	private  Properties testDataProp = new Properties();
	private  Properties dbDataProp = new Properties();
	protected static Logger logger = LogManager.getLogger();
	
	public WorkWithMainConfig(){
		try {
			   InputStream input = new FileInputStream(configPropFile);
				// load a properties file
				configProp.load(input);
				input.close();
				
				input = new FileInputStream(dataPropFile);
				testDataProp.load(input);
				input.close();
				
				input = new FileInputStream(dbPropFile);
				dbDataProp.load(input);
				input.close();
				

		} catch (Throwable e) {
			logger.error("in "+this.getClass().getName()+" caught:\r\n" + e);
			fail("Test Failed");
		}
	}
	
	public String getConfigProp(String key) {
		return configProp.getProperty(key);
	}
	
	public String getTestDataProp(String key) {
		return testDataProp.getProperty(key);
	}
	
	public String getDbDataProp(String key) {
		return dbDataProp.getProperty(key);
	}

	public  Object setTestDataProp(String key, String value) {
		return testDataProp.setProperty(key, value);
	}

	public void saveTestDataPropToFile() throws IOException {
		FileOutputStream output = new FileOutputStream(dataPropFile);
		// save a properties To file
		testDataProp.store(output, "check it out");
		// closing ImportStream
		output.close();
	}
  
}
