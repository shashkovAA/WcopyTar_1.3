package Objects;

import java.io.File;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import runner.UserProperties;

//import objects.GlobalVars;

public class Debug {
    public static Logger log;

    public static void initDebugLog(String logSettingsFilePath) {
    
    Properties userProperties = UserProperties.getPropertiesFromXML(logSettingsFilePath);

    String currentPath =  new File("").getAbsolutePath();
    System.setProperty("logFileName", currentPath + "\\logs\\debug");
	System.setProperty("logFileLevel", userProperties.getProperty("logFileLevel"));
	System.setProperty("logFileSize", userProperties.getProperty("logFileSize"));
	System.setProperty("logFileCount", userProperties.getProperty("logFileCount"));
	System.setProperty("logLevelConsole", userProperties.getProperty("logLevelConsole"));

	reconfigure();

	log = LogManager.getLogger(Debug.class.getName());
    }
    
    public static void initDebugLog() {
    	String currentPath =  new File("").getAbsolutePath();
    	System.setProperty("logFileName", currentPath + "\\debug");
    	System.setProperty("logFileLevel", "info");
    	System.setProperty("logFileSize", "1 Mb");
    	System.setProperty("logFileCount", "5");
    	System.setProperty("logLevelConsole", "warn");

    	reconfigure();
    	log = LogManager.getLogger(Debug.class.getName());
    }

    public static void reconfigure() {
	((org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false)).reconfigure();
    }

    public static String getLogFileLevel() {
	return System.getProperty("logFileLevel");
    }

    public static String getLogFileSize() {
	return System.getProperty("logFileSize");
    }

    public static String getLogFileCount() {
	return System.getProperty("logFileCount");
    }

    public static String getLogLevelConsole() {
	return System.getProperty("logLevelConsole");
    }

    public static String getLogLevelAppConsole() {
	return System.getProperty("logLevelAppConsole");
    }

    public static void setLogLevelAppConsole(String appLogLevel) {
	switch (appLogLevel.toLowerCase()) {
	case "fatal":
	    System.setProperty("logLevelAppConsole", "fatal");
	    reconfigure();
	    Debug.log.info("Set logging level for Application Console to 'Fatal'");
	    break;

	case "error":
	    System.setProperty("logLevelAppConsole", "error");
	    reconfigure();
	    Debug.log.info("Set logging level for Application Console to 'Error'");
	    break;

	case "warning":
	    System.setProperty("logLevelAppConsole", "warn");
	    reconfigure();
	    Debug.log.info("Set logging level for Application Console to 'Warning'");
	    break;

	case "info":
	    System.setProperty("logLevelAppConsole", "info");
	    reconfigure();
	    Debug.log.info("Set logging level for Application Console to 'Info'");
	    break;

	case "debug":
	    System.setProperty("logLevelAppConsole", "debug");
	    reconfigure();
	    Debug.log.info("Set logging level for Application Console to 'Debug'");
	    break;
	}
    }

    public static void setLogFileLevel(String fileLogLevel) {

	System.setProperty("logFileLevel", fileLogLevel.toLowerCase());
	reconfigure();
	Debug.log.info("Set  file logging level  to " + fileLogLevel.toUpperCase());
    }

}
