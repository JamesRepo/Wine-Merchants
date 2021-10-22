import javax.swing.JOptionPane;
import java.math.BigDecimal;

/**
 * Main class.
 * Handles gathering input from J Option Panes to gather information on the customer.
 * Using this information a customer account object is created and then the GUI.
 * 
 * @author jameselner
 *
 */
public class Main {
	public static void main(String[] args) {
		GUI app = new GUI(new CustomerAccount(customerName(), customerBalance()));
	}
	/**
	 *  Method to retrieve the customers name.
	 *  If the JOptionPane is closed or cancelled the whole program will terminate.
	 *  This will accept anything as the customers name, it will only be rejected if the text field is left blank.
	 */
	public static String customerName() {
		// Variable for the customer name, gathered directly from the JOptionPane
		String nameInput = JOptionPane.showInputDialog(null, "Name:", "Enter Name", JOptionPane.QUESTION_MESSAGE);
		// If statement to determine if the user has cancelled or closed the pop up window. It terminates the program if so. 
		if (nameInput == null || nameInput.equals("")) {
			System.exit(0);
			return null;
		}
		else {
			// Returns the customers name to be used as a argument for the GUI creation. 
			return nameInput;
		}
	}
	/**
	 *  Method to retrieve the customers balance. 
	 *  Again, if the JOptionPane is closed or cancelled the program will terminate.
	 *  There is an infinite for loop in this method to continue prompting the user for a balance if they give invalid data. (i.e. not a number)
	 */
	public static BigDecimal customerBalance() {
		// Infinite loop to check the users input. Will keep prompting if invalid data is entered.
		for(;;) {
			String balanceCheck = JOptionPane.showInputDialog(null, "Balance:", "Enter Balance", JOptionPane.QUESTION_MESSAGE);
			if (balanceCheck == null || balanceCheck.equals("")) {
				System.exit(0);
				return null;
			}
			// Try catch to make sure that the user inputs a number as the balance.
			try {
				return new BigDecimal(balanceCheck);
			}
			catch(NumberFormatException e) {
				// If invalid data is entered this error message will appear. This is the only way the loop continues.
				JOptionPane.showMessageDialog(null, "That is not a number", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
