package mySQLManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myObject.Book;
import myObject.Reader;
import myUtils.UseSQL;

public class DBmanager {
	
	/*
	 * getConnection()	得到和数据库连接
	 * CheckLogin()	检验账号密码
	 * RegAccount() 判断后注册账号密码
	 * getReader() 通过 账号/名字 查询并返回
	 * setReader() 更新用户信息
	 * addReader() 添加新用户
	 * getBooksReaderBorrowed() 返回用户借的书(List)
	 */
	
	private static Connection con = null;
	
	static {// 类被加载时得到和数据库的连接
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动加载错误");
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myLibrary", "root", "Dyk20050119.");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return con;
	}

	public static LoginState CheckLogin(String account, String password) {// 验证登陆
		Connection con = getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("select * from Accounts where Account = ?");
			ps.setString(1, account);
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				String str = res.getString("Password");
				if (str.equals(password)) {
					return LoginState.SUCCESS_LOGIN;
				} else {
					return LoginState.ERROR_PASSWORD;
				}
			} else {
				return LoginState.UNEXIST_ACCOUNT;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return LoginState.UNKNOWN_EXCEPTION;
	}

	public static RegisterState RegAccount(String account, String password) {// 注册账号
		Connection con = getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			ps = con.prepareStatement("select * from Accounts where Account = ?");
			ps.setString(1, account);
			ResultSet res = ps.executeQuery();
			if (!res.next()) {
				ps2 = con.prepareStatement(
						"INSERT INTO Accounts (Account,Password,isAdmin,Name) VALUES (?,?,false,'Reader')");
				ps2.setString(1, account);
				ps2.setString(2, password);
				ps2.execute();
				return RegisterState.ACCOUNT_SUCCESS_REGISTER;// 账号成功注册
			} else {
				return RegisterState.ACCOUNT_EXIST;// 账号存在
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps2 != null) {
				try {
					ps2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return RegisterState.UNKNOWN_EXCEPTION; // 未知错误
	}

	public static Reader getReader(String account, WayOfGetReader way) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (way.equals(WayOfGetReader.SEARCH_FOR_ACCOUNT)) {
			try {
				ps = con.prepareStatement("select * from Accounts where Account = ?");
				ps.setString(1, account);
				ResultSet res = ps.executeQuery();
				if (!res.next()) {
					return null;
				}
				String getAccount = res.getString("Account");
				String getName = res.getString("Name");
				boolean isAdmin = res.getBoolean("isAdmin");
				Reader reader = new Reader(getAccount, getName, isAdmin);
				return reader;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	public static void setReader(Reader reader) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (getReader(reader.getAccount(), WayOfGetReader.SEARCH_FOR_ACCOUNT) == null) {
			return;
		} else {
			try {
				ps = con.prepareStatement("UPDATE Accounts SET Account=?,Password=?,isAdmin=?,Name=? WHERE Account=?");
				ps.setString(1, reader.getAccount());
				ps.setString(2, reader.getPassword());
				ps.setBoolean(3, reader.isAdmin());
				ps.setString(4, reader.getName());
				ps.setString(5, reader.getAccount());
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void addReader(Reader reader) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (getReader(reader.getAccount(), WayOfGetReader.SEARCH_FOR_ACCOUNT) != null) {
			return;
		}
		try {
			ps = con.prepareStatement("INSERT INTO Accounts (Account,Password,isAdmin,Name) VALUES (?,?,?,?)");
			ps.setString(1, reader.getAccount());
			ps.setString(2, reader.getPassword());
			ps.setBoolean(3, reader.isAdmin());
			ps.setString(4, reader.getName());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void removeReader(Reader reader) {
		UseSQL.useSQLToExecute("DELETE FROM Accounts WHERE Account = ?",reader.getAccount()); 
	}

	public static List<Book> getBooksReaderBorrowed(Reader reader) {
		List<Book> books = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("select * from BookBorrowRecord where BorrowAccount = ?");
			ps.setString(1, reader.getAccount());
			ResultSet res = ps.executeQuery();
			while (res.next()) {// 把书放入List中
				String bookID = res.getString("BookID");
				books = BookFinder.getBooks(BookFinderType.SEARCH_FOR_ID, bookID);
			}
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;// 没找到书

	}
}
