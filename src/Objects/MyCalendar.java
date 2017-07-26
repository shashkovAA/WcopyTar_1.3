package Objects;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class MyCalendar {
	
	private volatile static LocalDateTime now;

    public static String getTimeFromMills(long millis) {
    	long second = (millis / 1000) % 60;
    	long minute = (millis / (1000 * 60)) % 60;
    	long hour = (millis / (1000 * 60 * 60)) % 24;
    	String time = "";
    	if (second != 0)
    		time = String.format("%02d sec", second);
    	else time = "0 sec";
    	if (minute != 0 )
    		time = String.format("%02d min %02d sec", minute, second);
    	if (hour != 0)
    		time = String.format("%02d h %02d min %02d sec", hour, minute, second);
    	
    	return time;
    }
	
	public static String getCurrentDayDateTimeNow() {
    	LocalDateTime now = LocalDateTime.now();
    	return now.format(getDateTimeFormat());
    }
    
    public static String getCurrentTimeNow() {
    	LocalDateTime now = LocalDateTime.now();
    	return now.format(getTimeFormat());
    }
    
    public static String getCurrentTimeNowCustomFormat(String format) {
    	LocalDateTime now = LocalDateTime.now();
    	return now.format(getTimeFormatCustom(format));
    }
    
    public static String getCurrentDateCustomFormat(String format) {
    	LocalDateTime now = LocalDateTime.now();
    	return now.format(getDateFormatCustom(format));
    }
    
    public static String getCurrentTimeNowWithSS() {
   	 now = LocalDateTime.now();
   	 return now.format(getTimeFormatWithSS());
    }

    public static String getCurrentDate() {
	LocalDate today = LocalDate.now();
	return today.format(getDateFormat());
    }

    public static String getCurrentDayDateTimeBegin() {
	return getCurrentDate() + " 00:00";
    }

    public static String getCurrentDayDateTimeEnd() {
	return getCurrentDate() + " 23:59";
    }

    public static String getMondayDateTimeOfCurrentWeek() {
	LocalDate today = LocalDate.now();
	LocalDate monday = today;
	while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
	    monday = monday.minusDays(1);
	}
	return monday.format(getDateFormat()) + " 00:00";
    }

    public static String getFirstDayDateTimeOfCurrentMonth() {
	LocalDate today = LocalDate.now();
	return today.with(TemporalAdjusters.firstDayOfMonth()).format(getDateFormat()) + " 00:00";
    }

    public static String getFirstDayDateTimeOfCurrentYear() {
	LocalDate today = LocalDate.now();
	return today.with(TemporalAdjusters.firstDayOfYear()).format(getDateFormat()) + " 00:00";
    }

    private static DateTimeFormatter getDateFormat() {
    	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    	return dateFormatter;
    }
    
    private static DateTimeFormatter getDateFormatCustom(String format) {
    	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
    	return dateFormatter;
    }
    
    public static DateTimeFormatter getTimeFormat() {
    	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    	return timeFormatter;
    }
    
    public static DateTimeFormatter getTimeFormatCustom(String format) {
    	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format);
    	return timeFormatter;
    }
    
    public static DateTimeFormatter getTimeFormatWithSS() {
    	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    	return timeFormatter;
    }
    public static DateTimeFormatter getDateTimeFormat() {
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
	return dateTimeFormatter;
    }
}