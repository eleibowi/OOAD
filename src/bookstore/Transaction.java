package bookstore;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {
	private ArrayList<BookPurchase> books;
	private Date time;
	
	public Transaction(ArrayList<BookPurchase>books){
		this.books = books;
		time = new Date();
	}
	
	public Transaction(ArrayList<BookPurchase>books, Date time){
		this.books=books;
		this.time=time;
	}
	
	public ArrayList<BookPurchase> getBooks() {
		return books;
	}
	public void setBooks(ArrayList<BookPurchase> books) {
		this.books = books;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
