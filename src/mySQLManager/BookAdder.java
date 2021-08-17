package mySQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import myObject.Book;
import myObject.Reader;
import myUtils.UseSQL;

public class BookAdder {
	public static boolean addBook(Book book) {
		Connection con = DBmanager.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO Books (BookID,BookName,BookType,Publisher,isBorrowed) VALUES (?,?,?,?,false);");
			ps.setString(1, book.getId());
			ps.setString(2, book.getBookName());
			ps.setString(3, book.getBookType());
			ps.setString(4, book.getPublisher());
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
	
	public static void setBookBorrowState(Book book,boolean isBorrowed) {
		if(book.equals(null)) {
			return;
		}
		Connection con = DBmanager.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE Books SET isBorrowed = ? WHERE BookID = ?");
			ps.setBoolean(1, isBorrowed);
			ps.setString(2, book.getId());
			ps.execute();
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
	}
	
	public static void addBookBorrowRecord(Reader reader,Book book) {
		UseSQL.useSQLToExecute("INSERT INTO BookBorrowRecord (BookID,BorrowAccount) VALUES (?,?)",book.getId(),reader.getAccount());
	}
	
	public static void delBookBorrowRecord(Reader reader,Book book) {
		UseSQL.useSQLToExecute("DELETE FROM BookBorrowRecord WHERE BookID = ?",book.getId());
	}
}
