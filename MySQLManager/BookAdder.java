package MySQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import myObject.Book;

public class BookAdder {
	public static boolean addBook(Book book) {
		Connection con = DBmanager.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO Books (BookName,BookType,Publisher) VALUES (?,?,?);");
			ps.execute();
			return true;//插入成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;//插入失败
	}
}
