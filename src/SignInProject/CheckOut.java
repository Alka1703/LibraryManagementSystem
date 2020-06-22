package SignInProject;

public class CheckOut {
	@Override
	public String toString() {
		return "CheckOut [transactionId=" + transactionId + ", userName=" + userName + ", bookId=" + bookId
				+ ", returnDate=" + returnDate + "]";
	}
	private int transactionId=0;
	private String userName="";
	private int bookId=0;
	private String returnDate=null;
	private int fine=0;
	
	public CheckOut() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CheckOut(int transactionId, String userName, int bookId, String returnDate, int fine) {
		super();
		this.transactionId = transactionId;
		this.userName = userName;
		this.bookId = bookId;
		this.returnDate = returnDate;
		this.fine=fine;
	}

	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	
	
	
	
}
