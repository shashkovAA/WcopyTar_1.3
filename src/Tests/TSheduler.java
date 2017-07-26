package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.Debug;
import Objects.MyCalendar;
import Objects.Property;
import Objects.Sheduler;

public class TSheduler
{
	
	@Before
    public void setUp() throws Exception {
	
	 Debug.initDebugLog();
	 
    }

	@After
    public void tearDown() throws Exception {
    }


	@Test
	public void testIsWorkTimeBE() {	
	Property userProperty = new Property();
	userProperty.setBeginTimeSheduler("23:59:59");	
	userProperty.setEndTimeSheduler("03:50:00");		
	Sheduler sheduler = new Sheduler(userProperty);	
	Debug.log.info("BE: Время до запуска " + MyCalendar.getTimeFromMills(sheduler.getBeginTimeoutToStart()));
	assertEquals(true, Sheduler.isWorkTime());	
	
	}
	@Test
	public void testIsWorkTimebE() {	
	Property userProperty = new Property();
	userProperty.setBeginTimeSheduler("22:30:00");	
	userProperty.setEndTimeSheduler("23:50:00");		
	Sheduler sheduler = new Sheduler(userProperty);	
	Debug.log.info("bE: Время до запуска " + MyCalendar.getTimeFromMills(sheduler.getBeginTimeoutToStart()) );
	assertEquals(true, Sheduler.isWorkTime());	
	
	}
	@Test
	public void testIsWorkTimebe() {	
	Property userProperty = new Property();
	userProperty.setBeginTimeSheduler("00:05:00");	
	userProperty.setEndTimeSheduler("20:50:00");		
	Sheduler sheduler = new Sheduler(userProperty);	
	Debug.log.info("be: Время до запуска " + MyCalendar.getTimeFromMills(sheduler.getBeginTimeoutToStart()));
	assertEquals(true, Sheduler.isWorkTime());		
	}
	
	@Test
	public void testIsWorkTimeEB() {	
	Property userProperty = new Property();
	userProperty.setBeginTimeSheduler("23:50:00");	
	userProperty.setEndTimeSheduler("23:30:00");		
	Sheduler sheduler = new Sheduler(userProperty);	
	Debug.log.info("EB: Время до запуска " + MyCalendar.getTimeFromMills(sheduler.getBeginTimeoutToStart()));
	assertEquals(true, Sheduler.isWorkTime());	
	
	}
	@Test
	public void testIsWorkTimeeB() {	
	Property userProperty = new Property();
	userProperty.setBeginTimeSheduler("23:30:00");	
	userProperty.setEndTimeSheduler("03:50:00");		
	Sheduler sheduler = new Sheduler(userProperty);	
	Debug.log.info("eB: Время до запуска " + MyCalendar.getTimeFromMills(sheduler.getBeginTimeoutToStart()));
	assertEquals(true, Sheduler.isWorkTime());	
	
	}
	@Test
	public void testIsWorkTimeeb() {	
	Property userProperty = new Property();
	userProperty.setBeginTimeSheduler("18:30:00");	
	userProperty.setEndTimeSheduler("07:50:00");		
	Sheduler sheduler = new Sheduler(userProperty);	
	Debug.log.info("eb: Время до запуска " + MyCalendar.getTimeFromMills(sheduler.getBeginTimeoutToStart()));
	assertEquals(true, Sheduler.isWorkTime());	
	
	}

}
