package bookstore;

public class BookPurchase extends BookEntry{
	private double price;
	private int quantity;
	private boolean used;
	
	public BookPurchase(BookEntry book, int quantity, boolean used) {
		super(book.getIsbn(),book.getTitle(),book.getAuthor(),book.getEdition(),book.getNewQuantity(),book.getNewPrice(),book.getUsedQuantity(),book.getUsedPrice());
		this.setQuantity(quantity);
		this.setUsed(used);
		if(used)
			setPrice((book.getUsedPrice()-book.getUsedDiscount())*quantity);
		else
			setPrice((book.getNewPrice()-book.getNewDiscount())*quantity);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
}
