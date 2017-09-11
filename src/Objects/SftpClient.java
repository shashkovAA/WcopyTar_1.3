package Objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
	private  int sftpSrcFileCount;
	private  ArrayList<String> sftpSrcFullFileNamesList;
	private  String sftpDstFilePath;


		
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
            
            for (int i=0; i<sftpSrcFileCount; i++) {
            	sftpChannel.get(sftpSrcFullFileNamesList.get(i), sftpDstFilePath + ConvertNames.getFileNameWithExt(sftpSrcFullFileNamesList.get(i)));
            	Debug.log.info("Download file " + sftpSrcFullFileNamesList.get(i) + " is successfully!");
            }
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
    
    public ArrayList<String> remoteLs() {
    	ArrayList<String> copyFilesList  = new ArrayList<String>();
    	JSch js = new JSch();
        try {
	        session = js.getSession(sftpLogin, sftpIp, sftpPort);
	        session.setPassword(sftpPass);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	
	        Channel channel = session.openChannel("exec");
	        ChannelExec channelExec = (ChannelExec) channel;
	
	        channelExec.setCommand("ls");
	        channelExec.setErrStream(System.err);
	        channelExec.connect();
	
	        BufferedReader reader = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	copyFilesList.add(line);
	        	Debug.log.info(line);
	        }
	
	        channelExec.disconnect();
	        session.disconnect();
	        Debug.log.info("Exit code: " + channelExec.getExitStatus());
	        Debug.log.info("Number of files for copy :" + copyFilesList.size());
	        return copyFilesList;
	        
        } catch (JSchException except) {
            Debug.log.error("Sftp server refused connection!");
            return new ArrayList<String>();
        } catch (IOException except2) {
            Debug.log.error(except2.getMessage());
            return new ArrayList<String>();
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

	public  void setSftpSrcFileCount(int sftpFileCount){
		this.sftpSrcFileCount = sftpFileCount;
	}

	public  void setSftpSrcFullFileNameList(ArrayList<String> sftpFullFileNamesList){
		this.sftpSrcFullFileNamesList = sftpFullFileNamesList;
	}
	public  void setSftpDstFilePath(String sftpFilePath){
		this.sftpDstFilePath = sftpFilePath;
	}

	public String getSftpDstFilePath() {
		return sftpDstFilePath;
	}

/*	public String getSftpDstFileName() {
		return sftpDstFileName;
	}

	public  void setSftpDstFileName(String sftpFileName){
		this.sftpDstFileName = sftpFileName;
	}*/
	
	public void setUserSettings(Property prop) {
		this.sftpIp = prop.getSftpIpAddr();
		this.sftpPort = prop.getSftpPort();
		this.sftpLogin = prop.getSftpLogin();
		this.sftpPass = prop.getSftpPass();
		this.sftpSrcFileCount = prop.getSftpSrcFileCount();
		this.sftpSrcFullFileNamesList = prop.getSftpSrcFullFileNamesList();
		this.sftpDstFilePath = prop.getSftpDestFilePath();
					
	}

	
}
