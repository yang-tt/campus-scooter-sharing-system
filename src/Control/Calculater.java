package Control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Tools.ReadData;

/**
 * Calculater methods which are used to calculate date
 * @author Yue Zhang
 * @version 1.0
 */
public class Calculater {
	private String QMID;
	
	 /**
     * Constructor of calculater
     * @param QMID    QMID
     */
	public Calculater(String QMID) {
		this.QMID = QMID;
		
	}
	
	/**
     * Calculate the total usage seconds of a QMID in the UsageDetail.csv
     * @return total      total seconds
     */
	public int calculate_total() {
	int total = 0;
	int result = 0;
	int year, month, day, hour, minute, second;
	String s, as[];
	List<String> dataList_detail = new ArrayList<String>();
	dataList_detail=ReadData.importCsv(new File("src/UsageDetail.csv"));
	if(dataList_detail!=null && !dataList_detail.isEmpty()){
		for(int i=1; i<dataList_detail.size();i++ ){
			
			s = dataList_detail.get(i);							
			as = s.split(",");
			if(as[0].equals(QMID)) {
				year = Integer.parseInt(as[10]) - Integer.parseInt(as[2]);
				month = Integer.parseInt(as[11]) - Integer.parseInt(as[3]);
				day = Integer.parseInt(as[12]) - Integer.parseInt(as[4]);
				hour = Integer.parseInt(as[13]) - Integer.parseInt(as[5]);
				minute = Integer.parseInt(as[14]) - Integer.parseInt(as[6]);
				second = Integer.parseInt(as[15]) - Integer.parseInt(as[7]);
			
				if(second < 0) {
					second = second + 60;
					minute = minute - 1;
				}
				if(minute < 0) {
					minute = minute + 60;
					hour = hour - 1;
				}
				if(hour < 0) {
					hour = hour + 24;
					day = day - 1;
				}
				if(day < 0) {
					day = day + 30;
					month = month - 1;
				}
				if(month < 0) {
					month = month + 12;
					year = year - 1;
				}
				result = second + minute * 60 + hour * 60 * 60 + day * 24 * 60 *60 + month * 30 * 24 * 60 * 60 + year * 12 * 30 * 24 * 60 * 60;
			}
			total = total +result;
		}
	}	
	return total;
	}
	
	/**
     * Calculate the latest trip usage seconds of the a QMID in the UsageDetail.csv
     * @return now     this time seconds  
     */
	public int calculate_now() {
			int now = 0;
			int year, month, day, hour, minute, second;
			String s, as[];
			/*List<String> dataList_borrow = new ArrayList<String>();
			List<String> dataList_return = new ArrayList<String>();
			dataList_borrow=ReadData.importCsv(new File("C://Users/asus/Desktop/CSV/Borrow_time.csv"));
			dataList_return=ReadData.importCsv(new File("C://Users/asus/Desktop/CSV/Return_time.csv"));
			if(dataList_borrow!=null && !dataList_borrow.isEmpty() && dataList_return!=null && !dataList_return.isEmpty()){
				for(int i=0; i<dataList_borrow.size();i++ ){
					
					for(int j=0; j<dataList_return.size();j++ ){
					s = dataList_borrow.get(i);	
					s1 = dataList_return.get(j);
					as = s.split(",");
					as1 = s1.split(",");
					if(as[0].equals(QMID) && as1[0].equals(QMID)) {
						year = Integer.parseInt(as1[2]) - Integer.parseInt(as[2]);
						month = Integer.parseInt(as1[3]) - Integer.parseInt(as[3]);
						day = Integer.parseInt(as1[4]) - Integer.parseInt(as[4]);
						hour = Integer.parseInt(as1[5]) - Integer.parseInt(as[5]);
						minute = Integer.parseInt(as1[6]) - Integer.parseInt(as[6]);
						second = Integer.parseInt(as1[7]) - Integer.parseInt(as[7]);
					*/
						List<String> dataList_detail = new ArrayList<String>();
						dataList_detail=ReadData.importCsv(new File("src/UsageDetail.csv"));
						if(dataList_detail!=null && !dataList_detail.isEmpty()){
							for(int i=1; i<dataList_detail.size();i++ ){
								
								s = dataList_detail.get(i);							
								as = s.split(",");
								if(as[0].equals(QMID) && as[4].equals(as[12])) {
									year = Integer.parseInt(as[10]) - Integer.parseInt(as[2]);
									month = Integer.parseInt(as[11]) - Integer.parseInt(as[3]);
									day = Integer.parseInt(as[12]) - Integer.parseInt(as[4]);
									hour = Integer.parseInt(as[13]) - Integer.parseInt(as[5]);
									minute = Integer.parseInt(as[14]) - Integer.parseInt(as[6]);
									second = Integer.parseInt(as[15]) - Integer.parseInt(as[7]);
								
									if(second < 0) {
										second = second + 60;
										minute = minute - 1;
									}
									if(minute < 0) {
										minute = minute + 60;
										hour = hour - 1;
									}
									if(hour < 0) {
										hour = hour + 24;
										day = day - 1;
									}
									if(day < 0) {
										day = day + 30;
										month = month - 1;
									}
									if(month < 0) {
										month = month + 12;
										year = year - 1;
									}
									now = second + minute * 60 + hour * 60 * 60 + day * 24 * 60 *60 + month * 30 * 24 * 60 * 60 + year * 12 * 30 * 24 * 60 * 60;
								}
							}
						}	
					return now;
			
	}
	
