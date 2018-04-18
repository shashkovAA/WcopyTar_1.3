package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.Debug;
import Objects.Disk;
import Objects.Property;
import Objects.SftpClient;
import Objects.SizeFilesMap;

public class TestFilesNameMap
{
	
	Property property;
	@Before
    public void setUp() throws Exception {

	 	//String settingsFileName = "config\\settings.xml";
		String logSettingsFileName = "config\\logging.xml";
		
		Debug.initDebugLog(logSettingsFileName);				
		SizeFilesMap map = new SizeFilesMap();
		map.putHash("One", "1234567890");
		map.putHash("Two", "0987654321");
		map.printFilesMap();

	 	
    }

 @After
 public void tearDown() throws Exception {
	
 }
	
	@Test
	public void test()
	{
		assertEquals(true, true);	
	}

}
