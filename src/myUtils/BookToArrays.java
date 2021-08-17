package myUtils;

import java.util.List;

import myObject.Book;

public class BookToArrays {
	public static String[][] booktoArrays(Book[] books) {
		if (books.equals(null)) {
			String[][] books_str = { { "查无此书", "查无此书", "查无此书", "查无此书", "查无此书" } };
			return books_str;
		}
		String[][] books_str = null;
		for (int i = 0; i < books.length; i++) {// 控制书数量
			for (int j = 0; j < 5; j++) {// 输入书的信息
				if (j == 0)
					books_str[i][j] = books[i].getId();
				if (j == 1)
					books_str[i][j] = books[i].getBookName();
				if (j == 2)
					books_str[i][j] = books[i].getBookType();
				if (j == 3)
					books_str[i][j] = books[i].getPublisher();
				if (j == 4)
					books_str[i][j] = "" + books[i].getisBorrowed();
			}
		}

		return books_str;

	}
}