	/**
     * Calculate today's total usage seconds of a QMID in the UsageDetail.csv
     * @return today      today seconds
     */
	public int calculate_today() {
		int today = 0;
		int result = 0;
		int year, month, day, hour, minute, second;
		String s, as[];
		List<String> dataList_detail = new ArrayList<String>();
		dataList_detail=ReadData.importCsv(new File("src/UsageDetail.csv"));
		if(dataList_detail!=null && !dataList_detail.isEmpty()){
			for(int i=1; i<dataList_detail.size();i++ ){
				
				s = dataList_detail.get(i);							
				as = s.split(",");
				if(as[0].equals(QMID) && as[4].equals(as[12])) {
					year = Integer.parseInt(as[10]) - Integer.parseInt(as[2]);
					month = Integer.parseInt(as[11]) - Integer.parseInt(as[3]);
					day = Integer.parseInt(as[12]) - Integer.parseInt(as[4]);
					hour = Integer.parseInt(as[13]) - Integer.parseInt(as[5]);
					minute = Integer.parseInt(as[14]) - Integer.parseInt(as[6]);
					second = Integer.parseInt(as[15]) - Integer.parseInt(as[7]);
				
					if(second < 0) {
						second = second + 60;
						minute = minute - 1;
					}
					if(minute < 0) {
						minute = minute + 60;
						hour = hour - 1;
					}
					if(hour < 0) {
						hour = hour + 24;
						day = day - 1;
					}
					if(day < 0) {
						day = day + 30;
						month = month - 1;
					}
					if(month < 0) {
						month = month + 12;
						year = year - 1;
					}
					result = second + minute * 60 + hour * 60 * 60 + day * 24 * 60 *60 + month * 30 * 24 * 60 * 60 + year * 12 * 30 * 24 * 60 * 60;
				}
				today = today +result;
			}
		}	
		return today;
		}

