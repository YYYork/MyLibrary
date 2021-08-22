package myObject;

public class Book {
	private String bookName;
	private String bookType;
	private String publisher;
	private String id;
	private boolean isBorrowed;
	
	public Book(String bookName, String bookType, String publisher,String id,boolean isBorrowed) {
		super();
		this.bookName = bookName;
		this.bookType = bookType;
		this.publisher = publisher;
		this.id = id;
		this.isBorrowed = isBorrowed;
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
	public String getId() {
		return id;
	}
	
	public boolean isBorrowed() {
		return isBorrowed;
	}
}
