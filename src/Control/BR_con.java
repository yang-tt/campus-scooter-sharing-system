package Control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Tools.GetTime;
import Tools.ReadData;
import Tools.WriteData;

/**
 * The methods that will be used in the GUI during the borrow and return process
 * @author Yue Zhang
 * @version 1.0
 */

public class BR_con {
	private String ID;
	private String QMID;
	private String Station;
    /**
     * Constructor of base account
     * @param QMID      QMID
     * @param ID        Scooter ID
     * @param Station   Station ID
     */
	public BR_con(String ID, String QMID, String Station) {
		this.QMID = QMID;
		this.ID = ID;
		this.Station = Station;
	}
	
    /**
     * Update the data in the .csv files which contain the information of each station
     * @param BR    determine the action is borrow or return
     */
	public void update_station(String BR) {
		List<String> dataList_station = new ArrayList<String>();
		List<String> dataList_inter = new ArrayList<String>();
		if(Station.equals("A")) {
			dataList_station=ReadData.importCsv(new File("src/StationA.csv"));
			if(dataList_station!=null && !dataList_station.isEmpty()){
				for(int i=0; i<dataList_station.size();i++ ){
					String s=dataList_station.get(i);
					String[] as = s.split(",");
					if(as[0].equals(ID)) {
						if(BR.equals("B")) {
							as[1] = "0";
						}
						else {
							as[1] = "1";
						}
						s = as[0] + "," + as[1];
						dataList_inter.add(s);
					}
					else {
						dataList_inter.add(s);
					}
					WriteData.exportCsv(new File("src/StationA.csv"), dataList_inter);
				}
			}
		}
		else if(Station.equals("B")) {
			dataList_station=ReadData.importCsv(new File("src/StationB.csv"));	
			if(dataList_station!=null && !dataList_station.isEmpty()){
				for(int i=0; i<dataList_station.size();i++ ){
					String s=dataList_station.get(i);
					String[] as = s.split(",");
					if(as[0].equals(ID)) {
						if(BR.equals("B")) {
							as[1] = "0";
						}
						else {
							as[1] = "1";
						}
						s = as[0] + "," + as[1];
						dataList_inter.add(s);
					}
					else {
						dataList_inter.add(s);
					}
					WriteData.exportCsv(new File("src/StationB.csv"), dataList_inter);
				}
			}
		}
		else {
			dataList_station=ReadData.importCsv(new File("src/StationC.csv"));
			if(dataList_station!=null && !dataList_station.isEmpty()){
				for(int i=0; i<dataList_station.size();i++ ){
					String s=dataList_station.get(i);
					String[] as = s.split(",");
					if(as[0].equals(ID)) {
						if(BR.equals("B")) {
							as[1] = "0";
						}
						else {
							as[1] = "1";
						}
						s = as[0] + "," + as[1];
						dataList_inter.add(s);
					}
					else {
						dataList_inter.add(s);
					}
					WriteData.exportCsv(new File("src/StationC.csv"), dataList_inter);
				}
			}
		}
	}
	
    /**
     * Add the borrow time to the Borrow_time.csv
     */	
	public void add_borrow() {
		List<String> dataList_borrow = new ArrayList<String>();
		dataList_borrow=ReadData.importCsv(new File("src/Borrow_time.csv"));
		GetTime gettime = new GetTime();
		if(dataList_borrow!=null && !dataList_borrow.isEmpty()){
			dataList_borrow.add(QMID + "," + gettime.getTime() + "," + gettime.getYear() + "," + gettime.getMonth() + "," + gettime.getDay() + "," + gettime.getHour() + "," + gettime.getMinute() + "," + gettime.getSecond() + "," + Station);
			WriteData.exportCsv(new File("src/Borrow_time.csv"), dataList_borrow);
		}
	}
	
    /**
     * Add the return time to the Return_time.csv
     */
	public void add_return() {
		List<String> dataList_time = new ArrayList<String>();
		dataList_time=ReadData.importCsv(new File("src/Return_time.csv"));
		GetTime gettime = new GetTime();
		if(dataList_time!=null && !dataList_time.isEmpty()){
			dataList_time.add(QMID + "," + gettime.getTime() + "," + gettime.getYear() + "," + gettime.getMonth() + "," + gettime.getDay() + "," + gettime.getHour() + "," + gettime.getMinute() + "," + gettime.getSecond() + "," + Station);
			WriteData.exportCsv(new File("src/Return_time.csv"), dataList_time);
		}
	}
	
    /**
     * Delete the borrow information when this QMID has returned the scooter
     * @return now_time     the time this trip costs
     */
	public int remove_borrow() {
		Calculater calculater = new Calculater(QMID);
		int now_time = 0;
		List<String> dataList_detail = new ArrayList<String>();
		List<String> dataList_borrow = new ArrayList<String>();
		List<String> dataList_other = new ArrayList<String>();
		dataList_detail=ReadData.importCsv(new File("src/UsageDetail.csv"));
		dataList_borrow=ReadData.importCsv(new File("src/Borrow_time.csv"));
		GetTime gettime = new GetTime();
		if(dataList_borrow!=null && !dataList_borrow.isEmpty()){
			for(int i=0; i<dataList_borrow.size();i++ ){	
				String s=dataList_borrow.get(i);
				String as[] = s.split(",");
				if(as[0].equals(QMID)) {
					dataList_detail.add(s + "," + gettime.getTime() + "," + gettime.getYear() + "," + gettime.getMonth() + "," + gettime.getDay() + "," + gettime.getHour() + "," + gettime.getMinute() + "," + gettime.getSecond() + "," + Station);
					WriteData.exportCsv(new File("src/UsageDetail.csv"), dataList_detail);
					now_time = calculater.calculate_now();
					
				}
				else {
					dataList_other.add(s);
				}
			}
			WriteData.exportCsv(new File("src/Borrow_time.csv"), dataList_other);

		
		}
		return now_time;
	}
	
    /**
     * Delete last return information when this QMID has borrowed the scooter
     */
	public void remove_return() {
		List<String> dataList_other = new ArrayList<String>();
		List<String> dataList_return = new ArrayList<String>();
		dataList_return=ReadData.importCsv(new File("src/Return_time.csv"));
		
		if(dataList_return!=null && !dataList_return.isEmpty()){
			for(int i=0; i<dataList_return.size();i++ ){	
				String s=dataList_return.get(i);
				String as[] = s.split(",");
				if(as[0].equals(QMID)) {
					
				}
				else {
					dataList_other.add(s);
				}
			}
			WriteData.exportCsv(new File("src/Return_time.csv"), dataList_other);

		}
	}

    /**
     * Change the status of this QMID to "OnDebt" and bill to "100" and update the latest information to the .csv file
     */
	public void debt() {
		List<String> dataList_stu = new ArrayList<String>();
		List<String> dataList_inter = new ArrayList<String>();
		dataList_stu=ReadData.importCsv(new File("src/Student.csv"));
		if(dataList_stu!=null && !dataList_stu.isEmpty()){
			for(int i=0; i<dataList_stu.size();i++ ){
				//if(i!=0){
				String s=dataList_stu.get(i);
				//System.out.println(s);
				String[] as = s.split(",");
				if(as[0].equals(QMID)) {
					as[5] = "OnDebt";
					as[6] = "100";
					s = as[0] + "," + as[1] + "," + as[2] + "," + as[3] + "," + as[4] + "," + as[5] + "," + as[6] + "," + as[7];
					dataList_inter.add(s);
				}
				else {
					dataList_inter.add(s);
				}
			}
			WriteData.exportCsv(new File("src/Student.csv"), dataList_inter);

		}
	}
}
