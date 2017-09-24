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
	private  ArrayList<String> sftpSrcFullFileNamesList = new ArrayList<String>();  
	private  String sftpDestFilePath = "";
	private  LocalTime beginTimeSheduler;
	private  LocalTime endTimeSheduler;
	private  int sftpSourceFileCount = 0;
	private  int intervalSheduler = 30 ;
	private long minfreediskspaceMB;	
	private String zipFileFullName;
	private boolean enableArchiving;
	private boolean enableNameMask;
	
	
	public Property() {};
	
	public Property(String settingsFileName) {
		Properties userProperties = UserProperties.getPropertiesFromXML(settingsFileName);
		
		setSftpSrcFileCount(userProperties.getProperty("srcfilecount"));
		int numOfFiles = getSftpSrcFileCount();
		Debug.log.debug("[srcfilecount] = " + numOfFiles);
		
		ArrayList<String> srcFileNamesList = new ArrayList<String>();
		for (int i=0;i<numOfFiles;i++) {
		srcFileNamesList.add(userProperties.getProperty("srcfullfilename" + (i+1)));
		Debug.log.debug("[srcfullfilename" + (i+1) + "]  = " + srcFileNamesList.get(i));
		}
		setSftpSrcFullFileNamesList(srcFileNamesList);
			
		setSftpIpAddr(userProperties.getProperty("ip"));
        Debug.log.debug("[ip] = " + userProperties.getProperty("ip"));
        
        setSftpPort(userProperties.getProperty("port"));
        Debug.log.debug("[port] = " + userProperties.getProperty("port"));
        
        setSftpLogin(userProperties.getProperty("login"));
        Debug.log.debug("[login] = " + userProperties.getProperty("login"));
        
        setSftpPass(userProperties.getProperty("password"));
        Debug.log.debug("[password] = " + userProperties.getProperty("password"));
        
        setSftpSrcFullFileNamesList(srcFileNamesList);
              
        setEnableCopyFilesByNameMask(userProperties.getProperty("findingfilesbymaskofname"));
        Debug.log.debug("[findingfilesbymaskofname] = " + userProperties.getProperty("findingfilesbymaskofname"));
        
        setSftpDestFilePath(userProperties.getProperty("dstfilepath"));
        Debug.log.debug("[dstfilepath] = " + userProperties.getProperty("dstfilepath"));
 		        
        setBeginTimeSheduler(userProperties.getProperty("begintime"));
        Debug.log.debug("[begintime] = " + userProperties.getProperty("begintime"));
        
        setEndTimeSheduler(userProperties.getProperty("endtime"));
        Debug.log.debug("[endtime] = " + userProperties.getProperty("endtime"));
        
        setIntervalSheduler(userProperties.getProperty("workinterval"));
        Debug.log.debug("[workinterval] = " + userProperties.getProperty("workinterval") + " sec");
        
        setZipFileFullName(userProperties.getProperty("zipfilefullname"));
        Debug.log.debug("[zipfilefullname] = " + userProperties.getProperty("zipfilefullname"));
		
        setEnableArchiving(userProperties.getProperty("enablearchiving"));
        Debug.log.debug("[enablearchiving] = " + userProperties.getProperty("enablearchiving"));
        
        setMinFreeDiskSpace(userProperties.getProperty("minfreediskspaceMB"));
        Debug.log.debug("[minfreediskspaceMB] = " + userProperties.getProperty("minfreediskspaceMB"));
        		
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
			Debug.log.error("Error format for [ip]. Correct value and start programm again!");
			System.exit(0);
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
			Debug.log.error("Error format for [port] = " + sftpPort + ". Set default value = [22]");
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
				Debug.log.error("Error format for [srcfilecount]. Correct value and start programm again!");
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
			Debug.log.error("Error format for [begintime]. Set default value = [00:00:00]");
			//System.exit(0);
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
			Debug.log.error("Error format for [endtime]. Set default value = [23:59:59]");
			//System.exit(0);
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
			Debug.log.error("Error format for [interval]. Set default value = [300]");
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
			Debug.log.error("Error format for [enablearchiving]. Set default value = [true]");
			this.enableArchiving = true;
		}	
	}
	public boolean getEnableArchiving() {
		return enableArchiving;	
	}
	
	public void setEnableCopyFilesByNameMask(String EnableNameMask) {
		this.enableNameMask = Boolean.valueOf(EnableNameMask);
		if (!(EnableNameMask.equals("true") || EnableNameMask.equals("false"))) {
			Debug.log.error("Error format for [findingfilesbymaskofname]. Set default value = [false]");
			this.enableNameMask = false;
		}	
	}
	public boolean getEnableCopyByMask() {
		return enableNameMask;	
	}
	
	public void setMinFreeDiskSpace(String spaceString)
	{
		try {
			this.minfreediskspaceMB = Long.valueOf(spaceString);
			} 
			catch (NumberFormatException except) {
				Debug.log.error("Error format of [minFreeDiskSpace]. Correct value and start programm again!");
				System.exit(0);
			}
	}
	
	public long getMinFreeDiskSpace() {
		return minfreediskspaceMB;
	}

	
	
}
