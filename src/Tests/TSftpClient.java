package Tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.Debug;
import Objects.SftpClient;

public class TSftpClient {
	private SftpClient client;

	 @Before
	    public void setUp() throws Exception {
		 	Debug.initDebugLog();
		 	client = new SftpClient();
		 	
		 	client.setSftpIp("127.0.0.1");
		 	client.setSftpPort(22);
		 	client.setSftpLogin("wawa");
		 	client.setSftpPass("password");
		 	//client.setSftpSrcFilePath("/");
		 	//client.setSftpSrcFileNamesList;
		 	client.setSftpDstFilePath("G:\\copy\\");
		 	//client.setSftpDstFileName("test_copy.txt");
		 	client.runConnect();
	    }

	 @After
	 public void tearDown() throws Exception {
		 /*File file = new File(client.getSftpDstFilePath() + client.getSftpDstFileName());
		 if (file.exists())
			 file.delete();*/
	 }
	
	 @Test
	 public void testServerAvailable() {	
		assertEquals(true, client.isSftpServerAvailable);
		assertEquals(true, client.isCredentialsCorrect);
		assertEquals(true, client.isFileExist);	
	}
	 
}
