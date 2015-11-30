package bookstore;

import java.util.ArrayList;

public class Controller {
	private Inventory inventory;
	private ArrayList<Transaction> transactions;
	private ArrayList<OrderRequest> orderRequests;
	private ShoppingCart shoppingCart;
	
	public Controller(){
		inventory = new Inventory();
		transactions = new ArrayList<Transaction>();
		orderRequests = new ArrayList<OrderRequest>();
		inventory.addBook(4, "Gone with the Wind", "Margaret Mitchell", 1, 5, 24, 0, 0);
		inventory.displayInventory();
		shoppingCart=new ShoppingCart();
	}
	
	public void checkOut(){
		//ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setBooks(chooseBooksFromInventory());
		transactions.add(new Transaction(shoppingCart.getBooks()));
		for(BookPurchase b : shoppingCart.getBooks()){
			inventory.sellBook(b);
		}
	}
	
	public void requestBooks(){
		ShoppingCart shoppingCart = new ShoppingCart();
		String customerName="";
		int customerPhone=0;
		String customerEmail="";
		//user input: choose books to request
		orderRequests.add(new OrderRequest(shoppingCart.getBooks(),customerName,customerPhone,customerEmail));
	}
	
	public void returnBooks(Transaction transaction){
		ShoppingCart shoppingCart = new ShoppingCart();
		//user input: choose books to remove
		transactions.add(new Transaction(shoppingCart.getBooks(),transaction.getTime()));
		transactions.remove(transaction);
	}
	
	public void buyBooksFromCustomer(){
		ShoppingCart shoppingCart = new ShoppingCart();
		
	}
	
	public ArrayList<BookPurchase> chooseBooksFromInventory(){
		ArrayList<BookPurchase> books = new ArrayList<BookPurchase>();
		//user input to select books and quantity, make BookPurchase object for each book selected and add it to ArrayList
		return books;
	}
	
	public void addNewBook(int isbn, String title, String author, int edition, int usedQuantity, double usedPrice, int newQuantity, double newPrice){
		
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	
	public ShoppingCart getShoppingCart(){
		return shoppingCart;
	}
	
	public static void main(String[]args){
		new Controller();
	}
}