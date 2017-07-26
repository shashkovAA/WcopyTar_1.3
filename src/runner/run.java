package runner;


import java.time.LocalTime;
import java.util.Properties;
import java.util.Scanner;


import Objects.Debug;
import Objects.MyCalendar;
import Objects.Property;
import Objects.SftpClient;
import Objects.Sheduler;
import Objects.ZipArchive;;

public class run {
	
	private static String settingsFileName;
	private LocalTime time;
			
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException
	{
		Debug.initDebugLog();
		Debug.log.info("Programm is started.");
		
		settingsFileName = "settings.xml";
		Property property = new Property();
		property = getUserSettingsToPropertyObjFromFile(settingsFileName);
				
		SftpClient client = new SftpClient();
		client.setUserSettings(property);
		
		ZipArchive zip = new ZipArchive();
		zip.setUserSettings(property);
	
		Sheduler sheduler = new Sheduler(property);
		
		/*TimerTask task = new TimerTask() { 

		    @Override
		    public void run() {
		         System.out.println("Поехали!!");
		         
		    }
		};
		
		Timer timer = new Timer();
		
		timer.schedule(task, 10000, 30000);
		timer.cancel();*/
		
	
	do {
		Debug.log.info("Checking time for work...");
		
		while (sheduler.isWorkTime()) {
			Debug.log.info("It's work time!");
			client.runConnect();
			zip.addFileToArchive();
			Debug.log.info("Waiting " + MyCalendar.getTimeFromMills(property.getIntervalSheduler()*1000));
			Thread.currentThread().sleep(property.getIntervalSheduler()*1000);			
		}		
		
		Debug.log.info("It's NOT work time.");
		
		Debug.log.info("Waiting " + MyCalendar.getTimeFromMills(sheduler.getBeginTimeoutToStart()));
		Thread.currentThread().sleep(sheduler.getBeginTimeoutToStart() + 1000);
		
	}
	while (true);
	
	}		
	
	public static Property getUserSettingsToPropertyObjFromFile(String settingsFileName)
	{
		Properties userProperties = UserProperties.getPropertiesFromXML(settingsFileName);
		Property property = new Property();
		
		property.setSftpIpAddr(userProperties.getProperty("ip"));
        Debug.log.debug("SFTP address from settings file: " + userProperties.getProperty("ip"));
        
        property.setSftpPort(userProperties.getProperty("port"));
        Debug.log.debug("SFTP port from settings file: " + userProperties.getProperty("port"));
        
        property.setSftpLogin(userProperties.getProperty("login"));
        Debug.log.debug("SFTP login from settings file: " + userProperties.getProperty("login"));
        
        property.setSftpPass(userProperties.getProperty("password"));
        Debug.log.debug("SFTP password from settings file: " + userProperties.getProperty("password"));
        
        property.setSftpSrcFilePath(userProperties.getProperty("srcfilepath"));
        Debug.log.debug("Source file path from settings file: " + userProperties.getProperty("srcfilepath"));
        
        property.setSftpSrcFileName(userProperties.getProperty("srcfilename"));
        Debug.log.debug("Source file name from settings file: " + userProperties.getProperty("srcfilename"));
        
        property.setSftpDestFilePath(userProperties.getProperty("dstfilepath"));
        Debug.log.debug("Destination file path from settings file: " + userProperties.getProperty("dstfilepath"));
 		
        property.setSftpDestFileName(userProperties.getProperty("dstfilename"));
        Debug.log.debug("Destination filename from settings file: " + userProperties.getProperty("dstfilename"));
        
        property.setBeginTimeSheduler(userProperties.getProperty("begintime"));
        Debug.log.debug("Begin work time from settings file: " + userProperties.getProperty("begintime"));
        
        property.setEndTimeSheduler(userProperties.getProperty("endtime"));
        Debug.log.debug("End work time from settings file: " + userProperties.getProperty("endtime"));
        
        property.setIntervalSheduler(userProperties.getProperty("workinterval"));
        Debug.log.debug("Interval time from settings file: " + userProperties.getProperty("workinterval"));
        
        property.setZipFileFullName(userProperties.getProperty("zipfilefullname"));
        Debug.log.debug("Zip archive name from settings file: " + userProperties.getProperty("zipfilefullname"));
        
        
        return property;
	}
	

	


}
