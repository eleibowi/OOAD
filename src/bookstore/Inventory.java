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
	
	public void addBook(int isbn, String title, String author, int edition, int newQuantity, double newPrice, int usedQuantity, double usedPrice){
		books.add(new BookEntry(isbn,title,author,edition,newQuantity,newPrice,usedQuantity,usedPrice));
	}
	
	public void removeBook(BookEntry book){
		books.remove(book);
	}
	
	public void restockBook(BookEntry book, int quantity, boolean used){
		if(used)
			book.setUsedQuantity(book.getUsedQuantity()+quantity);
		else
			book.setNewQuantity(book.getNewQuantity()+quantity);
	}
	
	public void sellBook(BookPurchase book){
		int index=-1;
		for(int i=0;i<books.size();i++)
			if(books.get(i).getIsbn()==book.getIsbn())
				index=i;
		if(book.isUsed())
			book.setUsedQuantity(book.getUsedQuantity()-book.getQuantity());
		else
			book.setNewQuantity(book.getNewQuantity()-book.getQuantity());
		books.set(index,book);
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
	
	public ArrayList<BookEntry> getBooks(){
		return books;
	}
	
	public String displayInventory(){
		String display="";
		for(BookEntry b:books){
			display+=b.getIsbn()+" "+b.getTitle()+" "+b.getAuthor()+" "+b.getEdition()+"\n";
		}
		return display;
	}

	public double getMoneyInRegister() {
		return moneyInRegister;
	}

	public void setMoneyInRegister(double moneyInRegister) {
		this.moneyInRegister = moneyInRegister;
	}
}
