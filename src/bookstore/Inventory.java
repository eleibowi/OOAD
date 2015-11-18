package bookstore;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<BookEntry> books;
	private double moneyInRegister;
	
	public Inventory(){
		this.books = new ArrayList<BookEntry>();
		this.setMoneyInRegister(0);
	}
	
	public Inventory(ArrayList<BookEntry> books, double money){
		this.books = books;
		this.setMoneyInRegister(money);
	}
	
	public void addBook(int isbn, String title, String author, int edition, int usedQuantity, double usedPrice, int newQuantity, double newPrice){
		books.add(new BookEntry(isbn,title,author,edition,usedQuantity,usedPrice,newQuantity,newPrice));
	}
	
	public void removeBook(BookEntry book){
		books.remove(book);
	}
	
	public void restockUsedBook(BookEntry book, int quantity){
		book.setUsedQuantity(book.getUsedQuantity()+quantity);
	}
	
	public void restockNewBook(BookEntry book, int quantity){
		book.setNewQuantity(book.getNewQuantity()+quantity);
	}
	
	public void sellBook(BookPurchase book){
		if(book.isUsed())
			book.setUsedQuantity(book.getUsedQuantity()-book.getQuantity());
		else
			book.setNewQuantity(book.getNewQuantity()-book.getQuantity());
		moneyInRegister+=book.getQuantity()*book.getPrice();
	}
	
	public void setBookUsedDiscount(BookEntry book, double discount){
		book.setUsedDiscount(discount);
	}
	
	public void setBookNewDiscount(BookEntry book, double discount){
		book.setNewDiscount(discount);
	}
	
	public void editBookInfo(BookEntry book, int isbn, String title, String author, int edition, int usedQuantity, double usedPrice, int newQuantity, double newPrice){
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setEdition(edition);
		book.setUsedQuantity(usedQuantity);
		book.setUsedPrice(usedPrice);
		book.setNewQuantity(newQuantity);
		book.setNewPrice(newPrice);
	}
	
	public BookEntry findBook(int isbn){
		for(BookEntry b:books)
			if(b.getIsbn() == isbn)
				return b;
		return null;
	}
	
	public ArrayList<BookEntry> findBook(String titleOrAuthor){
		ArrayList<BookEntry> foundBooks = new ArrayList<BookEntry>();
		for(BookEntry b:books)
			if(b.getTitle().equals(titleOrAuthor)||b.getAuthor().equals(titleOrAuthor))
				foundBooks.add(b);
		return foundBooks;
	}
	
	public void displayInventory(){
		for(BookEntry b:books){
			System.out.println(b.getIsbn()+" "+b.getTitle()+" "+b.getAuthor()+" "+b.getEdition());
		}
	}

	public double getMoneyInRegister() {
		return moneyInRegister;
	}

	public void setMoneyInRegister(double moneyInRegister) {
		this.moneyInRegister = moneyInRegister;
	}
}
