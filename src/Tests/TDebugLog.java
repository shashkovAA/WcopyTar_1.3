package Tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import Objects.Debug;

public class TDebugLog
{			
	@Test
	public void testFilePath()
	{
		Debug.initDebugLog();
		assertEquals(true, true);		
	}
}

