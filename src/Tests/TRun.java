package Tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.LocalTime;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.MyCalendar;
import Objects.Debug;
import runner.run;


public class TRun
{
	private String beginTimeInput = "00:10:00";
	private String endTimeInput = "00:20:00";
	
	private static LocalTime beginTime;
	private static LocalTime endTime;	
	private volatile static LocalTime nowTime;
	private static long diffInMilli;
	
	 @Before
	    public void setUp() throws Exception {
		 Debug.initDebugLog();
		beginTime = LocalTime.parse(beginTimeInput, MyCalendar.getTimeFormatWithSS());
		endTime = LocalTime.parse(endTimeInput, MyCalendar.getTimeFormatWithSS());	
		nowTime = LocalTime.parse(MyCalendar.getCurrentTimeNowWithSS(),MyCalendar.getTimeFormatWithSS());
	    }

	 @After
	    public void tearDown() throws Exception {
	    }

	
	@Test
	public void testBegin()
	{	
		assertEquals(true, nowTime.isBefore(beginTime));	
	}
	
	@Test
	public void testEnd()
	{	
		assertEquals(true, nowTime.isBefore(endTime));	
	}
	
	@Test
	public void testDuration()
	{	
		diffInMilli = java.time.Duration.between(nowTime, beginTime).toMillis();
		System.out.println(diffInMilli);
	}
	
	@Test
	public void testGetUserSettings()
	{	
		run.getUserSettingsToPropertyObjFromFile(new File("").getAbsolutePath() + "\\config\\settings.xml");
	}
	
		
}
