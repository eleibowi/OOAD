package bookstore;

import java.util.ArrayList;

public class ShoppingCart {
	private ArrayList<BookPurchase> books;
	
	public ShoppingCart(){
		books = new ArrayList<BookPurchase>();
	}
	
	public void addBook(BookEntry book, int quantity, boolean used){
		books.add(new BookPurchase(book,quantity,used));
	}
	
	public void removeBook(BookPurchase book){
		books.remove(book);
	}
	
	public void editQuantity(BookPurchase book, int quantity){
		book.setQuantity(quantity);
	}
	
	public double getTotalPrice(){
		double totalPrice=0;
		for(BookPurchase b:books){
			totalPrice+=b.getPrice()*b.getQuantity();
		}
		return totalPrice;
	}
	
	public void printReceipt(){
		String receipt="";
		for(BookPurchase b:books){
			receipt+=b.getTitle()+" $"+b.getPrice()+" ";
			if(b.isUsed())
				receipt+="used";
			else
				receipt+="new";
			receipt+=" "+b.getQuantity();
		}
		receipt+="Total Price: "+getTotalPrice();
		System.out.println(receipt);
	}
	
	public ArrayList<BookPurchase> getBooks(){
		return books;
	}
	
	public void setBooks(ArrayList<BookPurchase> books){
		this.books=books;
	}
}
