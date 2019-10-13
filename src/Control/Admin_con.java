package Control;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Tools.ReadData;
import Tools.WriteData;

/**
 * Admin methods which will be used in GUI
 * @author Yue Zhang
 * @version 1.0
 */

public class Admin_con {
	public Admin_con() {
		
	}
	
	/**
     * Check whether this QMID has been registered in the system
     * @param QMID       QMID
     * @return the boolean flag
     */
	public boolean find_user(String QMID) {
			boolean flag = false;
			List<String> dataList_student = ReadData.importCsv(new File("src/Student.csv"));
			
			if(dataList_student!=null && !dataList_student.isEmpty()) {
				for(int i=0; i<dataList_student.size(); i++ ) {
					if(i!=0) {
						String s=dataList_student.get(i);
						String as[] = s.split(",");
						if(QMID.equals(as[0])) {
							flag = true;
						}
				}
			}
		}
		return flag;
	}
	
	/**
     * Change the status of this QMID(from "OnDebt" to "Free")
     * @param QMID       QMID
     */
	public void change(String QMID) {
		List<String> dataList_student = new ArrayList<String>();
		List<String> dataList_inter = new ArrayList<String>();
		dataList_student=ReadData.importCsv(new File("src/Student.csv"));
			if(dataList_student!=null && !dataList_student.isEmpty()){
				for(int i=0; i<dataList_student.size();i++ ){
					String s=dataList_student.get(i);
					String[] as = s.split(",");
					if(as[0].equals(QMID)) {
						as[5] = "Free";
						as[6] = "0";
						s = as[0] + "," + as[1] + "," + as[2] + "," + as[3] + "," + as[4] + "," + as[5] + "," + as[6] + "," + as[7];
						dataList_inter.add(s);
					}
					else {
						dataList_inter.add(s);
					}
					WriteData.exportCsv(new File("src/Student.csv"), dataList_inter);
				}
			}
	}
}