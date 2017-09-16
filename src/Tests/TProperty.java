package Tests;

import static org.junit.Assert.assertEquals;

import Objects.Debug;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.Property;


public class TProperty
{
	Property prop = new Property();

	 @Before
	    public void setUp() throws Exception {
		 Debug.initDebugLog();
	    }

	 @After
	    public void tearDown() throws Exception {
	    }

	
	@Test
	public void testIPCorrect()
	{	
		assertEquals(true, prop.setSftpIpAddr("193.168.36.56"));	
	}
	
	@Test
	public void testIPIncorrect()
	{	
		assertEquals(false, prop.setSftpIpAddr("193.256.36.56"));	
	}
	
	@Test
	public void testPort()
	{	
		assertEquals(true, prop.setSftpPort("22"));	
	}
	
	@Test
	public void testTimeString()
	{	
		assertEquals(true, prop.isTimeStringCorrect("22:00:00"));	
	}
	
	@Test
	public void testBooleanString()
	{	
		prop.setEnableArchiving("true");
		assertEquals(true, prop.getEnableArchiving());	
	}
	
	@Test
	public void testBooleanString2()
	{	
		prop.setEnableCopyFilesByNameMask("true");
		assertEquals(true, prop.getEnableCopyByMask());	
	}
	
	

}
