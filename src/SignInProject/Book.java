package SignInProject;

public class Book {
	
	private int bookId=0;
	private String bookName="";
	private String authorName="";
	private String ISBN="";
	private String publisher="";
	private int totalCopies=0;
	private int availableCopies=0;
	private int rackNumber=0;
	
	public int getRackNumber() {
		return rackNumber;
	}
	public void setRackNumber(int rackNumber) {
		this.rackNumber = rackNumber;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int bookId, String bookName, String authorName, String publisher, String iSBN, int rackNumber, int totalCopies,
			int availableCopies) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		ISBN = iSBN;
		this.publisher = publisher;
		this.totalCopies = totalCopies;
		this.availableCopies = availableCopies;
		this.rackNumber=rackNumber;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getTotalCopies() {
		return totalCopies;
	}
	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}
	public int getAvailableCopies() {
		return availableCopies;
	}
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + ", ISBN=" + ISBN
				+ ", publisher=" + publisher + ", totalCopies=" + totalCopies + ", availableCopies=" + availableCopies
				+ ", rackNumber=" + rackNumber + "]";
	}
	
}
