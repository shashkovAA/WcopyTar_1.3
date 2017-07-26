package Objects;

import java.time.Duration;
import java.time.LocalTime;

/*
 * B - begin time is after current time
 * b - begin time is before current time 
 * E - end time is after current time 
 * e - end time is before current time 
 * 
 */
public class Sheduler {
	private static LocalTime beginTime;
	private static LocalTime endTime;	
	private static LocalTime nowTime;
	private static LocalTime timeMidnight;
	private static LocalTime oneSecondToMidnight;
	
	public Sheduler(Property userProperty){

		String midnight = "00:00:00";
		String onSecondBeforeMidnight = "23:59:59";
		
		beginTime = userProperty.getBeginTimeSheduler();
		endTime = userProperty.getEndTimeSheduler();
		
		timeMidnight = LocalTime.parse(midnight, MyCalendar.getTimeFormatWithSS());
		oneSecondToMidnight = LocalTime.parse(onSecondBeforeMidnight, MyCalendar.getTimeFormatWithSS());
	}
	
	
	public static long getBeginTimeoutToStart(){
		String point = getPoint();
		long timeBeforeStart = 0;
		
		switch (point) {
		case "BE" : timeBeforeStart = Duration.between(nowTime,beginTime).toMillis();
					break;
		case "be" : timeBeforeStart = Duration.between(nowTime,oneSecondToMidnight).toMillis() + 1000 + Duration.between(timeMidnight,beginTime).toMillis();
					break;
		case "eB" : timeBeforeStart = Duration.between(nowTime,beginTime).toMillis();
					break;
		}	
		
		return timeBeforeStart;
	}
	
	public static boolean isWorkTime() {
		
		if (getPoint().equals("bE"))
			return true;
		if (getPoint().equals("EB"))
			return true;
		if (getPoint().equals("eb"))
			return true;
			
		return false;
	}
	
	public static String getPoint() {
		
		 nowTime = LocalTime.parse(MyCalendar.getCurrentTimeNowWithSS(),MyCalendar.getTimeFormatWithSS());
		
		if (nowTime.isBefore(beginTime) && nowTime.isBefore(endTime) && beginTime.isBefore(endTime))
			return "BE";
		else if (nowTime.isAfter(beginTime) && nowTime.isBefore(endTime) && beginTime.isBefore(endTime))
			return "bE";
		else if (nowTime.isAfter(beginTime) && nowTime.isAfter(endTime) && beginTime.isBefore(endTime))
			return "be";
		else if (nowTime.isBefore(endTime) && nowTime.isBefore(beginTime) && beginTime.isAfter(endTime))
			return "EB";
		else if (nowTime.isAfter(endTime) && nowTime.isBefore(beginTime) && beginTime.isAfter(endTime))
			return "eB";
		else if (nowTime.isAfter(endTime) && nowTime.isAfter(beginTime) && beginTime.isAfter(endTime))
			return "eb";	
		else return "";
	}

}
