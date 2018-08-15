package tests;

import org.junit.Test;

import utils.Log4J2PropertiesConf;

import static org.junit.Assert.*;

public class Log4J2PropertiesConfTest {
    @Test
    public void testPerformSomeTask() throws Exception {
    	System.setProperty("log4j2.debug","INFO");
        Log4J2PropertiesConf log4J2PropertiesConf=new Log4J2PropertiesConf();
        log4J2PropertiesConf.performSomeTask();
    }
}
