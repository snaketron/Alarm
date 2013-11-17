package gui.adder;

import gui.constants.GuiConstants;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

/**
 * This routine is used to get the data needed to automatically 
 * initialize the combo-boxes contained in the AlarmAdder.
 * 
 * @author kitanovski
 *
 */
public class AlarmAdderRoutine {

	public static String [] getDaysOfWeek() {
		String [] daysOfWeek = new String [7];
		Map<String, Integer> map = Calendar.getInstance().getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
		for(int d = 0; d < 7; d++) {
			for(String key : map.keySet()) {
				if(map.get(key) == d + 1) {
					daysOfWeek[d] = key;
					break;
				}
			}
		}
		return daysOfWeek;
	}
	
	public static int getCurrentDayOfWeek() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
	} 
	
	public static Integer [] getYears() {
		Integer [] years = new Integer [6];
		int year = Calendar.getInstance().get(Calendar.YEAR);
		years[0] = year;
		for(int y = 1; y <= GuiConstants.YEARS_SHOWN; y++) {
			years[y] = year + y;
		}
		return years;
	}
	
	public static String [] getMonths() {
		String [] months = new String [12];
		Map<String, Integer> map = Calendar.getInstance().getDisplayNames(Calendar.MONTH, Calendar.SHORT, Locale.US);
		for(int m = 0; m < 12; m++) {
			for(String key : map.keySet()) {
				if(map.get(key) == m) {
					months[m] = key;
					break;
				}
			}
		}
		return months;
	}
	
	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	} 
	
	public static Integer [] getDays(int currentYear, int currentMonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, currentYear);
		cal.set(Calendar.MONTH, currentMonth);
		
		int maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Integer [] days = new Integer [maxDays];
		for(int d = 0; d < maxDays; d++) {
			days[d] = d + 1;
		}
		return days;
	}
	
	public static int getCurrentDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1;
	} 
	
	public static Integer [] getHours() {
		Integer [] hours = new Integer [24];
		for(int h = 0; h < 24; h++) {
			hours[h] = h;
		}
		return hours;
	}
	
	public static int getCurrentHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}
	
	public static Integer [] getMinutes() {
		Integer [] minutes = new Integer [60];
		for(int m = 0; m < 60; m++) {
			minutes[m] = m;
		}
		return minutes;
	}
	
	public static int getCurrentMinute() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	} 
	
	public static Integer [] getSeconds() {
		Integer [] seconds = new Integer [60];
		for(int s = 0; s < 60; s++) {
			seconds[s] = s;
		}
		return seconds;
	}
	
	public static int getCurrentSecond() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}
	
	//TODO db access
	public static String [] getAudios() {
		return new String []{"cat.mp3", "acdc.mp3"};
	}
	
	//TODO db access
	public static String [] getVideos() {
		return new String []{"storm.mp4", "flash.mp4"};
	}
}
