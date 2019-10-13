package Entity;

/**
 * Base on debt student account entity
 * @author Yue Zhang
 * @version 1.0
 */
public abstract class DebtStuAccount extends Student {
	public DebtStuAccount(String QMID, String name, String email, String address, String tel, String status, String debt, String money) {
		super(QMID, name, email, address, tel, status, debt, money);
	}
	
	/**
     * pay for the bills
     */
	public void pay() {
		
	}
}
