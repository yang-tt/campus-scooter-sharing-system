package Tools;
import java.io.BufferedWriter; 
import java.io.File; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List; 

/**
 * Write the ArrayList into .csv files
 * @author Yue Zhang
 * @version 1.0
 */
public class WriteData { 

	/**
     * Write the ArrayList into .csv files
     * @param file        the path of the .csv files
     * @param dataList    the dataList contains the data
     * @return boolean    whether this arraylist has witten into the .csv files
     */
	public static boolean exportCsv(File file, List<String> dataList){
		boolean isSucess=false;
		FileOutputStream out=null;
		OutputStreamWriter osw=null;
		BufferedWriter bw=null;
		try {
				out = new FileOutputStream(file);
				osw = new OutputStreamWriter(out);
				bw =new BufferedWriter(osw);
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				if(dataList!=null && !dataList.isEmpty()){
					for(String data : dataList){
						bw.append(data).append("\r");
					}
				}
				isSucess=true;
		} catch (Exception e) {
			isSucess=false;
		}
		finally{
			if(bw!=null){
				try {
					bw.close();
					bw=null;
				} catch (IOException e) {
						e.printStackTrace();
						}
				}
			if(osw!=null){
				try {
					osw.close();
					osw=null;
				} catch (IOException e) {
					e.printStackTrace();
					}
				}
			if(out!=null){
				try {
					out.close();
					out=null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isSucess;
	}
}