import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Class to handle the GUI. 
 * It will handle user interaction and what the user can see. (Controller and View) 
 * 
 * @author jameselner
 *
 */
public class GUI extends JFrame implements ActionListener {
	// Swing components
	private JButton saleButton, returnButton;
	private JLabel nameLabel, quantityLabel, priceLabel, purchaseLabel, transactionLabel, balanceLabel;
	private JTextField nameText, quantityText, priceText, purchaseText, transactionText, balanceText;
	private JPanel north, centre, south;
	// Variables for use with Wine object
	private String wineName;
	private int wineQuantity;
	private double winePrice;
	// Variables for use with Customer Account object
	private CustomerAccount newAccount;
	private double customerBalance;
	// Variables for updating the GUI
	private double transaction;
	private double newBalance;
	/**
	 * Constructor.
	 * Outlines size, location, close operation etc for GUI. 
	 * Also calls methods to instantiate and layout.
	 * 
	 * Arguments:
	 * @param account
	 */
	public GUI(CustomerAccount account) {
		// Set up of GUI
		this.newAccount = account;
		this.customerBalance = newAccount.getBalance();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 200);
		setLocation(100, 100);
		setTitle("Wine Merchants: " + newAccount.getName());
		componentInstansiate();
		guiLayout();
		setBalance(customerBalance);
		// Initialise variables
		wineName = "";
		wineQuantity = 0;
		winePrice = 0;
		customerBalance = 0;
		transaction = 0;
		newBalance = 0;
	}
	/**
	 * This method handles the instantiation of all of the components
	 */
	public void componentInstansiate() {
		// Create buttons
		saleButton = new JButton("Process Sale");
		returnButton = new JButton("Process Return");
		// Create labels
		nameLabel = new JLabel("Name:");
		quantityLabel = new JLabel("Quantity:");
		priceLabel = new JLabel("Price: £");
		purchaseLabel = new JLabel("Wine purchased:");
		transactionLabel = new JLabel("Amount of Transaction: £");
		balanceLabel = new JLabel("Current balance: £");
		// Create text fields
		nameText = new JTextField("", 15);
		quantityText = new JTextField("", 5);
		priceText = new JTextField("", 5);
		purchaseText = new JTextField("", 15);
		transactionText = new JTextField("", 5);
		balanceText = new JTextField("", 10);
		// Create panels
		north = new JPanel();
		centre = new JPanel();
		south = new JPanel();
		// These text fields should only be able to be updated by the program so need to make them un-editable
		purchaseText.setEditable(false);
		transactionText.setEditable(false);
		balanceText.setEditable(false);
		// Adding action handling for the buttons
		saleButton.addActionListener(this);
		returnButton.addActionListener(this);
	}
	/**
	 * This method handles the layout of the GUI
	 */
	public void guiLayout() {
		// North panel
		north.add(nameLabel);
		north.add(nameText);
		north.add(priceLabel);
		north.add(priceText);
		north.add(quantityLabel);
		north.add(quantityText);
		// Ties the north panel to the top of the GUI
		add(north, BorderLayout.NORTH);
		// Centre panel
		centre.add(saleButton);
		centre.add(returnButton);
		// Tie to the middle
		add(centre, BorderLayout.CENTER);
		// South panel
		south.add(transactionLabel);
		south.add(transactionText);
		south.add(balanceLabel);
		south.add(balanceText);
		south.add(purchaseLabel);
		south.add(purchaseText);
		// Tie to the bottom
		add(south, BorderLayout.SOUTH);
	}
	/**
	 * Method for event handling. 
	 * Certain methods are called whatever button is pressed. These are either side of the if/else statement.
	 * The if/else statement specifies which method is used depending on the button pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		// Process is always called, gathers the input from text fields.
		process();
		// If / else to determine which button was pressed.
		if (e.getSource() == saleButton) {
			processSale();
		}
		else if (e.getSource() == returnButton) {
			processReturn();
		}
		// Updates the balance on the GUI.
		newBalance = newAccount.getBalance();
		setBalance(newBalance);
		// Clears the text fields so new data can be entered. Last thing done when a button is pressed.
		clearFields();
	}
	/**
	 * Method called if either button is pressed.
	 * It retrieves the input from the user from the text fields.
	 * It also checks that the data entered in the price and quantity boxes is correct (i.e. a number).
	 * Creates a wine object to store the data.
	 */
	public void process() {
		// Retrieves the name of the wine and sets the label to that name.
		wineName = nameText.getText();
		purchaseText.setText(wineName);
		// Try catch block to make sure that data is valid
		try {
			winePrice = Double.parseDouble(priceText.getText());			
		}
		catch(NumberFormatException e) {
			// J Option Pane to show error message
			JOptionPane.showMessageDialog(null, "Invalid data entered for wine price", "Error", JOptionPane.ERROR_MESSAGE);
		}	
		// Try catch block to make sure data is valid
		try {
			wineQuantity = Integer.parseInt(quantityText.getText());			

		}
		catch(NumberFormatException e) {
			// J Option Pane to show error message
			JOptionPane.showMessageDialog(null, "Invalid data entered for wine quantity", "Error", JOptionPane.ERROR_MESSAGE);
		}	
		// Creates a wine object with the data that has been retrieved
		Wine wine = new Wine(wineName, winePrice, wineQuantity);
	}
	/**
	 * This method is called if the process sale button is pressed.
	 * It calls the method in the Customer Account class which handles the actual mathematics.
	 * It then calls another method which is used to display how much the transaction is. 
	 */
	public void processSale() {
		// Calls process sale method using the data gathered from the process method.
		transaction = newAccount.processSale(winePrice, wineQuantity);
		// Method for updating the GUI.
		setTransaction(transaction);
	}
	/**
	 * Method called if the process return button is pressed. 
	 * Calls a method in Customer Account class which handles the actual mathematics. 
	 * Then calls a method to update the GUI. 
	 */
	public void processReturn() {
		transaction = newAccount.processReturn(winePrice, wineQuantity);
		setTransaction(transaction);		
	}
	/**
	 * Method called after the processing in the event handling method.
	 * It updates the balance on the GUI.
	 * If the balance is above zero then the balance is published as is.
	 * If the balance is below zero it is not displayed as a negative but with a CR next to it.
	 * The formatting is also provided so that there are always two decimal places and it can accept
	 * numbers up to ten numbers long before the decimal place. 
	 * 
	 * @param bal
	 */
	public void setBalance(double bal) {
		if (bal >= 0) {
			balanceText.setText(String.format("%10.2f", bal));
		}
		else if (bal < 0) {
			// Call this math method to make the negative number positive again.
			bal = Math.abs(bal);
			balanceText.setText(String.format("%10.2f CR", bal));
		}
	}
	/**
	 * Method used to update the GUI with the amount of the transaction. 
	 * 
	 * @param trans
	 */
	public void setTransaction(double trans) {
		transactionText.setText(String.format("%10.2f", trans));
	}
	/**
	 * Method called after all other methods in the event handling to clear all of the text fields
	 * so that a new transaction can be entered. 
	 */
	public void clearFields() {
		nameText.setText("");
		priceText.setText("");
		quantityText.setText("");
	}
}