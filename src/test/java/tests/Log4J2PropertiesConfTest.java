package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import utils.Log4J2PropertiesConf;


public class Log4J2PropertiesConfTest {
    @Test
    public void testPerformSomeTask() throws Exception {
    	System.setProperty("log4j2.debug","INFO");
        Log4J2PropertiesConf log4J2PropertiesConf=new Log4J2PropertiesConf();
        log4J2PropertiesConf.performSomeTask();
    }
}
