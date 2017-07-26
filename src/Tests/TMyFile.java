package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.Debug;
import Objects.MyFile;

public class TMyFile
{
	MyFile myFile = new MyFile("G:\\copy\\test.zip");
	 @Before
	    public void setUp() throws Exception {
		 Debug.initDebugLog();
	    }

	 @After
	    public void tearDown() throws Exception {
	    }

	
	@Test
	public void testFilePath()	{	
		assertEquals("G:\\copy\\", myFile.getPath());
	}
	
	@Test
	public void testFileName()	{	
		assertEquals("test", myFile.getName());
	}
	
	@Test
	public void testFileExtension()	{	
		assertEquals(".zip", myFile.getExtension());
	}
	
	@Test
	public void testFullFileNameWithDate()	{	
		assertEquals("G:\\copy\\test_20170725.zip", myFile.getFullFileNameWithPathAndDate());
	}

}
