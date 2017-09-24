package runner;


import java.io.File;

import Objects.ConvertNames;
import Objects.Debug;
import Objects.Disk;
import Objects.MyCalendar;
import Objects.Property;
import Objects.SftpClient;
import Objects.Sheduler;
import Objects.ZipArchive;;

public class run {
	
	
			
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
				
		String currentPath =  new File("").getAbsolutePath();	
		String settingsFileName = currentPath + "\\config\\settings.xml";
		String logSettingsFileName = currentPath + "\\config\\logging.xml";
		
		Debug.initDebugLog(logSettingsFileName);
		Debug.log.info("Programm is started.");
		
		Property property = new Property(settingsFileName);
				
		SftpClient sftpConnect = new SftpClient();
		sftpConnect.setUserSettings(property);
		
		ZipArchive zipper = new ZipArchive();
		zipper.setUserSettings(property);
	
		Sheduler sheduler = new Sheduler(property);
				
	do {
		Debug.log.info("Checking time for work...");
		
		while (sheduler.isWorkTime()) {
			Debug.log.info("It's work time!");
			if (Disk.checkAvaliableSpace(property))
				sftpConnect.copyFiles();
			if (property.getEnableArchiving())
				zipper.addFileToArchive();
			Debug.log.info("Waiting " + MyCalendar.getTimeFromMills(property.getIntervalSheduler()*1000));
			Thread.currentThread().sleep(property.getIntervalSheduler()*1000);			
		}		
		
		Debug.log.info("It's NOT work time.");
		
		Debug.log.info("Waiting " + MyCalendar.getTimeFromMills(sheduler.getBeginTimeoutToStart()));
		Thread.currentThread().sleep(sheduler.getBeginTimeoutToStart() + 1000);
		
	}
	while (true);
	
	}
	

}
