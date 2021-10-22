import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

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
	// Variables for use with Customer Account object
	private final CustomerAccount customerAccount;
	private final WineService wineService;
	/**
	 * Constructor.
	 * Outlines size, location, close operation etc. for GUI.
	 * Also calls methods to instantiate and layout.
	 */
	public GUI(CustomerAccount account) {
		this.wineService = new WineServiceImpl();
		// Set up of GUI
		this.customerAccount = account;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 200);
		setLocation(100, 100);
		setTitle("Wine Merchants: " + this.customerAccount.getName());
		componentInstantiate();
		guiLayout();
		setBalance(this.customerAccount.getBalance());
	}
	/**
	 * This method handles the instantiation of all the components
	 */
	public void componentInstantiate() {
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
	 */
	public void actionPerformed(ActionEvent e) {
		// If / else to determine which button was pressed.
		if (e.getSource() == saleButton) {
			processSale();
		}
		else if (e.getSource() == returnButton) {
			processReturn();
		}
		// Updates the balance on the GUI.
		setBalance(customerAccount.getBalance());
		// Clears the text fields so new data can be entered. Last thing done when a button is pressed.
		clearFields();
	}
	/**
	 * This method is called if the process sale button is pressed.
	 */
	public void processSale() {
		Wine wine = getWine();
		int quantity = getWineQuantity();
		if (wine != null) {
			BigDecimal transactionAmount = wineService.processSale(wine.getPrice(), quantity);
			customerAccount.setBalance(customerAccount.getBalance().subtract(transactionAmount));
			setTransaction(transactionAmount);
		}
	}
	/**
	 * Method called if the process return button is pressed.
	 */
	public void processReturn() {
		Wine wine = getWine();
		int quantity = getWineQuantity();
		if (wine != null) {
			BigDecimal transactionAmount = wineService.processReturn(wine.getPrice(), quantity);
			customerAccount.setBalance(customerAccount.getBalance().add(transactionAmount));
			setTransaction(transactionAmount);
		}
	}

	private Wine getWine() {
		// Retrieves the name of the wine and sets the label to that name.
		String wineName = nameText.getText();
		//wineName = nameText.getText();
		purchaseText.setText(wineName);
		// Try catch block to make sure that data is valid
		BigDecimal winePrice;
		try {
			winePrice = new BigDecimal(priceText.getText());
		}
		catch(NumberFormatException e) {
			// J Option Pane to show error message
			JOptionPane.showMessageDialog(null, "Invalid data entered for wine price", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		// Creates a wine object with the data that has been retrieved
		return new Wine(wineName, winePrice);
	}

	private int getWineQuantity() {
		// Try catch block to make sure data is valid
		int wineQuantity = 0;
		try {
			wineQuantity = Integer.parseInt(quantityText.getText());
		}
		catch(NumberFormatException e) {
			// J Option Pane to show error message
			JOptionPane.showMessageDialog(null, "Invalid data entered for wine quantity", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return wineQuantity;
	}

	/**
	 * Method called after the processing in the event handling method.
	 * It updates the balance on the GUI.
	 * If the balance is above zero then the balance is published as is.
	 * If the balance is below zero it is not displayed as a negative but with a CR next to it.
	 * The formatting is also provided so that there are always two decimal places and it can accept
	 * numbers up to ten numbers long before the decimal place.
	 */
	public void setBalance(BigDecimal bal) {
		if (bal.compareTo(BigDecimal.ZERO) > 0) {
			balanceText.setText(String.format("%10.2f", bal));
		}
		else if (bal.compareTo(BigDecimal.ZERO) < 0) {
			balanceText.setText(String.format("%10.2f CR", bal.abs()));
		}
	}
	/**
	 * Method used to update the GUI with the amount of the transaction
	 */
	public void setTransaction(BigDecimal trans) {
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
