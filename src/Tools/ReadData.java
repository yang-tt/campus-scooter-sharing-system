package Tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Read .csv files and load data into ArrayLists
 * @author Yue Zhang
 * @version 1.0
 */
public class ReadData {

	/**
     * Read .csv files and load data into ArrayLists
     * @param file         the path of the .csv files
     * @return dataList    the ArrayList contains the data of the .csv files
     */
	public static List<String> importCsv(File file){
		List<String> dataList=new ArrayList<String>();
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				dataList.add(line);
			}
		}catch (Exception e) {
			}finally{
				if(br!=null){
					try {
						br.close();
						br=null;
					} catch (IOException e) {
						e.printStackTrace();
						}
				}
			}
		return dataList;
	}
}
