package Tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jcraft.jsch.JSchException;

import Objects.ConvertNames;
import Objects.Debug;
import Objects.Property;
import Objects.SftpClient;
import runner.Run;

public class TSftpClient2 {
	private SftpClient client;
	private Property prop;

	 @Before
	    public void setUp() throws Exception {
		 	client = new SftpClient();
		 	String settingsFileName = "config\\settings.xml";
			String logSettingsFileName = "config\\logging.xml";
			
			Debug.initDebugLog(logSettingsFileName);				
			Property prop = new Property(settingsFileName);
			
		 	client.setUserSettings(prop);
		 	
	    }

	 @After
	 public void tearDown() throws Exception {
		 /*File file = new File(client.getSftpDstFilePath() + ConvertNames.getFileExensiont(client.));
		 if (file.exists())
			 file.delete();*/
	 }
	
	 @Test
	 public void testServerAvailable() {
		client.copyFiles();
		assertEquals(true, client.isSftpServerAvailable);
		assertEquals(true, client.isCredentialsCorrect);
		assertEquals(true, client.isFileExist);	
	}
	 
	 /*@Test
	 public void testSFTPServerCommandLS() {
		
		  ArrayList<String> filesList; 
		  filesList = client.remoteLs("*.mta2");
	
		assertEquals(false, filesList.isEmpty());
	}*/
	 
}
