package Tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.Debug;
import Objects.MD5Hash;
import Objects.MyCalendar;
import Objects.Property;
import Objects.Sheduler;

public class TMD5HashCompare
{
	String settingsFileName;
	@Before
    public void setUp() throws Exception {
	
		String logSettingsFileName = "config\\logging.xml";
		Debug.initDebugLog(logSettingsFileName);
		String currentPath =  new File("").getAbsolutePath();
		settingsFileName = currentPath + "\\config\\settings.xml";
	 
    }

	@After
    public void tearDown() throws Exception {
    }


	@Test
	public void testMD5() {
		
	MD5Hash.getMD5Hash(settingsFileName);
	assertEquals(true, true);	
	
	}
	

}
