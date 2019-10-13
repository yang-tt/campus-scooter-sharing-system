package Control;

import java.io.File;
import java.util.List;

import Tools.ReadData;

/**
 * Convert methods which will be used in GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class Convert {
	private String usage[][];
	private String QMID, s, as[];
	private int times, m;
	 /**
     * Constructor of convert
     * @param QMID    QMID
     */
	public Convert(String QMID) {
		this.QMID = QMID;
	}
	
	/**
     * Convert the data type from ArrayList into String[][] for the data in the .csv file
     * @return usage      usage condition String[][]
     */
	public String[][] convert_total(){
		times = 0;
		m =0;
		List<String> dataList_detail=ReadData.importCsv(new File("src/UsageDetail.csv"));
		if(dataList_detail!=null && !dataList_detail.isEmpty()){
				for(int i=0; i<dataList_detail.size();i++ ){
					if(i!=0){
					s=dataList_detail.get(i);
					as = s.split(",");
					if(as[0].equals(QMID)) {
						times++;
					}
				}
			}
				//System.out.println(times);
		}
		
		String usage[][] = new String[times][5];
		
		if(dataList_detail!=null && !dataList_detail.isEmpty()){
			for(int i=0; i<dataList_detail.size();i++ ){
				if(i!=0){
				s=dataList_detail.get(i);
				as = s.split(",");
				if(as[0].equals(QMID)) {
					for(int n=m; n<times; n++) {
						usage[m][0] = QMID;
						//System.out.println(usage[m][0]);
						usage[m][1] = as[2] + "/" + as[3] + "/" + as[4] + " " + as[5] + ":" + as[6] + ":" + as[7];
						//System.out.println(usage[m][1]);
						usage[m][2] = as[8];
						//System.out.println(usage[m][2]);
						usage[m][3] = as[10] + "/" + as[11] + "/" + as[12] + " " + as[13] + ":" + as[14] + ":" + as[15];
						//System.out.println(usage[m][3]);
						usage[m][4] = as[16];
						//System.out.println(usage[m][4]);
						//usage[m][5] = 
						//a++;
						m++;
						break;
					}
				}
			}
		}
	}
		
		return usage;
	}
	
	/**
     * Convert the data type from ArrayList into String[][] for the data in the .csv file
     * @param month_start    the month of the start date
     * @param day_start      the day of the start date
     * @param month_end      the month of the end date
     * @param day_end        the day of the end date
     * @return usage      usage condition String[][]
     */	
	public String[][] convert_week(int month_begin, int day_begin, int month_end, int day_end){
		int month_start = month_begin;
		int day_start = day_begin;
		int month_final = month_end;
		int day_final = day_end;
		times = 0;
		m =0;
		List<String> dataList_detail=ReadData.importCsv(new File("src/UsageDetail.csv"));
		if(dataList_detail!=null && !dataList_detail.isEmpty()){
				for(int i=0; i<dataList_detail.size();i++ ){
					if(i!=0){
					s=dataList_detail.get(i);
					as = s.split(",");
					if(as[0].equals(QMID) && (month_start <= Integer.parseInt(as[3]) && Integer.parseInt(as[3]) <= month_final) && ((day_start <= Integer.parseInt(as[4]) && Integer.parseInt(as[4]) <= day_final) || (day_start <= Integer.parseInt(as[4]) && Integer.parseInt(as[4]) <= (day_start + 6)) || ((day_final - 6) <= Integer.parseInt(as[4]) && Integer.parseInt(as[4]) <= day_final)) && (month_start <= Integer.parseInt(as[11]) && Integer.parseInt(as[11]) <= month_final) && ((day_start <= Integer.parseInt(as[12]) && Integer.parseInt(as[12]) <= day_final) || (day_start <= Integer.parseInt(as[12]) && Integer.parseInt(as[12]) <= (day_start + 6)) || ((day_final - 6) <= Integer.parseInt(as[12]) && Integer.parseInt(as[12]) <= day_final))) {
						times++;
					}
				}
			}
		}
		
		String usage[][] = new String[times][5];
		
		if(dataList_detail!=null && !dataList_detail.isEmpty()){
			for(int i=0; i<dataList_detail.size();i++ ){
				if(i!=0){
				s=dataList_detail.get(i);
				as = s.split(",");
				if(as[0].equals(QMID) && (month_start <= Integer.parseInt(as[3]) && Integer.parseInt(as[3]) <= month_final) && ((day_start <= Integer.parseInt(as[4]) && Integer.parseInt(as[4]) <= day_final) || (day_start <= Integer.parseInt(as[4]) && Integer.parseInt(as[4]) <= (day_start + 6)) || ((day_final - 6) <= Integer.parseInt(as[4]) && Integer.parseInt(as[4]) <= day_final)) && (month_start <= Integer.parseInt(as[11]) && Integer.parseInt(as[11]) <= month_final) && ((day_start <= Integer.parseInt(as[12]) && Integer.parseInt(as[12]) <= day_final) || (day_start <= Integer.parseInt(as[12]) && Integer.parseInt(as[12]) <= (day_start + 6)) || ((day_final - 6) <= Integer.parseInt(as[12]) && Integer.parseInt(as[12]) <= day_final))) {
					for(int n=m; n<times; n++) {
//						System.out.println("11111111111");
						usage[m][0] = QMID;
//						System.out.println(usage[m][0]);
						usage[m][1] = as[2] + "/" + as[3] + "/" + as[4] + " " + as[5] + ":" + as[6] + ":" + as[7];
//						System.out.println(usage[m][1]);
						usage[m][2] = as[8];
//						System.out.println(usage[m][2]);
						usage[m][3] = as[10] + "/" + as[11] + "/" + as[12] + " " + as[13] + ":" + as[14] + ":" + as[15];
//						System.out.println(usage[m][3]);
						usage[m][4] = as[16];
//						System.out.println(usage[m][4]);
						//usage[m][5] = 
						//a++;
						m++;
						break;
					}
				}
			}
		}
	}
		
		return usage;
	}
	
}
