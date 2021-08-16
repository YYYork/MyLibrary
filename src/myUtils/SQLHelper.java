package myUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mySQLManager.DBmanager;

public class SQLHelper {
	private Connection con = null;
	private PreparedStatement ps = null;
	public SQLHelper(String sql,Object... objects) {
		con = DBmanager.getConnection();
		try {
			ps = con.prepareStatement(sql);
			int i=1;
			for(Object object:objects) {
				setObject(i,object);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SQLHelper(String sqls[],Object objects[][]) {
		// TODO Auto-generated constructor stub
	}

	public Connection con() {
		return con;
	}

	public PreparedStatement ps() {
		return ps;
	}
	
	public ResultSet useSQLtoQuery() {
		try {
			return ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean useSQLtoexecute() {
		try {
			return ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void close() {
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void setObject(int index,Object object) throws SQLException {
		if(object instanceof String) {
			ps.setString(index, (String)object);
			return;
		}
		if(object instanceof Boolean) {
			ps.setBoolean(index, (boolean)object);
			return;
		}
		if(object instanceof Integer) {
			ps.setInt(index, (int)object);
			return;
		}
		if(object instanceof Double) {
			ps.setDouble(index, (double)object);
			return;
		}
		if(object instanceof Float) {
			ps.setFloat(index, (float)object);
			return;
		}
	}
}
