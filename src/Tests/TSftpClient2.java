package Tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.Debug;
import Objects.Property;
import Objects.SftpClient;
import runner.run;

public class TSftpClient2 {
	private SftpClient client;
	private Property prop;

	 @Before
	    public void setUp() throws Exception {
		 	Debug.initDebugLog();
		 	client = new SftpClient();
		 	prop = run.getUserSettingsToPropertyObjFromFile("settings.xml");
		 	client.setUserSettings(prop);
		 	client.runConnect();
	    }

	 @After
	 public void tearDown() throws Exception {
		 File file = new File(client.getSftpDstFilePath() + client.getSftpDstFileName());
		 if (file.exists())
			 file.delete();
	 }
	
	 @Test
	 public void testServerAvailable() {	
		assertEquals(true, client.isSftpServerAvailable);
		assertEquals(true, client.isCredentialsCorrect);
		assertEquals(true, client.isFileExist);	
	}
	 
}
