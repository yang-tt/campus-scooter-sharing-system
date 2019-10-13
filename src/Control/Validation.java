package Control;

import java.io.File;
import java.util.List;

import Tools.ReadData;

/**
 * Validation methods which will be used in GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class Validation {
	private String QMID;
	
	/**
     * Constructor of Fine_con
     * @param QMID    QMID
     */
	public Validation(String QMID) {
		this.QMID = QMID;
	}
	
	/**
     * Verify whether this QMID is in the Student.csv file
     * @return flag_stu     whether this QMID is in the Student.csv file
     */
	public boolean login_stu() {
		boolean flag_stu = false;
		List<String> dataList_stu=ReadData.importCsv(new File("src/Student.csv"));
		if(dataList_stu!=null && !dataList_stu.isEmpty()){
			for(int i=0; i<dataList_stu.size();i++ ){
				if(i!=0){
					String s=dataList_stu.get(i);
					String[] as = s.split(",");
					if(QMID.equals(as[0])) {
						flag_stu = true;
					}
				}
			}
		}
		return flag_stu;
	}
	
	/**
     * Verify whether this QMID is in the Admin.csv file
     * @return flag_admin     whether this QMID is in the Admin.csv file
     */
	public boolean login_admin() {
		boolean flag_admin = false;
		List<String> dataList_admin=ReadData.importCsv(new File("src/Admin.csv"));
		if(dataList_admin!=null && !dataList_admin.isEmpty()){
			for(int i=0; i<dataList_admin.size();i++ ){
				if(i!=0){
					String s=dataList_admin.get(i);
					String[] as = s.split(",");
					if(QMID.equals(as[0])) {
						flag_admin = true;
					}
				}
			}
		}
		return flag_admin;
	}
	
	/**
     * Verify whether this QMID has borrowed a scooter and not returned
     * @return flag_borrow     whether this QMID has borrowed a scooter and not returned
     */
	public boolean borrow() {
		boolean flag_borrow = true;
		List<String> dataList_scooter=ReadData.importCsv(new File("src/Borrow_time.csv"));
		if(dataList_scooter!=null && !dataList_scooter.isEmpty()){
			for(int i=0; i<dataList_scooter.size();i++ ){
				if(i!=0){
					String s=dataList_scooter.get(i);
					String[] as = s.split(",");
					if(QMID.equals(as[0])) {
						flag_borrow = false;
					}
				}
			}
		}
		return flag_borrow;
	}
	
	/**
     * Verify whether this QMID has returned a scooter and not borrowed
     * @return flag_return     whether this QMID has returned a scooter and not borrowed
     */
	public boolean back() {
		boolean flag_return = true;
		List<String> dataList_scooter=ReadData.importCsv(new File("src/Return_time.csv"));
		if(dataList_scooter!=null && !dataList_scooter.isEmpty()){
			for(int i=0; i<dataList_scooter.size();i++ ){
				if(i!=0){
					String s=dataList_scooter.get(i);
					String[] as = s.split(",");
					if(QMID.equals(as[0])) {
						flag_return = false;
					}
				}
			}
		}
		return flag_return;
	}
	
	/**
     * Verify whether this QMID is on debt
     * @return flag_state     whether this QMID is on debt
     */
	public boolean status() {
		boolean flag_state = true;
		List<String> dataList_state=ReadData.importCsv(new File("src/Student.csv"));
		if(dataList_state!=null && !dataList_state.isEmpty()){
			for(int i=0; i<dataList_state.size();i++ ){
				if(i!=0){
					String s=dataList_state.get(i);
					String[] as = s.split(",");
					if(as[5].equals("OnDebt")) {
						flag_state = false;
					}
				}
			}
		}
		return flag_state;
	}

}
