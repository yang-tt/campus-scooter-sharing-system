package Tools;


import java.util.Date; 
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Get the system time
 * @author Yue Zhang
 * @version 1.0
 */
public class GetTime{ 
	private String time;
	private int year, month, day, hour, minute, second;
	public GetTime(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		time = dateFormat.format( now ); 

		Calendar c = Calendar.getInstance();

		year = c.get(Calendar.YEAR); 
		month = c.get(Calendar.MONTH)+1; 
		day = c.get(Calendar.DATE); 
		hour = c.get(Calendar.HOUR_OF_DAY); 
		minute = c.get(Calendar.MINUTE); 
		second = c.get(Calendar.SECOND); 
	}
	
	public String getTime() {
		return time;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getHour() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public int getSecond() {
		return second;
	}
	
}
	
