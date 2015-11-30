package bookstore;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class BookstoreGUI {
	private JFrame frame;
	private JPanel content;
	private Controller controller;
	
	public BookstoreGUI(){
		controller=new Controller();
		frame = new JFrame("Rinku's Bookstore");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300,700);
		//frame.setResizable(false);
		
		JPanel checkout = new JPanel();
		checkout.setLayout(new GridLayout(1,2));
		
		JPanel currentBooks = new JPanel();
		ArrayList<BookPurchase> shoppingCartBooks = controller.getShoppingCart().getBooks();
		currentBooks.setLayout(new GridLayout(shoppingCartBooks.size()+1,5));
		currentBooks.add(new JLabel("Title"));
		currentBooks.add(new JLabel("Author"));
		currentBooks.add(new JLabel("Edition"));
		currentBooks.add(new JLabel("ISBN"));
		currentBooks.add(new JLabel(""));
		//findBooks.add(new JLabel("Used Price"));
		//findBooks.add(new JLabel("New Price"));
		for(BookPurchase b:shoppingCartBooks){
			currentBooks.add(new JLabel(b.getTitle()));
			currentBooks.add(new JLabel(b.getAuthor()));
			currentBooks.add(new JLabel(""+b.getEdition()));
			currentBooks.add(new JLabel(""+b.getIsbn()));
			JButton addToCart = new JButton("Remove from cart");
			addToCart.putClientProperty("book", b);
			addToCart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					controller.getShoppingCart().removeBook((BookPurchase)((JButton)event.getSource()).getClientProperty("book"));
					
				}
			});
			currentBooks.add(addToCart);
			//findBooks.add(new JLabel(""+b.getUsedPrice()));
			//findBooks.add(new JLabel(""+b.getNewPrice()));
		}
		
		JPanel findBooks = new JPanel();
		ArrayList<BookEntry> inventoryBooks = controller.getInventory().getBooks();
		findBooks.setLayout(new GridLayout(inventoryBooks.size()+1,5));
		findBooks.add(new JLabel("Title"));
		findBooks.add(new JLabel("Author"));
		findBooks.add(new JLabel("Edition"));
		findBooks.add(new JLabel("ISBN"));
		findBooks.add(new JLabel(""));
		//findBooks.add(new JLabel("Used Price"));
		//findBooks.add(new JLabel("New Price"));
		for(BookEntry b:inventoryBooks){
			findBooks.add(new JLabel(b.getTitle()));
			findBooks.add(new JLabel(b.getAuthor()));
			findBooks.add(new JLabel(""+b.getEdition()));
			findBooks.add(new JLabel(""+b.getIsbn()));
			JButton addToCart = new JButton("Add to cart");
			addToCart.putClientProperty("book", b);
			addToCart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					controller.getShoppingCart().addBook((BookEntry)((JButton)event.getSource()).getClientProperty("book"), 1, false);
					System.out.println(controller.getShoppingCart().getBooks());
					frame.getContentPane().revalidate();
				}
			});
			findBooks.add(addToCart);
			//findBooks.add(new JLabel(""+b.getUsedPrice()));
			//findBooks.add(new JLabel(""+b.getNewPrice()));
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
	
	public static void main(String[]args){
		new BookstoreGUI();
	}
}