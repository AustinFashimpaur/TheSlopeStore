package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
/**
 * 
 * @author Trevor Colton and Austin Fashimpaur
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame{

	//graphics variables
	private JPanel contentPane;
	
	//db variables
	private static final String databaseUrl = "jdbc:derby:SlopeStoreDatabase;create=true";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//databaseReset();
		
		initializeJFrame();
	}

	/**
	 * Drops all tables and reinitializes them with data.
	 * Used for debugging. If error is thrown tables may not exist.
	 */
	private static void databaseReset() {
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			//statement.execute(SqlStores.dropTable());
			//statement.execute(SqlItems.dropTable());
			//statement.execute(SqlInventory.dropTable());
			
			statement.execute(SqlStores.createTable());
			statement.execute(SqlItems.createTable());
			statement.execute(SqlInventory.createTable());
			
			statement.execute(SqlStores.insertData());
			statement.execute(SqlItems.insertData());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void initializeJFrame() {
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
		
		//store logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(122, 5, 200, 107);
		Image SlopeStoreLogo = new ImageIcon(this.getClass().getResource("slope_200x200.png"))
				.getImage();
		welcomePanel.setLayout(null);
		lblLogo.setIcon(new ImageIcon(SlopeStoreLogo));
		welcomePanel.add(lblLogo);
		
		// Data to be displayed in the JTable 
        String[][] data = { 
            { "SAMPLE_NAME", "SAMPLE_BRAND", "S", "10.99" },
            { "SAMPLE_NAME", "SAMPLE_BRAND", "S", "10.99" },
            { "SAMPLE_NAME", "SAMPLE_BRAND", "S", "10.99" }
        }; 
  
        // Column Names 
        String[] columnNames = { "Name", "Brand", "Size", "Price" }; 
  
        // Initializing the JTable 
        JTable j = new JTable(data, columnNames); 
        j.setBounds(30, 40, 200, 300); 
  
        // adding it to JScrollPane 
        JScrollPane sp = new JScrollPane(j); 
        sp.setLocation(56, 190);
        sp.setSize(326, 117);
        welcomePanel.add(sp);
        
        // array of string contating cities 
        String s1[] = { "a", "b", "c", "d", "e" }; 
  
        //drop down
        // create checkbox 
        JComboBox c1 = new JComboBox(s1); 
        c1.setLocation(203, 152);
        c1.setSize(78, 28);
  
        // create labels 
        JLabel storeLabel = new JLabel("Select Your Store: "); 
        storeLabel.setLocation(77, 146);
        storeLabel.setSize(116, 34);

        welcomePanel.add(storeLabel); 
  
        // add combobox to panel 
        welcomePanel.add(c1); 

	}
	
	private static void printAllQueryResults(String... queries) {
		try (Connection connection = DriverManager.getConnection(databaseUrl);) {
			Statement statement = connection.createStatement();
			for (String query : queries) {
				ResultSet resultSet = statement.executeQuery(query);
				printResultSet(resultSet);
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void printResultSet(ResultSet resultSet) {
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();

			printHeader(metaData);
			printDataRecords(resultSet, metaData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void printDataRecords(ResultSet resultSet, ResultSetMetaData metaData) {

		try {
			int columnCount = metaData.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= columnCount; i++) {
					String formatString = "%-" + metaData.getColumnLabel(i).length() + "s  ";
					System.out.printf(formatString, resultSet.getObject(i));
					// System.out.print(resultSet.getObject(i) + " ");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("A problem occurred printing the data records");
			e.printStackTrace();
		}

	}

	private static void printHeader(ResultSetMetaData metaData) {
		try {
			int columnCount = metaData.getColumnCount();

			System.out.print("  ");
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(metaData.getColumnLabel(i) + "  ");
			}
			System.out.println();
		} catch (SQLException e) {
			System.out.println("A problem occurred printing the Header");
			e.printStackTrace();
		}
	}
}
