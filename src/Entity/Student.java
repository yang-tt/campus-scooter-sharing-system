package Entity;
/**
 * Base student account entity
 * @author Yue Zhang
 * @version 1.0
 */
public abstract class Student extends Account {
	private String status;
	private String debt;
	private String money;
	
    /**
     * Constructor of base account
     * @param QMID       QMID
     * @param name       user name
     * @param email      user email
     * @param address    user address
     * @param tel        user telephone number
     * @param status     student status
     * @param debt       debt amount
     * @param money      money
     */
	public Student(String QMID, String name, String email, String address, String tel, String status, String debt, String money) {
		super(QMID, name, email, address, tel);
		this.status = status;
		this.debt = debt;
		this.money = money;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String getDebt() {
		return this.debt;
	}
	
	public String getEmail() {
		return this.money;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setDebt(String debt) {
		this.debt = debt;
	}
	
	public void setMoney(String money) {
		this.money = money;
	}
	
	/**
     * recharge function
     * @param money    amount of money what to recharge
     */
	public void recharge(int money) {
		
	}
	
	/**
     * view usage condition
     */
	public void view_usage() {
		
	}
	
	/**
     * view fine condition
     */
	public void view_fine() {
		
	}
}
