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
				if(!(productName.getText().trim().isEmpty() && productName.getText().trim().isEmpty() 
						&& productName.getText().trim().isEmpty() && productName.getText().trim().isEmpty())) {
					String a = productName.getText();
					String b = brandName.getText();
					String c = price.getText().trim();
					String d = size.getText();
					SlopesDatabase.addItemRow(a, b, c, d);
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
		
		JLabel lblNewLabel = new JLabel("Brand Name:");
		lblNewLabel.setBounds(79, 109, 98, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Add New Product");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(110, 11, 231, 36);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblProduct = new JLabel("Product Name:");
		lblProduct.setBounds(79, 78, 98, 14);
		getContentPane().add(lblProduct);
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
	}
}
