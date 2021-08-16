package mySQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myObject.Book;

public class BookFinder {
	public static Book[] getBooks(BookFinderType type,String str) {
		Connection con = DBmanager.getConnection();
		PreparedStatement ps = null;
		ResultSet res = null;
		Book books[] = null;
		int account = 0;
		try {
			if(type.equals(BookFinderType.SEARCH_FOR_NAME)) {
			ps = con.prepareStatement("select * from Books where BookName = ?");
			ps.setString(1, str);
			res = ps.executeQuery();
				while(res.next()) {//一条条将数据库的书转化为Book对象
					String bookName = res.getString("BookName");
					String bookType = res.getString("bookType");
					String Publisher = res.getString("Publisher");
					String bookid = res.getString("BookID");
					boolean isBorrowed = res.getBoolean("isBorrowed");
					books[account] = new Book(bookName,bookType,Publisher,bookid,isBorrowed);
					account++;
				}
				return books;
			}
			if(type.equals(BookFinderType.SEARCH_FOR_TYPE)) {
				ps = con.prepareStatement("select * from Books where BookType = ?");
				res = ps.executeQuery();
				ps.setString(1, str);
				while(res.next()) {
					String bookName = res.getString("BookName");
					String bookType = res.getString("bookType");
					String Publisher = res.getString("Publisher");
					String bookid = res.getString("BookID ");
					boolean isBorrowed = res.getBoolean("isBorrowed");
					books[account] = new Book(bookName,bookType,Publisher,bookid,isBorrowed);
					account++;
				}
				return books;
			}
			if(type.equals(BookFinderType.SEARCH_FOR_PUBLISHER)) {
				ps = con.prepareStatement("select * from Books where Publisher = ?");
				res = ps.executeQuery();
				ps.setString(1, str);
				while(res.next()) {
					String bookName = res.getString("BookName");
					String bookType = res.getString("bookType");
					String Publisher = res.getString("Publisher");
					String bookid = res.getString("BookID ");
					boolean isBorrowed = res.getBoolean("isBorrowed");
					books[account] = new Book(bookName,bookType,Publisher,bookid,isBorrowed);
					account++;
				}
				return books;
			}
			
			if(type.equals(BookFinderType.SEARCH_FOR_ID)) {
				ps = con.prepareStatement("select * from Books where BookID = ?");
				res = ps.executeQuery();
				ps.setString(1, str);
				while(res.next()) {
					String bookName = res.getString("BookName");
					String bookType = res.getString("bookType");
					String Publisher = res.getString("Publisher");
					String bookid = res.getString("BookID ");
					boolean isBorrowed = res.getBoolean("isBorrowed");
					books[account] = new Book(bookName,bookType,Publisher,bookid,isBorrowed);
					account++;
				}
				return books;
			}
			
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
		
		return null;//没有查询到书
		
	}
}
