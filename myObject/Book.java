package myObject;

public class Book {
	private String bookName;
	private String bookType;
	private String publisher;
	
	
	
	public Book(String bookName, String bookType, String publisher) {
		super();
		this.bookName = bookName;
		this.bookType = bookType;
		this.publisher = publisher;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	
}
