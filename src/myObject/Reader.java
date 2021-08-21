package myObject;

public class Reader {
	private String account;
	private String name;
	private boolean isAdmin;
	private String password;//��ѡ������
	
	public Reader(String account, String name, boolean isAdmin) {
		super();
		this.account = account;
		this.name = name;
		this.isAdmin = isAdmin;
		this.password = "123456";//Ĭ������
	}
	
	public Reader(String account, String name, boolean isAdmin,String password) {
		super();
		this.account = account;
		this.name = name;
		this.isAdmin = isAdmin;
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
	
	public String getAccount() {
		return account;
	}
	
	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}
