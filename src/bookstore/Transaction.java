package bookstore;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {
	private ArrayList<BookPurchase> books;
	private Date time;
	private String paymentType;
	
	public Transaction(ArrayList<BookPurchase>books, String payment){
		this.books = books;
		this.paymentType = payment;
		time = new Date();
	}
	
	public Transaction(ArrayList<BookPurchase>books, Date time, String payment){
		this.books=books;
		this.time=time;
		this.paymentType=payment;
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
	
	public String getPaymentType(){
		return paymentType;
	}
	
	public void setPaymentType(String payment){
		paymentType=payment;
	}
	
}
