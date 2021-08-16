package myUtils;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UseSQL {
	public static void useSQLToExecute(String sql, Object... objects) {
		SQLHelper helper = null;
		try {
			helper = new SQLHelper(sql, objects);
			helper.useSQLtoexecute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (helper != null) {
				try {
					helper.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static ResultSet useSQLToExecuteQuery(String sql, Object... objects) {
		SQLHelper helper = null;
		try {
			helper = new SQLHelper(sql, objects);
			return helper.useSQLtoQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (helper != null) {
				try {
					helper.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
