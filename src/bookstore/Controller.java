package bookstore;

import java.util.ArrayList;

public class Controller {
	private Inventory inventory;
	private ArrayList<Transaction> transactions;
	private ArrayList<OrderRequest> orderRequests;
	
	public Controller(){
		inventory = new Inventory();
		transactions = new ArrayList<Transaction>();
		orderRequests = new ArrayList<OrderRequest>();
		inventory.addBook(4, "Gone with the Wind", "Margaret Mitchell", 1, 5, 24, 0, 0);
		inventory.displayInventory();
	}
	
	public void checkOut(){
		ShoppingCart shoppingCart=new ShoppingCart();
		//user input stuff
		transactions.add(new Transaction(shoppingCart.getBooks()));
		for(BookPurchase b : shoppingCart.getBooks()){
			inventory.sellBook(b);
		}
	}
	
	public void requestBooks(){
		ShoppingCart shoppingCart=new ShoppingCart();
		String customerName="";
		int customerPhone=0;
		String customerEmail="";
		//user input stuff
		orderRequests.add(new OrderRequest(shoppingCart.getBooks(),customerName,customerPhone,customerEmail));
	}
	
	public static void main(String[]args){
		new Controller();
	}
}
