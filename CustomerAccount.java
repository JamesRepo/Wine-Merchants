import java.math.BigDecimal;

/**
 * Class to define the Customer Account object. 
 * It's purpose is to store the customer's name and balance.
 * 
 * @author jameselner
 *
 */
public class CustomerAccount {
	 // Instance variables 
	private String name;
	private BigDecimal balance;

	/**
	 * Constructor to initialise variables
	 */
	public CustomerAccount(String name, BigDecimal balance) {
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
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	// Get
	public String getName() {
		return name;
	}
	public BigDecimal getBalance() {
		return balance;
	}

}
