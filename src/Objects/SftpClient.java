package Objects;

import java.util.Properties;

import com.jcraft.jsch.*;
 

public class SftpClient {
	/*
	 * Test Flags
	 */
	public boolean isSftpServerAvailable = false;
	public boolean isCredentialsCorrect = false;
	public boolean isFileExist = false;
	
	private  Session session = null;
	private  Channel channel = null;
	private  ChannelSftp sftpChannel = null;
	private  String sftpIp;
	private  int sftpPort;
	private  String sftpLogin;
	private  String sftpPass;
	private  String sftpSrcFilePath;
	private  String sftpSrcFileName;
	private  String sftpDstFilePath;
	private  String sftpDstFileName;

		
    public void runConnect() {
    	
    	Debug.log.debug("Start SFTP Client.");
    	
    	JSch jsch = new JSch();
  
        try {
            session = jsch.getSession(sftpLogin, sftpIp, sftpPort);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(sftpPass);
            
            session.connect();
            Debug.log.debug("Connected to "+ sftpIp + ".");
            isSftpServerAvailable = true;
            isCredentialsCorrect = true;

            channel = session.openChannel("sftp");
            channel.connect();
            
            sftpChannel = (ChannelSftp) channel;
            sftpChannel.cd(sftpSrcFilePath);	
            sftpChannel.get(sftpSrcFileName, sftpDstFilePath + sftpDstFileName);
            
            Debug.log.info("Download file " + sftpSrcFileName + " is successfully!");
            isFileExist = true;
            
            sftpChannel.exit();
            channel.disconnect();
            session.disconnect();
            
            
        } catch (JSchException except) {
            Debug.log.error("Sftp server refused connection!");
        } catch (SftpException except2) {
        	Debug.log.error("File download failed!");
            Debug.log.error(except2.getMessage());
        } catch (Exception except3) {
            Debug.log.error(except3.getMessage());
        } 
        finally {
        	
        	try { 
        		if (sftpChannel.isConnected()) {
        			sftpChannel.exit();
        			channel.disconnect();
        			session.disconnect();
        			Debug.log.info("Disconnected from " + sftpIp + ".");
        			
        	}
        	} catch (NullPointerException except) {
        		Debug.log.error("Sftp server is not available.");
        		isSftpServerAvailable = false;
        	
        	}
        
        }
        Debug.log.debug("Stop SFTP Client.");
    }
    
    public  void setSftpIp(String sftpIp){
		this.sftpIp = sftpIp;
	}

	public  void setSftpPort(int sftpPort){
		this.sftpPort = sftpPort;
	}

	public  void setSftpLogin(String sftpLogin){
		this.sftpLogin = sftpLogin;
	}

	public  void setSftpPass(String sftpPass){
		this.sftpPass = sftpPass;
	}

	public  void setSftpSrcFilePath(String sftpFilePath){
		this.sftpSrcFilePath = sftpFilePath;
	}

	public  void setSftpSrcFileName(String sftpFileName){
		this.sftpSrcFileName = sftpFileName;
	}
	public  void setSftpDstFilePath(String sftpFilePath){
		this.sftpDstFilePath = sftpFilePath;
	}

	public String getSftpDstFilePath() {
		return sftpDstFilePath;
	}

	public String getSftpDstFileName() {
		return sftpDstFileName;
	}

	public  void setSftpDstFileName(String sftpFileName){
		this.sftpDstFileName = sftpFileName;
	}
	
	public void setUserSettings(Property prop) {
		this.sftpIp = prop.getSftpIpAddr();
		this.sftpPort = prop.getSftpPort();
		this.sftpLogin = prop.getSftpLogin();
		this.sftpPass = prop.getSftpPass();
		this.sftpSrcFilePath = prop.getSftpSrcFilePath();
		this.sftpSrcFileName = prop.getSftpSrcFileName();
		this.sftpDstFilePath = prop.getSftpDestFilePath();
		this.sftpDstFileName = prop.getSftpDestFileName();
				
		
	}

	
}
