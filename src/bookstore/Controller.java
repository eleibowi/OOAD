package bookstore;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Controller {
	private Inventory inventory;
	private ArrayList<Transaction> transactions;
	private ArrayList<OrderRequest> orderRequests;
	private ShoppingCart shoppingCart;
	private JFrame frame;
	
	public Controller(){
		inventory = new Inventory();
		transactions = new ArrayList<Transaction>();
		orderRequests = new ArrayList<OrderRequest>();
		inventory.addBook(1, "Gone with the Wind", "Margaret Mitchell", 1, 5, 24, 0, 0);
		inventory.addBook(2, "Romeo and Juliet", "William Shakespeare", 2, 10, 20, 5, 10);
		inventory.addBook(3, "Harry Potter", "JK Rowling", 1, 20, 18, 17, 10);
		inventory.addBook(4, "Catcher in the Rye", "JD Salinger", 4, 15, 15, 5, 2);
		inventory.addBook(5, "Broadband Telecommunications Management", "Riaz Ezmailzadeh", 1, 7, 45, 0, 0);
		inventory.displayInventory();
		shoppingCart=new ShoppingCart();
		
		frame = new JFrame("Rinku's Bookstore");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300,700);
		//frame.setResizable(false);
		
		JPanel checkout = new JPanel();
		checkout.setLayout(new GridLayout(1,2));
		
		JPanel currentBooks = new JPanel();
		currentBooks.setLayout(new GridLayout(shoppingCart.getBooks().size()+1,5));
		currentBooks.add(new JLabel("Title"));
		currentBooks.add(new JLabel("Author"));
		currentBooks.add(new JLabel("Edition"));
		currentBooks.add(new JLabel("ISBN"));
		currentBooks.add(new JLabel(""));
		//findBooks.add(new JLabel("Used Price"));
		//findBooks.add(new JLabel("New Price"));
		for(BookPurchase b:shoppingCart.getBooks()){
			currentBooks.add(new JLabel(b.getTitle()));
			currentBooks.add(new JLabel(b.getAuthor()));
			currentBooks.add(new JLabel(""+b.getEdition()));
			currentBooks.add(new JLabel(""+b.getIsbn()));
			JButton addToCart = new JButton("Remove from cart");
			addToCart.putClientProperty("book", b);
			addToCart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					shoppingCart.removeBook((BookPurchase)((JButton)event.getSource()).getClientProperty("book"));
					
				}
			});
			currentBooks.add(addToCart);
			//findBooks.add(new JLabel(""+b.getUsedPrice()));
			//findBooks.add(new JLabel(""+b.getNewPrice()));
		}
		
		JPanel findBooks = new JPanel();
		findBooks.setLayout(new GridLayout(inventory.getBooks().size()+1,6));
		findBooks.add(new JLabel("Title"));
		findBooks.add(new JLabel("Author"));
		findBooks.add(new JLabel("Edition"));
		findBooks.add(new JLabel("ISBN"));
		findBooks.add(new JLabel("Quality"));
		findBooks.add(new JLabel(""));
		for(BookEntry b:inventory.getBooks()){
			findBooks.add(new JLabel(b.getTitle()));
			findBooks.add(new JLabel(b.getAuthor()));
			findBooks.add(new JLabel(""+b.getEdition()));
			findBooks.add(new JLabel(""+b.getIsbn()));
			String[] newUsedStrings = new String[2];
			if(b.getNewQuantity()>0)
				newUsedStrings[0]="New: $"+b.getNewPrice();
			else
				newUsedStrings[0]="New: Out of Stock";
			if(b.getUsedQuantity()>0)
				newUsedStrings[1]="Used: $"+b.getUsedPrice();
			else
				newUsedStrings[1]="Used: Out of Stock";
			JComboBox<String> newUsed = new JComboBox<String>(newUsedStrings);
			findBooks.add(newUsed);
			JButton addToCart = new JButton("Add to cart");
			addToCart.putClientProperty("book", b);
			addToCart.putClientProperty("newUsed",newUsed);
			addToCart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					if(((String)((JComboBox<String>)((JButton)event.getSource()).getClientProperty("newUsed")).getSelectedItem()).charAt(0)=='U'&&((String)((JComboBox<String>)((JButton)event.getSource()).getClientProperty("newUsed")).getSelectedItem()).indexOf("Out of Stock")==-1)
						shoppingCart.addBook((BookEntry)((JButton)event.getSource()).getClientProperty("book"), 1, true);
					else if(((String)((JComboBox<String>)((JButton)event.getSource()).getClientProperty("newUsed")).getSelectedItem()).charAt(0)=='N'&&((String)((JComboBox<String>)((JButton)event.getSource()).getClientProperty("newUsed")).getSelectedItem()).indexOf("Out of Stock")==-1)
						shoppingCart.addBook((BookEntry)((JButton)event.getSource()).getClientProperty("book"), 1, false);
				}
			});
			findBooks.add(addToCart);
		}
		
		checkout.add(currentBooks);
		checkout.add(findBooks);
		
		JPanel returnPurchase = new JPanel();
		
		JPanel buyFromCustomer = new JPanel();
		
		JPanel manageInventory = new JPanel();
		
		JTabbedPane tabbedPane=new JTabbedPane();
		tabbedPane.addTab("Checkout", checkout);
		tabbedPane.addTab("Return Purchase", returnPurchase);
		tabbedPane.addTab("Buy From Customer", buyFromCustomer);
		tabbedPane.addTab("Manage Inventory", manageInventory);
		frame.add(tabbedPane);
		
		frame.setVisible(true);
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