	/**
     * Calculate the total days of a specific date
     * @param month_num   month number of a date
     * @param day_num     day number of a date
     * @return total_day      total number of days
     */	
	public int calculate_day(int month_num, int day_num) {
		int total_day = day_num;
		if(month_num == 1) {
			
		}
		else if(month_num == 2) {
			total_day = total_day + 31;
		}
		else if(month_num == 3) {
			total_day = total_day + 31 + 28;
		}
		else if(month_num == 4) {
			total_day = total_day + 31 + 28 + 31;
		}
		else if(month_num == 5) {
			total_day = total_day + 31 + 28 + 31 + 30;
		}
		else if(month_num == 6) {
			total_day = total_day + 31 + 28 + 31 + 30 + 31;
		}
		else if(month_num == 7) {
			total_day = total_day + 31 + 28 + 31 + 30 + 31 + 30;
		}
		else if(month_num == 8) {
			total_day = total_day + 31 + 28 + 31 + 30 + 31 + 30 + 31;
		}
		else if(month_num == 9) {
			total_day = total_day + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31;
		}
		else if(month_num == 10) {
			total_day = total_day + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
		}
		else if(month_num == 11) {
			total_day = total_day + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
		}
		else if(month_num == 12) {
			total_day = total_day + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30;
		}
		return total_day;
	}

	/**
     * Determine the month number with total days
     * @param day_num     total days
     * @return month_num      month number
     */
	public int calculate_month(int day_num) {
		int month_num = 0;
		int day_number = day_num;
		if(day_number > (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30)) {
			month_num++;
			day_number = day_number - 30;
		}
		if(day_number > (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31)) {
			month_num++;
			day_number = day_number - 31;
		}
		if(day_number > (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30)) {
			month_num++;
			day_number = day_number - 30;
		}
		if(day_number > (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31)) {
			month_num++;
			day_number = day_number - 31;
		}
		if(day_number > (31 + 28 + 31 + 30 + 31 + 30 + 31)) {
			month_num++;
			day_number = day_number - 31;
		}
		if(day_number > (31 + 28 + 31 + 30 + 31 + 30)) {
			month_num++;
			day_number = day_number  - 30;
		}
		if(day_number > (31 + 28 + 31 + 30 + 31)) {
			month_num++;
			day_number = day_number - 31;
		}
		if(day_number > (31 + 28 + 31 + 30) ) {
			month_num++;
			day_number = day_number - 30;
		}
		if(day_number > (31 + 28 + 31) ) {
			month_num++;
			day_number = day_number - 31;
		}
		if(day_number > (31 + 28) ) {
			month_num++;
			day_number = day_number - 28;
		}
		if(day_number > (31) ) {
			month_num++;
			day_number = day_number - 31;
		}
		if(day_number < 31) {
			month_num++;
		}
		
		return month_num;
	}

	/**
     * Calculate the total days of a specific date
     * @param month_num   month number of a date
     * @param day_num     day number of a date
     * @return day_number      total number of days
     */	
	public int calculate_date(int month_num, int day_num) {
		int day_number = day_num;
		int month_number = month_num;
		if(month_number == 2) {
			day_number = day_number - 31;
		}
		if(month_number == 3) {
			month_num++;
			day_number = day_number - 31 - 28;
		}
		if(month_number == 4) {
			month_num++;
			day_number = day_number - 31 - 28 - 31;
		}
		if(month_number == 5) {
			month_num++;
			day_number = day_number - 31 - 28 - 31 - 30;
		}
		if(month_number == 6) {
			month_num++;
			day_number = day_number - 31 - 28 - 31 - 30 - 31;
		}
		if(month_number == 7) {
			month_num++;
			day_number = day_number - 31 - 28 - 31 - 30 - 31 - 30;
		}
		if(month_number == 8) {
			month_num++;
			day_number = day_number - 31 - 28 - 31 - 30 - 31 - 30 - 31;
		}
		if(month_number == 9) {
			month_num++;
			day_number = day_number - 31 - 28 - 31 - 30 - 31 - 30 - 31 - 31;
		}
		if(month_number == 10) {
			month_num++;
			day_number = day_number - 31 - 28 - 31 - 30 - 31 - 30 - 31 - 31 - 30;
		}
		if(month_number == 11) {
			month_num++;
			day_number = day_number - 31 - 28 - 31 - 30 - 31 - 30 - 31 - 31 - 30 - 31;
		}
		if(month_number == 12) {
			month_num++;
			day_number = day_number - 31 - 28 - 31 - 30 - 31 - 30 - 31 - 31 - 30 - 31 - 30;
		}
		return day_number;
	}
}
