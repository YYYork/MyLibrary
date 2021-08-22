package mySQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myObject.Book;

public class BookFinder {
	public static List<Book> getBooks(BookFinderType type,String str) {
		Connection con = DBmanager.getConnection();
		PreparedStatement ps = null;
		ResultSet res = null;
		List<Book> books = new ArrayList<>();
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
					books.add(new Book(bookName,bookType,Publisher,bookid,isBorrowed));
				}
				return books;
			}
			if(type.equals(BookFinderType.SEARCH_FOR_TYPE)) {
				ps = con.prepareStatement("select * from Books where BookType = ?");
				ps.setString(1, str);
				res = ps.executeQuery();
				while(res.next()) {
					String bookName = res.getString("BookName");
					String bookType = res.getString("bookType");
					String Publisher = res.getString("Publisher");
					String bookid = res.getString("BookID");
					boolean isBorrowed = res.getBoolean("isBorrowed");
					books.add(new Book(bookName,bookType,Publisher,bookid,isBorrowed));
				}
				return books;
			}
			if(type.equals(BookFinderType.SEARCH_FOR_PUBLISHER)) {
				ps = con.prepareStatement("select * from Books where Publisher = ?");
				ps.setString(1, str);
				res = ps.executeQuery();
				while(res.next()) {
					String bookName = res.getString("BookName");
					String bookType = res.getString("bookType");
					String Publisher = res.getString("Publisher");
					String bookid = res.getString("BookID ");
					boolean isBorrowed = res.getBoolean("isBorrowed");
					books.add(new Book(bookName,bookType,Publisher,bookid,isBorrowed));
				}
				return books;
			}
			
			if(type.equals(BookFinderType.SEARCH_FOR_ID)) {
				ps = con.prepareStatement("select * from Books where BookID = ?");
				ps.setString(1, str);
				res = ps.executeQuery();
				while(res.next()) {
					String bookName = res.getString("BookName");
					String bookType = res.getString("bookType");
					String Publisher = res.getString("Publisher");
					String bookid = res.getString("BookID ");
					boolean isBorrowed = res.getBoolean("isBorrowed");
					books.add(new Book(bookName,bookType,Publisher,bookid,isBorrowed));
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
		}
		return books;//没有查询到书
	}
}
