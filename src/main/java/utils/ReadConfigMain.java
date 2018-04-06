package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Properties;

public class ReadConfigMain {
	
  public static String GetUrlFromProperties() {
	String UrlProperty = null;
	Properties prop = new Properties();
	InputStream input = null;
	
	try {
		
		input = new FileInputStream("src/main/resources/config.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		UrlProperty = prop.getProperty("openurl");
		System.out.println("Read Succesfully");
		System.out.println(UrlProperty);
		
		
	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	return UrlProperty;
	

  }
}
