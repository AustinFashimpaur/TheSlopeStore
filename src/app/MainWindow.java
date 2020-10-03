package app;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * GUI for the database application
 * @author Trevor Colton and Austin Fashimpaur
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	//graphics variables
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SlopesDatabase.databaseReset();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 445, 462);
		contentPane.add(layeredPane);
		
		//welcome panel, consider this the main JPanel for the time being
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBounds(0, 0, 435, 451);
		layeredPane.add(welcomePanel);
		
		JLabel lblLogo = createStoreLogo(welcomePanel);
		welcomePanel.add(lblLogo);
  
        // Initializing the JTable 
        JTable j = createJTable();
  
        // adding it to JScrollPane 
        JScrollPane sp = new JScrollPane(j); 
        sp.setLocation(10, 190);
        sp.setSize(425, 117);
        welcomePanel.add(sp);
        
        JComboBox c1 = createComboBox();
        welcomePanel.add(c1);
  
        // create labels 
        JLabel storeLabel = new JLabel("Select Your Store: "); 
        storeLabel.setLocation(77, 146);
        storeLabel.setSize(116, 34);

        welcomePanel.add(storeLabel); 
        
        JButton btnUpdate = createUpdateBtn(j);
        welcomePanel.add(btnUpdate);
        
        JButton btnAddItem = createAddBtn(j);
        welcomePanel.add(btnAddItem);
        
        JButton btnRemove = createRemoveBtn(j);
        welcomePanel.add(btnRemove);

	}

	private JLabel createStoreLogo(JPanel welcomePanel) {
		//store logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(122, 5, 200, 107);
		Image SlopeStoreLogo = new ImageIcon(this.getClass().getResource("slope_200x200.png"))
				.getImage();
		welcomePanel.setLayout(null);
		lblLogo.setIcon(new ImageIcon(SlopeStoreLogo));
		return lblLogo;
	}

	private JComboBox createComboBox() {
		// array of string containing the store's city and state.
        String stores[] = SlopesDatabase.getAllStores(); 
  
        //drop down
        // create checkbox 
        JComboBox c1 = new JComboBox(stores); 
        c1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		JComboBox jcb = (JComboBox) arg0.getSource();
        		JOptionPane.showMessageDialog(null, jcb.getSelectedItem().toString());
        	}
        });
        c1.setLocation(203, 152);
        c1.setSize(161, 28);
		return c1;
	}

	private JTable createJTable() {
		JTable j = new JTable() {
        	@Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
              Component component = super.prepareRenderer(renderer, row, column);
              int rendererWidth = component.getPreferredSize().width;
              TableColumn tableColumn = getColumnModel().getColumn(column);
              tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
              return component;
            }
          };
        j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        j.setBounds(30, 40, 200, 300); 
        j.setModel(SlopesDatabase.getAllItems());
		return j;
	}

	private JButton createUpdateBtn(JTable j) {
		JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		j.setModel(SlopesDatabase.getAllItems());
        		SlopesDatabase.printAllQueryResults(SqlInventory.getAll());
        	}
        });
        btnUpdate.setBounds(171, 417, 89, 23);
		return btnUpdate;
	}

	private JButton createAddBtn(JTable j) {
		JButton btnAddItem = new JButton("Add Item");
        btnAddItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		new AddPanel(MainWindow.this);
        	}
        });
        btnAddItem.setBounds(171, 364, 89, 23);
		return btnAddItem;
	}

	private JButton createRemoveBtn(JTable j) {
		JButton btnRemove = new JButton("Remove Item");
        btnRemove.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(!j.getSelectionModel().isSelectionEmpty()) {
        			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you wand to delete this item? It will be removed from the database.", "Warning", JOptionPane.YES_NO_OPTION);
        			if (reply == JOptionPane.YES_OPTION) {
        				int row = j.getSelectedRow();
        				int column = 4;
        				String value = j.getModel().getValueAt(row, column).toString();
        				SlopesDatabase.removeItemRow(value);
        				SlopesDatabase.removeInventoryItem(value);
        				j.setModel(SlopesDatabase.getAllItems());
        			}
        		}else {
        			msgboxError("You must have an item selected from the table.");
        			
        		}
        	}
        });
        btnRemove.setBounds(36, 364, 111, 23);
		return btnRemove;
	}
	
	private void msgboxError(String s){
		JOptionPane optionPane = new JOptionPane(s, JOptionPane.ERROR_MESSAGE);    
		JDialog dialog = optionPane.createDialog("Failure");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
		}
}
