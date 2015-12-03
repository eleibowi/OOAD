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
	
	public void addQuantity(BookPurchase book, int quantity){
		for(int i=0;i<books.size();i++)
			if(books.get(i).getIsbn()==book.getIsbn()){
				BookPurchase updatedBook = books.get(i);
				updatedBook.setQuantity(updatedBook.getQuantity()+quantity);
				books.set(i,updatedBook);
			}
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
