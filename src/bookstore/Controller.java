package bookstore;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;

public class Controller {
	private Inventory inventory;
	private ArrayList<BookEntry> inventoryDisplay;
	private ArrayList<Transaction> transactions;
	private ArrayList<OrderRequest> orderRequests;
	private ShoppingCart shoppingCart;
	private JFrame frame;
	private JPanel checkout;
	private JPanel shoppingCartPanel;
	private JPanel inventoryPanel;
	private JPanel transactionPanel;
	
	public Controller(){
		inventory = new Inventory();
		transactions = new ArrayList<Transaction>();
		orderRequests = new ArrayList<OrderRequest>();
		inventory.addBook(1, "Gone with the Wind", "Margaret Mitchell", 1, 5, 24, 0, 0);
		inventory.addBook(2, "Romeo and Juliet", "William Shakespeare", 2, 10, 20, 5, 10);
		inventory.addBook(3, "Harry Potter", "JK Rowling", 1, 20, 18, 17, 10);
		inventory.addBook(4, "Catcher in the Rye", "JD Salinger", 4, 15, 15, 5, 2);
		inventory.addBook(5, "Broadband Telecommunications Management", "Riaz Esmailzadeh", 1, 7, 45, 0, 0);
		inventoryDisplay = inventory.getBooks();
		shoppingCart=new ShoppingCart();
		
		frame = new JFrame("Rinku's Bookstore");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300,700);
		//frame.setResizable(false);
		
		checkout=new JPanel();
		checkout.setLayout(new GridLayout(1,2));
		updateCheckoutPanel();
		
		JPanel returnPurchase = new JPanel();
		
		JPanel buyFromCustomer = new JPanel();
		
		JPanel manageInventory = new JPanel();
		
		transactionPanel = new JPanel();
		transactionPanel.setLayout(new GridLayout(21,1));
		updateTransactionPanel();
		
		JTabbedPane tabbedPane=new JTabbedPane();
		tabbedPane.addTab("Checkout", checkout);
		tabbedPane.addTab("Return Purchase", returnPurchase);
		tabbedPane.addTab("Buy From Customer", buyFromCustomer);
		tabbedPane.addTab("Manage Inventory", manageInventory);
		tabbedPane.addTab("Past Transactions", transactionPanel);
		frame.add(tabbedPane);
		
