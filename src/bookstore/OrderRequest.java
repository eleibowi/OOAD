package bookstore;

import java.util.ArrayList;
import java.util.Date;

public class OrderRequest extends ShoppingCart {
	private Date time;
	private String customerName;
	private int customerPhoneNumber;
	private String customerEmail;
	
	public OrderRequest(ArrayList<BookPurchase> books, String customerName, int customerPhoneNumber, String customerEmail){
		super();
		setBooks(books);
		setTime(new Date());
		this.customerName=customerName;
		this.customerPhoneNumber=customerPhoneNumber;
		this.customerEmail=customerEmail;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(int customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
}
