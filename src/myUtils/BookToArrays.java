package myUtils;

import java.util.List;

import myObject.Book;

public class BookToArrays {
	
	/*
	 * 把list转换成String二维数组（JTable的好帮手）
	 * */
	
	public static String[][] booktoArrays(List<Book> books) {
		if (books.isEmpty()) {
			String[][] books_str = { { "查无此书", "查无此书", "查无此书", "查无此书", "查无此书" } };
			return books_str;
		}
		String[][] books_str = new String[books.size()][5];
		for(int i =0;i<books.size();i++) {
			Book book = books.get(i);
			for(int j=0;j<5;j++) {
				if(j==0) {
					books_str[i][j] = book.getId();
				}
				if(j==1) {
					books_str[i][j] = book.getBookName();
				}
				if(j==2) {
					books_str[i][j] = book.getBookType();
				}
				if(j==3) {
					books_str[i][j] = book.getPublisher();
				}
				if(j==4) {
					books_str[i][j] = ""+book.isBorrowed();
				}
			}
		}
		return books_str;

	}
}
