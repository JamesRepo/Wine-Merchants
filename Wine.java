import java.math.BigDecimal;

/**
 * Class to represent information about the wine.
 * @author jameselner
 *
 */
public class Wine {
	// Instance variables
	private String name;
	private BigDecimal price;
	/**
	 * Constructor to initialise
	 */
	public Wine(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	/**
	 * Get and set methods below
	 */
	// Set
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	// Get
	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}
}
