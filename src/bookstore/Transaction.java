package bookstore;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {
	private ArrayList<BookPurchase> books;
	private Date time;
	private String paymentType;
	private int id;
	private static int idCount=1;
	
	public Transaction(ArrayList<BookPurchase>books, String payment){
		this.books = books;
		this.paymentType = payment;
		time = new Date();
		id=idCount;
		idCount++;
	}
	
	public Transaction(ArrayList<BookPurchase>books, Date time, String payment){
		this.books=books;
		this.time=time;
		this.paymentType=payment;
		id=idCount;
		idCount++;
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
	
	public int getId(){
		return id;
	}
	
	public void setId(int newId){
		id=newId;
	}
	
	public void remove(int i){
		books.remove(i);
	}
	
}
