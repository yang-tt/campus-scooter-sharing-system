package Control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Tools.ReadData;
import Tools.WriteData;

/**
 * Fine control methods which will be used in GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class Fine_con {
	private String QMID;
	 
	/**
     * Constructor of Fine_con
     * @param QMID    QMID
     */
	public Fine_con(String QMID) {
		this.QMID = QMID;
	}
	
	/**
     * Pay for the debt and update the data in the .csv file
     */
	public void pay() {
		List<String> dataList_other = new ArrayList<String>();
		List<String> dataList_student=ReadData.importCsv(new File("src/Student.csv"));
		if(dataList_student!=null && !dataList_student.isEmpty()){
				for(int i=0; i<dataList_student.size();i++ ){
					String s=dataList_student.get(i);
					String as[] = s.split(",");
					if(as[0].equals(QMID)) {
						if(Integer.parseInt(as[7]) >= Integer.parseInt(as[6])) {
							as[5] = "Free";
							as[7] = (Integer.parseInt(as[7])-Integer.parseInt(as[6]))+"";
							as[6] = "0";
							s = as[0] + "," + as[1] + "," +  as[2] + "," + as[3] + "," + as[4] + "," + as[5] + "," + as[6] + "," + as[7];
							dataList_other.add(s);
							JOptionPane.showMessageDialog(null,"You have paid the bill successfully!");
						}
						else {
							dataList_other.add(s);
							JOptionPane.showMessageDialog(null,"You don't have enough money in your account!");
						}
					}
					else {
						dataList_other.add(s);
					}
				
			}
			WriteData.exportCsv(new File("src/Student.csv"), dataList_other);
		}
	}
	
	/**
     * Recharge the money to the QMID and update the data in the .csv file
     * @param pay    the amount of the money the user want to recharge
     */
	public void recharge(String pay) {
		List<String> dataList_other = new ArrayList<String>();
		List<String> dataList_student=ReadData.importCsv(new File("src/Student.csv"));
		if(dataList_student!=null && !dataList_student.isEmpty()){
				for(int i=0; i<dataList_student.size();i++ ){
					
					String s=dataList_student.get(i);
					String as[] = s.split(",");
					if(as[0].equals(QMID)) {
						as[7] = Integer.parseInt(pay) + Integer.parseInt(as[7]) + "";
						s = as[0] + "," + as[1] + "," +  as[2] + "," + as[3] + "," + as[4] + "," + as[5] + "," + as[6] + "," + as[7];
						dataList_other.add(s);
						WriteData.exportCsv(new File("src/Student.csv"), dataList_other);
					}else {
					dataList_other.add(s);
					WriteData.exportCsv(new File("src/Student.csv"), dataList_other);
					}
			}
		}
	}
}
