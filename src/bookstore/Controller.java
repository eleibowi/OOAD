package bookstore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Controller {
	private Inventory inventory;
	private ArrayList<BookEntry> inventoryDisplay;
	private ArrayList<Transaction> transactions;
	private ArrayList<OrderRequest> orderRequests;
	private ShoppingCart shoppingCart;
	private JFrame frame;
	private JTabbedPane tabbedPane;
	
	public Controller(){
		inventory = new Inventory();
		transactions = new ArrayList<Transaction>();
		orderRequests = new ArrayList<OrderRequest>();
		inventory.addBook(1, "Gone with the Wind", "Margaret Mitchell", 1, 5, 24, 0, 0);
		inventory.addBook(2, "Romeo and Juliet", "William Shakespeare", 2, 10, 20, 5, 10);
		inventory.addBook(3, "Harry Potter", "JK Rowling", 1, 20, 18, 17, 10);
		inventory.addBook(4, "Catcher in the Rye", "JD Salinger", 4, 15, 15, 5, 2);
		inventory.addBook(5, "Broadband Telecommunications Management", "Riaz Esmailzadeh", 1, 7, 45, 0, 0);
		inventory.addBook(6, "Othello", "William Shakespeare", 6, 12, 12, 4, 6);
		inventory.addBook(7, "Rich Dad Poor Dad", "Robert Kiyosaki", 3, 10, 20, 5, 10);
        inventory.addBook(8, "Steve Jobs: The Exclusive Biography", "Walter Isaacson", 1, 20, 18, 17, 10);
        inventory.addBook(9, "The Golden Tap", "Kashyap Deorah", 4, 15, 15, 5, 2);
        inventory.addBook(10, "World's Best Boyfriend", "Durjoy Dutta", 1, 7, 45, 0, 0);
        inventory.addBook(11, "To Kill A Mockingbird", "Harper Lee", 6, 12, 22, 4, 6);                                                
        inventory.addBook(12, "The Secret", "Rhonda Byrne", 1, 7, 45, 0, 0);
        inventory.addBook(13, "Heretic", "Harper Collins", 6, 12, 12, 4, 6);
        inventory.addBook(14, "Jesus Calling", "Young", 3, 10, 20, 5, 10);
        inventory.addBook(15, "Atlantis Cards", "Diana Cooper", 1, 20, 18, 17, 10);
		inventoryDisplay = inventory.getBooks();
		shoppingCart=new ShoppingCart();
		
		frame = new JFrame("Rinku's Bookstore");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300,700);
		
		tabbedPane=new JTabbedPane();
		updatePanels();
		frame.add(tabbedPane);
		
		frame.setVisible(true);
	}
	
	public void updatePanels(){
		int selected = tabbedPane.getSelectedIndex();
		if(selected==-1)
			selected++;
		tabbedPane.removeAll();
		tabbedPane.addTab("Checkout",buildPanel("Checkout"));
		tabbedPane.addTab("Manage Inventory",buildPanel("Manage Inventory"));
		tabbedPane.addTab("Past Transactions",buildPanel("Past Transactions"));
		tabbedPane.setSelectedIndex(selected);
	}
	
	//buildPanel is effectively a JPanel factory, which provides fully formed panels of each given type
	public JPanel buildPanel(String panelType){
		if(panelType.equals("Checkout")){
			JPanel findBooks = new JPanel();
			findBooks.setLayout(new GridLayout(21,1));
			
			JPanel findBooksTitle = new JPanel();
			findBooksTitle.setLayout(new GridLayout(1,2));
			findBooksTitle.add(new JLabel("Choose books from inventory"));
			
			JPanel searchInventory = new JPanel();
			searchInventory.add(new JLabel("Showing "+inventoryDisplay.size()+"/"+inventory.getBooks().size()+" books"));
			
			//Search for book by title, author, or isbn
			JTextField searchField = new JTextField();
			searchField.setPreferredSize(new Dimension(250,25));
			searchInventory.add(searchField);
			
			JButton searchButton = new JButton("Search");
			searchButton.putClientProperty("searchKey", searchField);
			searchButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					inventoryDisplay = new ArrayList<BookEntry>();
					for(BookEntry b:inventory.getBooks()){
						String enteredText = ((JTextField)((JButton)event.getSource()).getClientProperty("searchKey")).getText().toLowerCase();
						if(b.getAuthor().toLowerCase().indexOf(enteredText)!=-1||b.getTitle().toLowerCase().indexOf(enteredText)!=-1||(""+b.getIsbn()).equals(enteredText))
							inventoryDisplay.add(b);
					}
					updatePanels();
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
						int quantity = Integer.parseInt(((JTextField)((JButton)event.getSource()).getClientProperty("quantity")).getText());
						boolean used = false;
						if(((String)((JComboBox<String>)((JButton)event.getSource()).getClientProperty("newUsed")).getSelectedItem()).charAt(0)=='U')
							used=true;
						for(int i=0;i<shoppingCart.getBooks().size();i++)
							if(shoppingCart.getBooks().get(i).getIsbn()==((BookEntry)((JButton)event.getSource()).getClientProperty("book")).getIsbn()){
								shoppingCart.addQuantity(shoppingCart.getBooks().get(i),quantity);
								updatePanels();
								return;
							}
						shoppingCart.addBook((BookEntry)((JButton)event.getSource()).getClientProperty("book"), quantity, used);
						updatePanels();
					}
				});
				rightRight.add(addToCart);
				rightSide.add(rightRight);
				findBooksLine.add(rightSide);
				findBooks.add(findBooksLine);
			}
			JPanel currentBooks = new JPanel();
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
				JButton removeButton = new JButton("Remove");
				removeButton.putClientProperty("book", b);
				removeButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						shoppingCart.removeBook((BookPurchase)((JButton)event.getSource()).getClientProperty("book"));
						updatePanels();
					}
				});
				removeButton.setPreferredSize(new Dimension(100,25));
				JPanel removeButtonPanel = new JPanel();
				removeButtonPanel.add(removeButton);
				currentBooksLine.add(removeButtonPanel);
				currentBooks.add(currentBooksLine);
			}
			if(shoppingCart.getBooks().size()>0){
				currentBooks.add(new JLabel("Total Price: $"+String.format("%.2f",shoppingCart.getTotalPrice())));
				JButton checkoutButton=new JButton("Check Out");
				checkoutButton.setPreferredSize(new Dimension(500,25));
				checkoutButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						JFrame checkoutFrame = new JFrame();
						checkoutFrame.setTitle("Payment Method");
						checkoutFrame.setSize(400,400);
						
						JPanel cashPayment = new JPanel();
						cashPayment.setLayout(new GridLayout(2,1));
			
						JPanel cashTopHalf = new JPanel();
						JLabel amountDueLabel = new JLabel("Amount Due: $"+String.format("%.2f",shoppingCart.getTotalPrice()));
						amountDueLabel.setPreferredSize(new Dimension(300,25));
						cashTopHalf.add(amountDueLabel);
						cashTopHalf.add(new JLabel("Customer Paid: $"));
						JTextField paidCashField = new JTextField(String.format("%.2f",shoppingCart.getTotalPrice()));
						paidCashField.setPreferredSize(new Dimension(100,25));
						cashTopHalf.add(paidCashField);
						
						JPanel cashBottomHalf = new JPanel();
						cashBottomHalf.setLayout(new CardLayout());
						
						JPanel beforePayment = new JPanel();
						JButton getChangeButton = new JButton("Confirm");
						getChangeButton.putClientProperty("entered", paidCashField);
						getChangeButton.putClientProperty("bottomHalf", cashBottomHalf);
						getChangeButton.putClientProperty("frame",checkoutFrame);
						getChangeButton.setPreferredSize(new Dimension(300,25));
						getChangeButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent event){
								JPanel afterPayment = new JPanel();
								double change = Double.parseDouble(((String)((JTextField)((JButton)event.getSource()).getClientProperty("entered")).getText()))-shoppingCart.getTotalPrice();
								if(change<0)
									return;
								JLabel changeDue = new JLabel("Change due: $"+String.format("%.2f",change));
								changeDue.setPreferredSize(new Dimension(300,25));
								JButton confirmButton = new JButton("Confirm");
								confirmButton.setPreferredSize(new Dimension(200,25));
								confirmButton.putClientProperty("frame", (JFrame)((JButton)event.getSource()).getClientProperty("frame"));
								confirmButton.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent event){
										transactions.add(new Transaction(shoppingCart.getBooks(),"Cash"));
										for(BookPurchase b : shoppingCart.getBooks()){
											inventory.sellBook(b);
										}
										shoppingCart.setBooks(new ArrayList<BookPurchase>());
										updatePanels();
										((JFrame)((JButton)event.getSource()).getClientProperty("frame")).dispose();
									}
								});
								afterPayment.add(changeDue);
								afterPayment.add(confirmButton);
								JPanel bottomHalf = ((JPanel)((JButton)event.getSource()).getClientProperty("bottomHalf"));
								bottomHalf.add(afterPayment,"after");
								((CardLayout)(bottomHalf.getLayout())).show(bottomHalf, "after");
							}
						});
						beforePayment.add(getChangeButton);
						cashBottomHalf.add(beforePayment,"before");
						((CardLayout)cashBottomHalf.getLayout()).show(cashBottomHalf, "before");
						
						cashPayment.add(cashTopHalf);
						cashPayment.add(cashBottomHalf);
						
						JPanel debitPayment = new JPanel();
						debitPayment.add(new JLabel("Debit Card Payment"));
						
						JPanel creditPayment = new JPanel();
						creditPayment.add(new JLabel("Credit Card Payment"));
						
						JPanel paymentTypes = new JPanel();
						paymentTypes.setLayout(new CardLayout());
						paymentTypes.add(cashPayment,"cash");
						paymentTypes.add(debitPayment,"debit");
						paymentTypes.add(creditPayment,"credit");
						((CardLayout)paymentTypes.getLayout()).show(paymentTypes,"cash");
						
						JRadioButton cashRadio = new JRadioButton("Cash");
						cashRadio.putClientProperty("paymentTypes", paymentTypes);
						cashRadio.setSelected(true);
						cashRadio.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent event){
								((CardLayout)((JPanel)((JRadioButton)event.getSource()).getClientProperty("paymentTypes")).getLayout()).show((JPanel)((JRadioButton)event.getSource()).getClientProperty("paymentTypes"),"cash");
							}
						});
						JRadioButton debitRadio = new JRadioButton("Debit card");
						debitRadio.putClientProperty("paymentTypes", paymentTypes);
						debitRadio.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent event){
								((CardLayout)((JPanel)((JRadioButton)event.getSource()).getClientProperty("paymentTypes")).getLayout()).show((JPanel)((JRadioButton)event.getSource()).getClientProperty("paymentTypes"),"debit");
							}
						});
						JRadioButton creditRadio = new JRadioButton("Credit card");
						creditRadio.putClientProperty("paymentTypes", paymentTypes);
						creditRadio.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent event){
								((CardLayout)((JPanel)((JRadioButton)event.getSource()).getClientProperty("paymentTypes")).getLayout()).show((JPanel)((JRadioButton)event.getSource()).getClientProperty("paymentTypes"),"credit");
							}
						});
						ButtonGroup group = new ButtonGroup();
						group.add(cashRadio);
						group.add(debitRadio);
						group.add(creditRadio);
						JPanel checkoutFramePanel = new JPanel();
						checkoutFramePanel.setLayout(new BorderLayout());
						JPanel buttonsPanel = new JPanel();
						buttonsPanel.add(cashRadio);
						buttonsPanel.add(debitRadio);
						buttonsPanel.add(creditRadio);
						
						checkoutFramePanel.add(buttonsPanel,BorderLayout.PAGE_START);
						checkoutFramePanel.add(paymentTypes);
						
						checkoutFrame.add(checkoutFramePanel);
						checkoutFrame.setVisible(true);
						
					}
				});
				JPanel checkoutButtonPanel = new JPanel();
				checkoutButtonPanel.add(checkoutButton);
				currentBooks.add(checkoutButtonPanel);
			}
			JPanel checkout=new JPanel();
			checkout.setLayout(new GridLayout(1,2));
			checkout.add(currentBooks);
			checkout.add(findBooks);
			return checkout;
		}
		if(panelType.equals("Manage Inventory")){
			JPanel manageInventoryPanel = new JPanel();
			manageInventoryPanel.setLayout(new BorderLayout());
			
			JPanel searchInventory = new JPanel();
			searchInventory.add(new JLabel("Showing "+inventoryDisplay.size()+"/"+inventory.getBooks().size()+" books"));
			JTextField searchField = new JTextField();
			searchField.setPreferredSize(new Dimension(250,25));
			searchInventory.add(searchField);
			
			JButton searchButton = new JButton("Search");
			searchButton.putClientProperty("searchKey", searchField);
			searchButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					inventoryDisplay = new ArrayList<BookEntry>();
					for(BookEntry b:inventory.getBooks()){
						String enteredText = ((JTextField)((JButton)event.getSource()).getClientProperty("searchKey")).getText().toLowerCase();
						if(b.getAuthor().toLowerCase().indexOf(enteredText)!=-1||b.getTitle().toLowerCase().indexOf(enteredText)!=-1||(""+b.getIsbn()).equals(enteredText))
							inventoryDisplay.add(b);
					}
					updatePanels();
				}	
			});
			searchInventory.add(searchButton);
			
			JButton addBookButton = new JButton("Add Book");
			addBookButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					JFrame addBookFrame = new JFrame();
					addBookFrame.setTitle("Add new book");
					JPanel addBookPanel = new JPanel();
					addBookPanel.setLayout(new GridLayout(9,1));
					String[] fieldNames = {"ISBN","Title","Author","Edition","New Quantity","New Price","Used Quantity","Used Price"};
					JTextField[] fields = new JTextField[8];
					for(int i=0;i<8;i++){
						JPanel fieldPanel = new JPanel();
						JLabel fieldLabel = new JLabel(fieldNames[i]+":");
						fieldLabel.setPreferredSize(new Dimension(100,30));
						fields[i] = new JTextField();
						fields[i].setPreferredSize(new Dimension(200,30));
						fieldPanel.add(fieldLabel);
						fieldPanel.add(fields[i]);
						addBookPanel.add(fieldPanel);
					}
					JPanel submitButtonPanel = new JPanel();
					JButton submitButton = new JButton("Submit");
					submitButton.setPreferredSize(new Dimension(200,25));
					submitButton.putClientProperty("fields", fields);
					submitButton.putClientProperty("frame", addBookFrame);
					submitButton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent event2){
							JTextField[] fields = ((JTextField[])((JButton)event2.getSource()).getClientProperty("fields"));
							//add error checking here
							inventory.addBook(Integer.parseInt(fields[0].getText()), fields[1].getText(), fields[2].getText(), Integer.parseInt(fields[3].getText()), Integer.parseInt(fields[4].getText()), Double.parseDouble(fields[5].getText()), Integer.parseInt(fields[6].getText()), Double.parseDouble(fields[7].getText()));
							updatePanels();
							JFrame frame =((JFrame)((JButton)event2.getSource()).getClientProperty("frame"));
							frame.dispose();
						}
					});
					submitButtonPanel.add(submitButton);
					addBookPanel.add(submitButtonPanel);
					
					addBookFrame.setSize(500,400);
					addBookFrame.add(addBookPanel);
					addBookFrame.setVisible(true);
				}
			});
			searchInventory.add(addBookButton);
			
			String[] columnHeadings = {"ISBN","Title","Author","Edition","New #","New Price","Used #","Used Price"};
			Object[][] tableContents = new Object[inventoryDisplay.size()][8];
			for(int i=0;i<inventoryDisplay.size();i++){
				tableContents[i][0]=inventoryDisplay.get(i).getIsbn();
				tableContents[i][1]=inventoryDisplay.get(i).getTitle();
				tableContents[i][2]=inventoryDisplay.get(i).getAuthor();
				tableContents[i][3]=inventoryDisplay.get(i).getEdition();
				tableContents[i][4]=inventoryDisplay.get(i).getNewQuantity();
				tableContents[i][5]=inventoryDisplay.get(i).getNewPrice();
				tableContents[i][6]=inventoryDisplay.get(i).getUsedQuantity();
				tableContents[i][7]=inventoryDisplay.get(i).getUsedPrice();
			}
			
			JTable inventoryTable = new JTable(tableContents,columnHeadings);
			inventoryTable.setModel(new DefaultTableModel(tableContents,columnHeadings){
				//Turn off editing for ISBN values
				public boolean isCellEditable(int row, int column){
					if(column==0||column==8)
						return false;
					return true;
				}
				
				//Specify classes for values, for sorting
				public Class getColumnClass(int column){
					if(column==1||column==2)
						return String.class;
					if(column==5||column==7)
						return Double.class;
					if(column==8)
						return JButton.class;
					return Integer.class;
				}
			});
			
			//Update inventory when table values are changed
			inventoryTable.getModel().addTableModelListener(new TableModelListener(){
				public void tableChanged(TableModelEvent e){
					int changedItem=e.getFirstRow();
					for(int i=0;i<inventory.getBooks().size();i++)
						if((int)((TableModel)e.getSource()).getValueAt(changedItem,0)==inventory.getBooks().get(i).getIsbn()){
							BookEntry changedEntry = inventory.getBooks().get(i);
							changedEntry.setTitle((String)((TableModel)e.getSource()).getValueAt(changedItem,1));
							changedEntry.setAuthor((String)((TableModel)e.getSource()).getValueAt(changedItem,2));
							changedEntry.setEdition((int)((TableModel)e.getSource()).getValueAt(changedItem,3));
							changedEntry.setNewQuantity((int)((TableModel)e.getSource()).getValueAt(changedItem,4));
							changedEntry.setNewPrice((double)((TableModel)e.getSource()).getValueAt(changedItem,5));
							changedEntry.setUsedQuantity((int)((TableModel)e.getSource()).getValueAt(changedItem,6));
							changedEntry.setUsedPrice((double)((TableModel)e.getSource()).getValueAt(changedItem,7));
						}
					updatePanels();
				}
			});
			
			//Set the ISBN column to justify left
			DefaultTableCellRenderer isbnColRenderer = new DefaultTableCellRenderer();
			isbnColRenderer.setHorizontalAlignment(SwingConstants.LEFT);
			inventoryTable.getColumnModel().getColumn(0).setCellRenderer(isbnColRenderer);
			
			//Add table's sort functionality
			inventoryTable.setAutoCreateRowSorter(true);
			
			JButton removeBookButton = new JButton("Delete Selected");
			removeBookButton.putClientProperty("table", inventoryTable);
			removeBookButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					JTable table = (JTable)((JButton)event.getSource()).getClientProperty("table");
					for(int i:table.getSelectedRows())
						for(int j=0;j<inventory.getBooks().size();j++)
							if(inventory.getBooks().get(j).getIsbn()==(int)table.getValueAt(i,0))
								inventory.removeBook(inventory.getBooks().get(j));
					updatePanels();
				}
			});
			searchInventory.add(removeBookButton);
			
			manageInventoryPanel.add(searchInventory,BorderLayout.PAGE_START);
			
			JScrollPane scrollPane = new JScrollPane(inventoryTable);
			inventoryTable.setFillsViewportHeight(true);
			manageInventoryPanel.add(scrollPane);
			return manageInventoryPanel;
		}
		if(panelType.equals("Past Transactions")){
			JPanel transactionPanel = new JPanel();
			transactionPanel.setLayout(new BorderLayout());
			String[] columnHeadings = {"Transaction ID","Time","Book","Quality","#","Price","Payment Type"};
			int numRows = 0;
			for(Transaction t:transactions)
				numRows+=t.getBooks().size();
			int rowCount=0;
			Object[][] tableContents = new Object[numRows][7];
			for(int i=0;i<transactions.size();i++){
				for(int j=0;j<transactions.get(i).getBooks().size();j++){
					tableContents[rowCount][0]=transactions.get(i).getId();
					tableContents[rowCount][1]=transactions.get(i).getTime();
					tableContents[rowCount][2]=transactions.get(i).getBooks().get(j).getTitle();
					if(transactions.get(i).getBooks().get(j).isUsed())
						tableContents[rowCount][3]="Used";
					else
						tableContents[rowCount][3]="New";
					tableContents[rowCount][4]=transactions.get(i).getBooks().get(j).getQuantity();
					tableContents[rowCount][5]=transactions.get(i).getBooks().get(j).getPrice();
					tableContents[rowCount][6]=transactions.get(i).getPaymentType();
					rowCount++;
				}
				
			}
			
			JTable transactionTable = new JTable(tableContents,columnHeadings);
			transactionTable.setModel(new DefaultTableModel(tableContents,columnHeadings){
				//Turn off editing
				public boolean isCellEditable(int row, int column){
					return false;
				}
				
				//Specify classes for values, for sorting
				public Class getColumnClass(int column){
					if(column==0||column==4)
						return Integer.class;
					if(column==5)
						return Double.class;
					if(column==1)
						return Date.class;
					return String.class;
				}
			});
			
			//Set the ID column to justify left
			DefaultTableCellRenderer idColRenderer = new DefaultTableCellRenderer();
			idColRenderer.setHorizontalAlignment(SwingConstants.LEFT);
			transactionTable.getColumnModel().getColumn(0).setCellRenderer(idColRenderer);
			transactionTable.getColumnModel().getColumn(4).setCellRenderer(idColRenderer);
			transactionTable.getColumnModel().getColumn(5).setCellRenderer(idColRenderer);
			
			JPanel returnPanel = new JPanel();
			JButton returnButton = new JButton("Return selected books");
			returnButton.putClientProperty("table",transactionTable);
			returnButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					JTable table = (JTable)((JButton)event.getSource()).getClientProperty("table");
					ArrayList<BookPurchase> selectedPurchases = new ArrayList<BookPurchase>();
					int transactionId = -1;
					for(int i:table.getSelectedRows())
						for(Transaction t:transactions)
							if((int)table.getValueAt(i, 0)==t.getId()){
								if(transactionId==-1)
									transactionId=t.getId();
								else if(transactionId!=t.getId())
									return;
								for(BookPurchase b:t.getBooks())
									if(b.getTitle().equals(table.getValueAt(i, 2))&&b.getPrice()==(double)table.getValueAt(i,5))
										selectedPurchases.add(b);
							}
					if(selectedPurchases.size()>0){
						JFrame returnFrame = new JFrame();
						JPanel returnPanel = new JPanel();
						returnPanel.setLayout(new GridLayout(1+selectedPurchases.size(),1));
						returnFrame.setSize(500,selectedPurchases.size()*30+100);
						ArrayList<JTextField> returnFields = new ArrayList<JTextField>();
						for(BookPurchase b:selectedPurchases){
							JPanel returnSelection = new JPanel();
							JLabel returnLabel = new JLabel("Returning "+b.getTitle());
							JTextField returnField = new JTextField(""+b.getQuantity());
							returnField.setPreferredSize(new Dimension(100,25));
							returnFields.add(returnField);
							returnSelection.add(returnLabel);
							returnSelection.add(returnField);
							returnPanel.add(returnSelection);
						}
						JButton confirmReturn = new JButton("Confirm");
						confirmReturn.putClientProperty("selected",selectedPurchases);
						confirmReturn.putClientProperty("fields", returnFields);
						confirmReturn.putClientProperty("transactionId",transactionId);
						confirmReturn.putClientProperty("frame",returnFrame);
						confirmReturn.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent event){
								ArrayList<BookPurchase> selectedPurchases = (ArrayList<BookPurchase>)((JButton)event.getSource()).getClientProperty("selected");
								ArrayList<JTextField> returnFields = (ArrayList<JTextField>)((JButton)event.getSource()).getClientProperty("fields");
								int transactionId = (int)((JButton)event.getSource()).getClientProperty("transactionId");
								for(int i=0;i<returnFields.size();i++)
									if(Integer.parseInt(returnFields.get(i).getText())>selectedPurchases.get(i).getQuantity())
										return;
								for(int i=0;i<transactions.size();i++){
									if(transactions.get(i).getId()==transactionId)
										for(int j=0;j<transactions.get(i).getBooks().size();j++)
											for(int k=0;k<selectedPurchases.size();k++)
												if(transactions.get(i).getBooks().get(j).equals(selectedPurchases.get(k))){
													BookPurchase newRecord = transactions.get(i).getBooks().get(j);
													newRecord.setQuantity(newRecord.getQuantity()-Integer.parseInt(returnFields.get(k).getText()));
													transactions.get(i).getBooks().set(j,newRecord);
													boolean used=false;
													if(selectedPurchases.get(k).isUsed())
														used=true;
													inventory.restockBook(selectedPurchases.get(k), Integer.parseInt(returnFields.get(k).getText()), used);
													inventory.setMoneyInRegister(inventory.getMoneyInRegister()+selectedPurchases.get(k).getPrice()-newRecord.getPrice());
												}
								}
								((JFrame)((JButton)event.getSource()).getClientProperty("frame")).dispose();
								updatePanels();
							}
						});
						JPanel confirmReturnPanel = new JPanel();
						confirmReturn.setPreferredSize(new Dimension(200,25));
						confirmReturnPanel.add(confirmReturn);
						returnPanel.add(confirmReturnPanel);
						
						returnFrame.add(returnPanel);
						returnFrame.setVisible(true);
					}
				}
			});
			returnPanel.add(returnButton);
			transactionPanel.add(returnPanel,BorderLayout.PAGE_START);
			
			//Add table's sort functionality
			transactionTable.setAutoCreateRowSorter(true);
			
			JScrollPane scrollPane = new JScrollPane(transactionTable);
			transactionTable.setFillsViewportHeight(true);
			transactionPanel.add(scrollPane);
			return transactionPanel;
		}
		return null;
	}
	
	public static void main(String[]args){
		new Controller();
	}
}