		frame.setVisible(true);
	}
	
	public JPanel getInventoryPanel(){
		JPanel findBooks = new JPanel();
		findBooks.setLayout(new GridLayout(21,1));
		
		JPanel findBooksTitle = new JPanel();
		findBooksTitle.setLayout(new GridLayout(1,2));
		findBooksTitle.add(new JLabel("Choose books from inventory"));
		
		JPanel searchInventory = new JPanel();
		searchInventory.setLayout(new GridLayout(1,3));
		searchInventory.add(new JLabel("Search"));
		JTextField searchField = new JTextField();
		searchInventory.add(searchField);
		
		JButton searchButton = new JButton("Go");
		searchButton.putClientProperty("searchKey", searchField);
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				inventoryDisplay = new ArrayList<BookEntry>();
				for(BookEntry b:inventory.getBooks()){
					String enteredText = ((JTextField)((JButton)event.getSource()).getClientProperty("searchKey")).getText().toLowerCase();
					if(b.getAuthor().toLowerCase().indexOf(enteredText)!=-1||b.getTitle().toLowerCase().indexOf(enteredText)!=-1||(""+b.getIsbn()).equals(enteredText))
						inventoryDisplay.add(b);
				}
				updateCheckoutPanel();
			}	
		});
		searchInventory.add(searchButton);
		
		findBooks.add(findBooksTitle);
		findBooks.add(searchInventory);
		
		JPanel findBooksHeading= new JPanel();
		findBooksHeading.setLayout(new GridLayout(1,2));
		
		JPanel findBooksHeadingLeft = new JPanel();
		findBooksHeadingLeft.setLayout(new GridLayout(1,2));
		findBooksHeadingLeft.add(new JLabel("Title"));
		findBooksHeadingLeft.add(new JLabel("Author"));
		findBooksHeading.add(findBooksHeadingLeft);
		
		JPanel findBooksHeadingRight = new JPanel();
		findBooksHeadingRight.setLayout(new GridLayout(1,3));
		
		JPanel findBooksHeadingRightLeft = new JPanel();
		findBooksHeadingRightLeft.setLayout(new GridLayout(1,2));
		findBooksHeadingRightLeft.add(new JLabel("Edition"));
		findBooksHeadingRightLeft.add(new JLabel("ISBN"));
		findBooksHeadingRight.add(findBooksHeadingRightLeft);
		
		findBooksHeadingRight.add(new JLabel("Quality"));
		
		JPanel findBooksHeadingRightRight = new JPanel();
		findBooksHeadingRightRight.setLayout(new GridLayout(1,2));
		findBooksHeadingRightRight.add(new JLabel("#"));
		findBooksHeadingRightRight.add(new JLabel("Add"));
		findBooksHeadingRight.add(findBooksHeadingRightRight);
		findBooksHeading.add(findBooksHeadingRight);
		findBooks.add(findBooksHeading);
		
		for(BookEntry b:inventoryDisplay){
			JPanel findBooksLine = new JPanel();
			findBooksLine.setLayout(new GridLayout(1,2));
			
			JPanel leftSide = new JPanel();
			leftSide.setLayout(new GridLayout(1,2));
			leftSide.add(new JLabel(b.getTitle()));
			leftSide.add(new JLabel(b.getAuthor()));
			findBooksLine.add(leftSide);
			
			JPanel rightSide = new JPanel();
			rightSide.setLayout(new GridLayout(1,3));
			
			JPanel rightLeft = new JPanel();
			rightLeft.setLayout(new GridLayout(1,2));
			rightLeft.add(new JLabel(""+b.getEdition()));
			rightLeft.add(new JLabel(""+b.getIsbn()));
			rightSide.add(rightLeft);
			
			String[] newUsedStrings = new String[2];
			if(b.getNewQuantity()>0)
				newUsedStrings[0]="New: $"+String.format("%.2f", b.getNewPrice());
			else
				newUsedStrings[0]="New: Out of Stock";
			if(b.getUsedQuantity()>0)
				newUsedStrings[1]="Used: $"+String.format("%.2f",b.getUsedPrice());
			else
				newUsedStrings[1]="Used: Out of Stock";
			JComboBox<String> newUsed = new JComboBox<String>(newUsedStrings);
			rightSide.add(newUsed);
			
			JPanel rightRight = new JPanel();
			rightRight.setLayout(new GridLayout(1,2));
			
			JTextField quantField = new JTextField("1");
			rightRight.add(quantField);
			
			JButton addToCart = new JButton("+");
			addToCart.putClientProperty("book", b);
			addToCart.putClientProperty("newUsed",newUsed);
			addToCart.putClientProperty("quantity",quantField);
			addToCart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					String quantString = ((JTextField)((JButton)event.getSource()).getClientProperty("quantity")).getText();
					//check if quantString is valid here
					if(((String)((JComboBox<String>)((JButton)event.getSource()).getClientProperty("newUsed")).getSelectedItem()).charAt(0)=='U'&&((String)((JComboBox<String>)((JButton)event.getSource()).getClientProperty("newUsed")).getSelectedItem()).indexOf("Out of Stock")==-1)
						shoppingCart.addBook((BookEntry)((JButton)event.getSource()).getClientProperty("book"), Integer.parseInt(quantString), true);
					else if(((String)((JComboBox<String>)((JButton)event.getSource()).getClientProperty("newUsed")).getSelectedItem()).charAt(0)=='N'&&((String)((JComboBox<String>)((JButton)event.getSource()).getClientProperty("newUsed")).getSelectedItem()).indexOf("Out of Stock")==-1)
						shoppingCart.addBook((BookEntry)((JButton)event.getSource()).getClientProperty("book"), Integer.parseInt(quantString), false);
					//shoppingCartPanel=getShoppingCartPanel();
					updateCheckoutPanel();
				}
			});
			rightRight.add(addToCart);
			rightSide.add(rightRight);
			findBooksLine.add(rightSide);
			findBooks.add(findBooksLine);
		}
		return findBooks;
	}
	
	public void updateCheckoutPanel(){
		checkout.removeAll();
		shoppingCartPanel = getShoppingCartPanel();
		checkout.add(shoppingCartPanel);
		inventoryPanel = getInventoryPanel();
		checkout.add(inventoryPanel);
		checkout.revalidate();
		checkout.repaint();
	}
	
	public JPanel getShoppingCartPanel(){
		JPanel currentBooks = new JPanel();
		//currentBooks.setLayout(new GridLayout(shoppingCart.getBooks().size()+1,5));
		currentBooks.setLayout(new GridLayout(21,1));
		
		JPanel cartTitle = new JPanel();
		cartTitle.add(new JLabel("Shopping Cart"));
		currentBooks.add(cartTitle);
		
		if(shoppingCart.getBooks().size()>0){
			JPanel cartHeading = new JPanel();
			cartHeading.setLayout(new GridLayout(1,5));
			cartHeading.add(new JLabel("Title"));
			cartHeading.add(new JLabel("Quality"));
			cartHeading.add(new JLabel("#"));
			cartHeading.add(new JLabel("Price"));
			cartHeading.add(new JLabel(""));
			currentBooks.add(cartHeading);
		}
		
		for(BookPurchase b:shoppingCart.getBooks()){
			JPanel currentBooksLine = new JPanel();
			currentBooksLine.setLayout(new GridLayout(1,5));
			currentBooksLine.add(new JLabel(b.getTitle()));
			if(b.isUsed())
				currentBooksLine.add(new JLabel("Used"));
			else
				currentBooksLine.add(new JLabel("New"));
			currentBooksLine.add(new JLabel(""+b.getQuantity()));
			currentBooksLine.add(new JLabel("$"+String.format("%.2f",b.getPrice())));
			JButton addToCart = new JButton("Remove");
			addToCart.putClientProperty("book", b);
			addToCart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					shoppingCart.removeBook((BookPurchase)((JButton)event.getSource()).getClientProperty("book"));
					updateCheckoutPanel();
				}
			});
			currentBooksLine.add(addToCart);
			currentBooks.add(currentBooksLine);
		}
		if(shoppingCart.getBooks().size()>0){
			JButton checkoutButton=new JButton("Check Out");
			checkoutButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					transactions.add(new Transaction(shoppingCart.getBooks()));
					for(BookPurchase b : shoppingCart.getBooks()){
						inventory.sellBook(b);
					}
					shoppingCart.setBooks(new ArrayList<BookPurchase>());
					updateCheckoutPanel();
					updateTransactionPanel();
				}
			});
			currentBooks.add(checkoutButton);
		}
		
		return currentBooks;
	}
	
	public void updateTransactionPanel(){
		transactionPanel.removeAll();
		
		JPanel transactionHeading = new JPanel();
		transactionHeading.setLayout(new GridLayout(1,5));
		transactionHeading.add(new JLabel("Time"));
		transactionHeading.add(new JLabel("Book"));
		transactionHeading.add(new JLabel("Quality"));
		transactionHeading.add(new JLabel("#"));
		transactionHeading.add(new JLabel("Price"));
		transactionPanel.add(transactionHeading);

		for(Transaction t: transactions){
			JPanel transactionRow=new JPanel();
			transactionRow.setLayout(new GridLayout(1,5));
			transactionRow.add(new JLabel(t.getTime().toString()));
			double totalPrice=0;
			for(BookPurchase b:t.getBooks()){
				transactionRow.add(new JLabel(b.getTitle()));
				if(b.isUsed())
					transactionRow.add(new JLabel("Used"));
				else
					transactionRow.add(new JLabel("New"));
				transactionRow.add(new JLabel(""+b.getQuantity()));
				transactionRow.add(new JLabel("$"+String.format("%.2f",b.getPrice())));
				transactionPanel.add(transactionRow);
				transactionRow=new JPanel();
				transactionRow.setLayout(new GridLayout(1,5));
				transactionRow.add(new JLabel(""));
				totalPrice+=b.getPrice();
			}
			transactionPanel.add(new JLabel("TotalPrice: $"+String.format("%.2f",totalPrice)));
			transactionPanel.add(new JLabel(""));
		}
		transactionPanel.revalidate();
		transactionPanel.repaint();
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