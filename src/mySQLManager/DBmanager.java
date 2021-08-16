package mySQLManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBmanager {
	public static Connection getConnection() {//得到和数据库的连接
		Connection con;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动加载错误");
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myLibrary","root","root");
			return con;
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		return null;
	}
	
	public static LoginState CheckLogin(String account,String password) {//验证登陆
		Connection con = getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("select * from Accounts where Account = ?");
			ps.setString(1, account);
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				String str = res.getString("Password");
				if(str.equals(password)) {
					return LoginState.SUCCESS_LOGIN;
				}else {
					return LoginState.ERROR_PASSWORD;
				}
			}else {
				return LoginState.UNEXIT_ACCOUNT;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return LoginState.UNKNOWN_EXCEPTION;
	}
	
	public static RegisterState RegAccount(String account,String password) {//注册账号
		Connection con = getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			ps = con.prepareStatement("select * from Accounts where Account = ?");
			ps.setString(1, account);
			ResultSet res = ps.executeQuery();
			if(!res.next()) {
				ps2 = con.prepareStatement("INSERT INTO Accounts (Account,Password,isAdmin,Name) VALUES (?,?,false,'Reader');");
				ps2.setString(1, account);
				ps2.setString(2, password);
				ps2.execute();
				return RegisterState.ACCOUNT_SUCCESS_REGISTER;//账号成功注册
			}else {
				return RegisterState.ACCOUNT_EXITS;//	账号存在
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps2!=null){
				try {
					ps2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return RegisterState.UNKNOWN_EXCEPTION; //未知错误
	}
	
	public static boolean isAdmin(String account) {//判断是不是管理员
		Connection con = getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("select * from Accounts where Account = ?");
			ps.setString(1, account);
			ResultSet res = ps.executeQuery();
			res.next();
			return res.getBoolean("isAdmin");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;//未知错误，就不是管理员
	}
	
	public static String getName(String account) {//得到用户的名字
		Connection con = getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("select * from Accounts where Account = ?");
			ps.setString(1, account);
			ResultSet res = ps.executeQuery();
			res.next();
			return res.getString("Name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;//未知错误，返回null
	}
}
