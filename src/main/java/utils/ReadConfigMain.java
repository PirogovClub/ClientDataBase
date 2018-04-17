package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Properties;

public class ReadConfigMain {
	
	private static String currentPropName= null;
	private static final String propFileDirectory = "src/main/resources/config.properties";
	
	public static void SetCurrentParamName (String SetValue) {
		currentPropName=SetValue;
	}
	
  public static String getValueFromProperty(String key) throws IOException {
	Properties prop = new Properties();
	InputStream input = null;
	input = new FileInputStream(propFileDirectory);

	// load a properties file
	prop.load(input);

	// closing ImportStream
	input.close();
	return prop.getProperty(key);
		
	
	

  }
}
