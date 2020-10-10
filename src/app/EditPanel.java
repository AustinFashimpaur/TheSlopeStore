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
 * Edits existing information in the Items and Inventory sql tables.
 * @author Trevor Colton & Austin Fashimpaur
 *
 */
@SuppressWarnings("serial")
public class EditPanel extends JDialog {
	private JTextField productName;
	private JTextField brandName;
	private JTextField price;
	private JTextField size;
	private JTextField quantity;
	private JTextField storeNumber;
	private String id;
	private String name;
	private String brand;
	private String size1;
	private String price1;
	private String quantity1;
	private String store;
	
	//private static final String databaseUrl = "jdbc:derby:SlopeStoreDatabase;create=true";

	/**
	 * Create the panel.
	 * @param price 
	 * @param size 
	 * @param brand 
	 * @param name 
	 */
	public EditPanel(Frame owner, String id, String name, String brand, String size1, String price1, String quantity1, String store) {
		super(owner);
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.size1 = size1;
		this.price1 = price1;
		this.quantity1 = quantity1;
		this.store = store;
		
		setBounds(100, 100, 500, 300);
		setVisible(true);
		setModal(true);
		getContentPane().setLayout(null);
		
		createTextBoxes();
		
		createLabels();
		
		JButton btnCancel = createCancelBtn();
		getContentPane().add(btnCancel);
		
		JButton btnAdd = createSaveBtn();
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

	private JButton createSaveBtn() {
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!productName.getText().trim().isEmpty() && !brandName.getText().trim().isEmpty() 
						&& !price.getText().trim().isEmpty() && !size.getText().trim().isEmpty()
						&& !quantity.getText().trim().isEmpty() && !storeNumber.getText().trim().isEmpty()) {
					int storeInt = Integer.parseInt(storeNumber.getText().trim());
					if (storeInt <= 5 && storeInt >= 1) {
						String name = productName.getText();
						String brand = brandName.getText();
						String p = price.getText().trim();
						String s = size.getText().trim();
						String qty = quantity.getText().trim();
						String sID = storeNumber.getText().trim();
						SlopesDatabase.updateItem(id, name, brand, p, s);
						SlopesDatabase.updateItemInventory(id, qty, sID);
						dispose();
					}
				}
			}
		});
		btnSave.setBounds(302, 230, 89, 23);
		return btnSave;
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
		
		JLabel lblTitle = new JLabel("Edit Existing Product");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTitle.setBounds(141, 6, 231, 36);
		getContentPane().add(lblTitle);
		
		JLabel lblProduct = new JLabel("Product Name:");
		lblProduct.setBounds(79, 78, 98, 14);
		getContentPane().add(lblProduct);
		
		JLabel lblStoreNum = new JLabel("Store Number: ");
		lblStoreNum.setBounds(240, 201, 105, 14);
		getContentPane().add(lblStoreNum);
		
		JLabel lblQty = new JLabel("Quantity: ");
		lblQty.setBounds(79, 201, 97, 14);
		getContentPane().add(lblQty);
	}

	private void createTextBoxes() {
		productName = new JTextField();
		productName.setText(name);
		productName.setBounds(187, 75, 204, 20);
		getContentPane().add(productName);
		productName.setColumns(10);
		
		brandName = new JTextField();
		brandName.setText(brand);
		brandName.setBounds(187, 106, 204, 20);
		getContentPane().add(brandName);
		brandName.setColumns(10);
		
		price = new JTextField();
		price.setText(price1);
		price.setBounds(187, 168, 86, 20);
		getContentPane().add(price);
		price.setColumns(10);
		
		size = new JTextField();
		size.setText(size1);
		size.setBounds(187, 137, 86, 20);
		getContentPane().add(size);
		size.setColumns(10);
		
		quantity = new JTextField();
		quantity.setBounds(187, 198, 43, 20);
		quantity.setText(quantity1);
		getContentPane().add(quantity);
		quantity.setColumns(10);
		
		storeNumber = new JTextField();
		storeNumber.setBounds(357, 198, 43, 20);
		storeNumber.setText(store);
		getContentPane().add(storeNumber);
		storeNumber.setColumns(10);
	}
}
