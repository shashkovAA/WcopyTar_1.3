package Objects;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;

import runner.UserProperties;

public  class Property
{
	private  String sftpIpAddr ;
	private  int sftpPort ;
	private  String sftpLogin = "admin";
	private  String sftpPass = "password";
	private  int sftpSourceFileCount = 0;
	private  ArrayList<String> sftpSrcFullFileNamesList = new ArrayList<String>();  
	private  String sftpDestFilePath = "";
	private  LocalTime beginTimeSheduler;
	private  LocalTime endTimeSheduler;
	private  int intervalSheduler = 30 ;
	private String zipFileFullName;
	private boolean enableArchiving;
	private boolean enableNameMask;
	
	
	public Property() {};
	
	public Property(String settingsFileName) {
		Properties userProperties = UserProperties.getPropertiesFromXML(settingsFileName);
		
		setSftpSrcFileCount(userProperties.getProperty("srcfilecount"));
		int numOfFiles = getSftpSrcFileCount();
		Debug.log.debug("Number of files: " + numOfFiles);
		
		ArrayList<String> srcFileNamesList = new ArrayList<String>();
		for (int i=0;i<numOfFiles;i++) {
		srcFileNamesList.add(userProperties.getProperty("srcfullfilename" + (i+1)));
		Debug.log.debug("Source filename" + (i+1) + " : " + srcFileNamesList.get(i));
		}
		setSftpSrcFullFileNamesList(srcFileNamesList);
			
		setSftpIpAddr(userProperties.getProperty("ip"));
        Debug.log.debug("SFTP address from settings file: " + userProperties.getProperty("ip"));
        
        setSftpPort(userProperties.getProperty("port"));
        Debug.log.debug("SFTP port from settings file: " + userProperties.getProperty("port"));
        
        setSftpLogin(userProperties.getProperty("login"));
        Debug.log.debug("SFTP login from settings file: " + userProperties.getProperty("login"));
        
        setSftpPass(userProperties.getProperty("password"));
        Debug.log.debug("SFTP password from settings file: " + userProperties.getProperty("password"));
        
        setSftpSrcFullFileNamesList(srcFileNamesList);
              
        setEnableCopyFilesByNameMask(userProperties.getProperty("findingfilesbymaskofname"));
        Debug.log.debug("Enabling coping files by mask of name is :" + userProperties.getProperty("findingfilesbymaskofname"));
        
        setSftpDestFilePath(userProperties.getProperty("dstfilepath"));
        Debug.log.debug("Destination file path from settings file: " + userProperties.getProperty("dstfilepath"));
 		        
        setBeginTimeSheduler(userProperties.getProperty("begintime"));
        Debug.log.debug("Begin work time from settings file: " + userProperties.getProperty("begintime"));
        
        setEndTimeSheduler(userProperties.getProperty("endtime"));
        Debug.log.debug("End work time from settings file: " + userProperties.getProperty("endtime"));
        
        setIntervalSheduler(userProperties.getProperty("workinterval"));
        Debug.log.debug("Interval time from settings file: " + userProperties.getProperty("workinterval"));
        
        setZipFileFullName(userProperties.getProperty("zipfilefullname"));
        Debug.log.debug("Zip archive name from settings file: " + userProperties.getProperty("zipfilefullname"));
		
        setEnableArchiving(userProperties.getProperty("enablearchiving"));
        Debug.log.debug("Enabling add file(s) to archive is " + userProperties.getProperty("enablearchiving"));
		
	};
	

	public  String getSftpIpAddr()
	{
		return sftpIpAddr;
	}
	public  boolean setSftpIpAddr(String sftpIpAddr)
	{
		if (sftpIpAddr.matches("((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)")) {
			this.sftpIpAddr = sftpIpAddr;
			return true;
		}
			
		else {
			Debug.log.error("IP address have not correct format!");
			return false;
			
		}
	}
	public  int getSftpPort() {	
		return sftpPort;	 
	}
	

	public  boolean setSftpPort(String sftpPort)
	{	
		try {
			this.sftpPort = Integer.valueOf(sftpPort);
			return true;
		}	
		catch (NumberFormatException except) {
			Debug.log.error("Error format of port. Set port 22 by default.");
			this.sftpPort = 22;
			return false;
		}		
	}
	
