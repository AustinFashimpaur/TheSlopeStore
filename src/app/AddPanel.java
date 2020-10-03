package app;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Adds a new row to the SQL table Items based on user input
 * @author Trevor Colton & Austin Fashimpaur
 *
 */
@SuppressWarnings("serial")
public class AddPanel extends JDialog {
	private JTextField productName;
	private JTextField brandName;
	private JTextField price;
	private JTextField size;
	private JTextField quantity;
	private JTextField storeNumber;
	
	//private static final String databaseUrl = "jdbc:derby:SlopeStoreDatabase;create=true";

	/**
	 * Create the panel.
	 */
	public AddPanel(Frame owner) {
		super(owner);
		setBounds(100, 100, 500, 300);
		setVisible(true);
		setModal(true);
		getContentPane().setLayout(null);
		
		createTextBoxes();
		
		createLabels();
		
		JButton btnCancel = createCancelBtn();
		getContentPane().add(btnCancel);
		
		JButton btnAdd = createAddBtn();
		getContentPane().add(btnAdd);
	}

	private JButton createCancelBtn() {
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(66, 230, 89, 23);
		return btnCancel;
	}

	private JButton createAddBtn() {
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!productName.getText().trim().isEmpty() && !brandName.getText().trim().isEmpty() 
						&& !price.getText().trim().isEmpty() && !size.getText().trim().isEmpty()
						&& !quantity.getText().trim().isEmpty() && !storeNumber.getText().trim().isEmpty()) {
					
					String a = productName.getText();
					String b = brandName.getText();
					String c = price.getText().trim();
					String d = size.getText().trim();
					String qty = quantity.getText().trim();
					String sID = storeNumber.getText().trim();
					SlopesDatabase.addItemRow(a, b, c, d, qty, sID);
					dispose();
				}
			}
		});
		btnAdd.setBounds(302, 230, 89, 23);
		return btnAdd;
	}

	private void createLabels() {
		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setBounds(79, 171, 98, 14);
		getContentPane().add(lblPrice);
		
		JLabel lblSize = new JLabel("Size: ");
		lblSize.setBounds(79, 140, 98, 14);
		getContentPane().add(lblSize);
		
		JLabel lblBrandName = new JLabel("Brand Name:");
		lblBrandName.setBounds(79, 109, 98, 14);
		getContentPane().add(lblBrandName);
		
		JLabel lblTitle = new JLabel("Add New Product");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTitle.setBounds(110, 11, 231, 36);
		getContentPane().add(lblTitle);
		
		JLabel lblProduct = new JLabel("Product Name:");
		lblProduct.setBounds(79, 78, 98, 14);
		getContentPane().add(lblProduct);
		
		JLabel lblStoreNum = new JLabel("Store Number: ");
		lblStoreNum.setBounds(240, 201, 91, 14);
		getContentPane().add(lblStoreNum);
		
		JLabel lblQty = new JLabel("Quantity: ");
		lblQty.setBounds(79, 201, 97, 14);
		getContentPane().add(lblQty);
	}

	private void createTextBoxes() {
		productName = new JTextField();
		productName.setBounds(187, 75, 204, 20);
		getContentPane().add(productName);
		productName.setColumns(10);
		
		brandName = new JTextField();
		brandName.setBounds(187, 106, 204, 20);
		getContentPane().add(brandName);
		brandName.setColumns(10);
		
		price = new JTextField();
		price.setBounds(187, 168, 86, 20);
		getContentPane().add(price);
		price.setColumns(10);
		
		size = new JTextField();
		size.setBounds(187, 137, 86, 20);
		getContentPane().add(size);
		size.setColumns(10);
		
		quantity = new JTextField();
		quantity.setBounds(187, 198, 43, 20);
		getContentPane().add(quantity);
		quantity.setColumns(10);
		
		storeNumber = new JTextField();
		storeNumber.setBounds(345, 198, 43, 20);
		getContentPane().add(storeNumber);
		storeNumber.setColumns(10);
	}
}
