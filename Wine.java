/**
 * Class to represent information about the wine. 
 * No operations performed here, it is purely for storing information. 
 * @author jameselner
 *
 */
public class Wine {
	// Instance variables
	private String name;
	private double price;
	private int quantity;
	/**
	 * Constructor to initialise
	 * Arguments:
	 * @param name
	 * @param price
	 * @param quantity
	 */
	public Wine(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	/**
	 * Get and set methods below
	 */
	// Set
	public void setName(String name) {
		this.name = name;
	}
	public void setprice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	// Get
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
}
