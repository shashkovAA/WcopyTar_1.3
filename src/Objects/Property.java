package Objects;

import java.time.LocalTime;

public  class Property
{
	private  String sftpIpAddr ;
	private  int sftpPort ;
	private  String sftpLogin = "admin";
	private  String sftpPass = "password";
	private  String sftpSourceFilePath = "";
	private  String sftpSourceFileName = "test.txt";
	private  String sftpDestFilePath = "";
	private  String sftpDestFileName = "test.txt";
	private  LocalTime beginTimeSheduler;
	private  LocalTime endTimeSheduler;
	private  int intervalSheduler = 30 ;
	private String zipFileFullName;
	
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
	
	public  String getSftpLogin(){
		return sftpLogin;
	}
	public  void setSftpLogin(String sftpLogin){
		this.sftpLogin = sftpLogin;
	}
	public  String getSftpPass(){
		return sftpPass;
	}
	public  void setSftpPass(String sftpPass){
		this.sftpPass = sftpPass;
	}
	public  String getSftpSrcFilePath(){
		return sftpSourceFilePath;
	}
	public  void setSftpSrcFilePath(String sftpFilePath)
	{
		this.sftpSourceFilePath = sftpFilePath;
	}
	public  String getSftpSrcFileName()
	{
		return sftpSourceFileName;
	}
	public  void setSftpSrcFileName(String sftpFileName)
	{
		this.sftpSourceFileName = sftpFileName;
	}
	public  String getSftpDestFilePath()
	{
		return sftpDestFilePath;
	}
	public  void setSftpDestFilePath(String sftpDestFilePath)
	{
		this.sftpDestFilePath = sftpDestFilePath;
	}
	public  String getSftpDestFileName()
	{
		return sftpDestFileName;
	}
	public  void setSftpDestFileName(String sftpDestFileName)
	{
		this.sftpDestFileName = sftpDestFileName;
	}
	public  LocalTime getBeginTimeSheduler()
	{
		return beginTimeSheduler;
	}
	
	public  void setBeginTimeSheduler(String beginTimeSheduler)
	{
		if (isTimeStringCorrect(beginTimeSheduler))
			this.beginTimeSheduler = LocalTime.parse(beginTimeSheduler, MyCalendar.getTimeFormatWithSS());
		else {
			this.beginTimeSheduler = LocalTime.parse("00:00:00", MyCalendar.getTimeFormatWithSS());
			Debug.log.error("Begin time have not correct format");
		}
	}
	public  LocalTime getEndTimeSheduler()
	{
		return endTimeSheduler;
	}
	
	public  void setEndTimeSheduler(String endTimeSheduler)
	{
		if (isTimeStringCorrect(endTimeSheduler))
			this.endTimeSheduler = LocalTime.parse(endTimeSheduler, MyCalendar.getTimeFormatWithSS());
		else {
			this.endTimeSheduler = LocalTime.parse("23:59:59", MyCalendar.getTimeFormatWithSS());
			Debug.log.error("End time have not correct format");
		}
	}
	
	public  int getIntervalSheduler()
	{
		return intervalSheduler;
	}
	public  void setIntervalSheduler(String intervalSheduler)
	{
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
	public String getZipFileFullName()
	{
		return zipFileFullName;
	}

	
}
