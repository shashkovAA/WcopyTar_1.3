package runner;


import java.io.File;

import Objects.*;

public class Run {
	
		
	@SuppressWarnings("static-access")
	public void start() throws InterruptedException {
		
		String currentPath =  new File("").getAbsolutePath();	
		String settingsFileName = currentPath + "\\config\\settings.xml";
		String logSettingsFileName = currentPath + "\\config\\logging.xml";
		
		Debug.initDebugLog(logSettingsFileName);
		Debug.log.info("Programm is started.");
		
		Property property = new Property(settingsFileName);
				
		FilesComparer comparer = new FilesComparer();
		comparer.setUserSettings(property);
		
		SftpClient sftpConnect = new SftpClient();
		sftpConnect.setUserSettings(property);
		sftpConnect.setFilesComparerLink(comparer);
		
		
		ZipArchive zipper = new ZipArchive();
		zipper.setUserSettings(property);
		zipper.setFilesComparerLink(comparer);
	
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
			
		
	public static void main(String[] args)  {				
		try {			
			Run wcopytar = new Run();
			wcopytar.start();		
		} catch (InterruptedException e) {			
			Debug.log.error(e.getMessage());
		}
	}
}
