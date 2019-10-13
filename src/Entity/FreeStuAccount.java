package Entity;

/**
 * Base free student account entity
 * @author Yue Zhang
 * @version 1.0
 */
public abstract class FreeStuAccount extends Student {
	public FreeStuAccount(String QMID, String name, String email, String address, String tel, String status, String debt, String money) {
		super(QMID, name, email, address, tel, status, debt, money);
	}
	
	/**
     * borrow the scooter
     */
	public void borrow() {
		
	}
	
	/**
     * return the scooter
     */
	public void back() {	
		
	}
	
}
