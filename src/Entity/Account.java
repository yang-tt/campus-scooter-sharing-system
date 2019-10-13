package Entity;

/**
 * Base account entity
 * @author Yue Zhang
 * @version 1.0
 */
public abstract class Account {
	private String QMID;
	private String name;
	private String email;
	private String address;
	private String tel;
	
    /**
     * Constructor of base account
     * @param QMID       QMID
     * @param name       user name
     * @param email      user email
     * @param address    user address
     * @param tel        user telephone number
     */
	public Account(String QMID, String name, String email, String address, String tel) {
		this.QMID = QMID;
		this.name = name;
		this.email = email;
		this.address = address;
		this.tel = tel;
	}
	
	public String getQMID() {
		return this.QMID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getTel() {
		return this.tel;
	}
	
	public void setQMID(String QMID) {
		this.QMID = QMID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}

}
