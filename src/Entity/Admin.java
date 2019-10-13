package Entity;

public abstract class Admin extends Account {
	public Admin(String QMID, String name, String email, String address, String tel) {
		super(QMID, name, email, address, tel);
	}
	
	public void add_user(String QMID, String name, String email, String address, String tel) {
		
	}
	
	public void remove_user(String QMID, String name, String email) {
		
	}
	
	public void add_scooter(String name, int scooter_number) {
		
	}
	
	public void remove_scooter(String name, int scooter_number) {
		
	}
	
	public void add_station(String name, int scooter_number) {
		
	}
	
	public void remove_station(String name, int scooter_number) {
		
	}
	
	public void view_station(String name) {
		
	}
	
	public void view_usage(String QMID) {
		
	}
}
