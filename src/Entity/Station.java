package Entity;

/**
 * Base station entity
 * @author Yue Zhang
 * @version 1.0
 */
public class Station {
	private String name;
	private int scooter_num;
	
    /**
     * Constructor of base account
     * @param name           station name
     * @param scooter_num    amount of scooters of a station
     */
	public Station(String name, int scooter_num) {
		this.name = name;
		this.scooter_num = scooter_num;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getNum() {
		return this.scooter_num;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNum(int scooter_num) {
		this.scooter_num = scooter_num;
	}
}
