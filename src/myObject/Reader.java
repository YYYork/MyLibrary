package myObject;

public class Reader {
	private String account;
	private String name;
	private boolean isAdmin;
	
	public Reader(String account, String name, boolean isAdmin) {
		super();
		this.account = account;
		this.name = name;
		this.isAdmin = isAdmin;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public Reader(String account, String name) {
		super();
		this.account = account;
		this.name = name;
	}
	
	public String getAccount() {
		return account;
	}
	
	public String getName() {
		return name;
	}
}
