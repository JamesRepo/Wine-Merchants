/**
 * Class to define the Customer Account object. 
 * It's purpose is to store the customer's name and balance, performing operations on the balance too. 
 * 
 * @author jameselner
 *
 */
public class CustomerAccount {
	 // Instance variables 
	private String name;
	private double balance; 
	// Constant final variable to represent the percentage of service charge
	private final double SERVICE = 0.2;
	/**
	 * Constructor to initialise variables 
	 * Two arguments:
	 * @param name
	 * @param balance
	 */
	public CustomerAccount(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	/**
	 * Get and set methods below
	 */
	// Set
	public void setName(String name) {
		this.name = name;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	// Get
	public String getName() {
		return name;
	}
	public double getBalance() {
		return balance;
	}
	/**
	 * Methods for performing operations below
	 */
	// To process a sale
	public double processSale(double winePrice, int wineQuantity) {
		// Calculate total amount of the order
		double transactionTotal = winePrice * wineQuantity;
		// Update balance
		balance -= transactionTotal;
		// Return the transaction as a double (decimals)
		return transactionTotal;
	}
	// To process a return 
	public double processReturn(double winePrice, int wineQuantity) {
		// Calculate amount to take off return as service charge - There is a service charge of 20% on returns
		double serviceCharge = (winePrice * wineQuantity) * SERVICE;
		// Calculate how much to be refunded
		double transactionTotal = (winePrice * wineQuantity) - serviceCharge;
		// Update balance
		balance += transactionTotal;
		// Return the transaction
		return transactionTotal;
	}
}
