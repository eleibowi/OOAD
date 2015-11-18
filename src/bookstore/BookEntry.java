package bookstore;

public class BookEntry {
	private int isbn;
	private String title;
	private String author;
	private int edition;
	private int usedQuantity;
	private double usedPrice;
	private int newQuantity;
	private double newPrice;
	private double usedDiscount;
	private double newDiscount;
	private int numberRequested;
	
	public BookEntry(int isbn, String title, String author, int edition, int usedQuantity, double usedPrice, int newQuantity, double newPrice){
		this.isbn=isbn;
		this.title=title;
		this.author=author;
		this.edition=edition;
		this.usedQuantity=usedQuantity;
		this.usedPrice=usedPrice;
		this.newQuantity=newQuantity;
		this.newPrice=newPrice;
		usedDiscount=0;
		newDiscount=0;
		numberRequested=0;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public int getUsedQuantity() {
		return usedQuantity;
	}

	public void setUsedQuantity(int usedQuantity) {
		this.usedQuantity = usedQuantity;
	}

	public double getUsedPrice() {
		return usedPrice;
	}

	public void setUsedPrice(double usedPrice) {
		this.usedPrice = usedPrice;
	}

	public int getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(int newQuantity) {
		this.newQuantity = newQuantity;
	}

	public double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

	public int getNumberRequested() {
		return numberRequested;
	}

	public void setNumberRequested(int numberRequested) {
		this.numberRequested = numberRequested;
	}

	public double getUsedDiscount() {
		return usedDiscount;
	}

	public void setUsedDiscount(double usedDiscount) {
		this.usedDiscount = usedDiscount;
	}

	public double getNewDiscount() {
		return newDiscount;
	}

	public void setNewDiscount(double newDiscount) {
		this.newDiscount = newDiscount;
	}
}