	public  String getSftpLogin() {
		return sftpLogin;
	}
	public  void setSftpLogin(String sftpLogin) {
		this.sftpLogin = sftpLogin;
	}
	
	public  String getSftpPass() {
		return sftpPass;
	}
	public  void setSftpPass(String sftpPass) {
		this.sftpPass = sftpPass;
	}
	
	public  int getSftpSrcFileCount(){
		return sftpSourceFileCount;
	}
	public  void setSftpSrcFileCount(String sftpSrcFileCount) {
		try {
			this.sftpSourceFileCount = Integer.valueOf(sftpSrcFileCount);
			} 
			catch (NumberFormatException except) {
				Debug.log.error("Error format number of source files!");
				System.exit(0);
				this.intervalSheduler = 1;
			}
	}
	public  ArrayList<String> getSftpSrcFullFileNamesList() {
		return sftpSrcFullFileNamesList;
	}
	
	public  void setSftpSrcFullFileNamesList(ArrayList<String> sftpFullFileNamesList) {
		this.sftpSrcFullFileNamesList = sftpFullFileNamesList;
	}
	
	public  String getSftpDestFilePath() {
		return sftpDestFilePath;
	}
	
	public  void setSftpDestFilePath(String sftpDestFilePath) {
		this.sftpDestFilePath = sftpDestFilePath;
	}

	public  LocalTime getBeginTimeSheduler() {
		return beginTimeSheduler;
	}
	
	public  void setBeginTimeSheduler(String beginTimeSheduler)
	{
		if (isTimeStringCorrect(beginTimeSheduler))
			this.beginTimeSheduler = LocalTime.parse(beginTimeSheduler, MyCalendar.getTimeFormatWithSS());
		else {
			this.beginTimeSheduler = LocalTime.parse("00:00:00", MyCalendar.getTimeFormatWithSS());
			Debug.log.error("Begin time have not correct format");
			System.exit(0);
		}
	}
	public  LocalTime getEndTimeSheduler() {
		return endTimeSheduler;
	}
	
	public  void setEndTimeSheduler(String endTimeSheduler) {
		if (isTimeStringCorrect(endTimeSheduler))
			this.endTimeSheduler = LocalTime.parse(endTimeSheduler, MyCalendar.getTimeFormatWithSS());
		else {
			this.endTimeSheduler = LocalTime.parse("23:59:59", MyCalendar.getTimeFormatWithSS());
			Debug.log.error("End time have not correct format");
			System.exit(0);
		}
	}
	
	public  int getIntervalSheduler() {
		return intervalSheduler;
	}
	public  void setIntervalSheduler(String intervalSheduler) {
		try {
		this.intervalSheduler = Integer.valueOf(intervalSheduler);
		} 
		catch (NumberFormatException except) {
			Debug.log.error("Error format of interval. Set 300 seconds by default.");
			this.intervalSheduler = 300;
		}
	}
	
	public  boolean isTimeStringCorrect(String time) {
		
		return (time.matches("(0[0-9]|1[0-9]|2[0-3])\\:[0-5]\\d\\:[0-5]\\d")) ;	
	}
		
	public void setZipFileFullName(String zipFileFullName) {
		this.zipFileFullName = zipFileFullName;	
	}
	
	public String getZipFileFullName() {
		return zipFileFullName;
	}

	public void setEnableArchiving(String EnableArchiving) {
		this.enableArchiving = Boolean.valueOf(EnableArchiving);
		if (!(EnableArchiving.equals("true") || EnableArchiving.equals("false"))) {
			Debug.log.error("Not correct value for Enable Archiving!");
			Debug.log.error("Will use default value TRUE");
			this.enableArchiving = true;
		}	
	}
	public boolean getEnableArchiving() {
		return enableArchiving;	
	}
	
	public void setEnableCopyFilesByNameMask(String EnableNameMask) {
		this.enableNameMask = Boolean.valueOf(EnableNameMask);
		if (!(EnableNameMask.equals("true") || EnableNameMask.equals("false"))) {
			Debug.log.error("Not correct value for Coping Files by Mask of Name");
			Debug.log.error("Will use default value FALSE");
			this.enableNameMask = false;
		}	
	}
	public boolean getEnableCopyByMask() {
		return enableNameMask;	
	}
	
	

	
	
}
