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
	
	public void SetCurrentParamName (String SetValue) {
		currentPropName=SetValue;
	}
	
  public String GetParamFromProperties() {
	String OutProperty = null;
	Properties prop = new Properties();
	InputStream input = null;
	
	try {
		//Check if we know what to get from config File
		if (currentPropName == null) {
			
			throw new NullPointerException("No property To get from Param File, set property with SetCurrentParamName first");
		}
		input = new FileInputStream(propFileDirectory);

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		OutProperty = prop.getProperty(currentPropName);
		System.out.println("Read Succesfully");
		System.out.println(OutProperty);
		
		
	} catch (IOException ex) {
		ex.printStackTrace();
	} catch (NullPointerException ex) {
		System.out.println(ex);
  	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	return OutProperty;
	

  }
}
