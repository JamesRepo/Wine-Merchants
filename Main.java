import javax.swing.JOptionPane;
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
		/**
		 *  Creates the GUI, gathering arguments from two methods defined in this class.
		 *  These methods get the Customer's name and balance from smaller windows.
		 *  Only one line in the main.
		 */
		GUI gui = new GUI(new CustomerAccount(customerName(), customerBalance()));
	}
	/**
	 *  Method to retrieve the customers name.
	 *  If the JOptionPane is closed or cancelled the whole program will terminate.
	 *  This will accept anything as the customers name, it will only be rejected if the text field is left blank. 
	 * @return
	 */
	public static String customerName() {
		// Variable for the customer name, gathered directly from the JOptionPane
		String nameInput = JOptionPane.showInputDialog(null, "Name:", "Enter Name", JOptionPane.QUESTION_MESSAGE);
		// If statement to determine if the user has cancelled or closed the pop up window. It terminates the program if so. 
		if (nameInput.equals(JOptionPane.CLOSED_OPTION) || nameInput.equals(JOptionPane.CANCEL_OPTION) || nameInput.equals("")) {
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
	 * @return
	 */
	public static double customerBalance() {
		// Variable to represent the balance, defined here to keep it global within the method. 
		double balanceInput = 0.0; 
		// Infinite loop to check the users input. Will keep prompting if invalid data is entered.
		for(;;) {
			String balanceCheck = JOptionPane.showInputDialog(null, "Balance:", "Enter Balance", JOptionPane.QUESTION_MESSAGE);
			// If statement to terminate the program should the user close the window or click cancel. 
			if (balanceCheck.equals(JOptionPane.CLOSED_OPTION) || balanceCheck.equals(JOptionPane.CANCEL_OPTION)) {
				System.exit(0);
				return 0;
			}
			else {
				// Try catch to make sure that the user inputs a number as the balance. 
				try {
					balanceInput = Double.parseDouble(balanceCheck);			
					return balanceInput;
				}
				catch(NumberFormatException e) {
					// If invalid data is entered this error message will appear. This is the only way the loop continues.
					JOptionPane.showMessageDialog(null, "That is not a number", "Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		}
	}
}
