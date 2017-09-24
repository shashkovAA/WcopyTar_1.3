package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.Debug;
import Objects.Disk;
import Objects.Property;
import Objects.SftpClient;

public class TestDisk
{
	
	Property property;
	@Before
    public void setUp() throws Exception {

	 	String settingsFileName = "config\\settings.xml";
		String logSettingsFileName = "config\\logging.xml";
		
		Debug.initDebugLog(logSettingsFileName);				
		property = new Property(settingsFileName);

	 	
    }

 @After
 public void tearDown() throws Exception {
	 /*File file = new File(client.getSftpDstFilePath() + ConvertNames.getFileExensiont(client.));
	 if (file.exists())
		 file.delete();*/
 }
	
	@Test
	public void test()
	{
		assertEquals(true, Disk.checkAvaliableSpace(property));	
	}

}
