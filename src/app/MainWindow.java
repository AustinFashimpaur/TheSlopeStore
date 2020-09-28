package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * 
 * @author Trevor Colton and Austin Fashimpaur
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	//graphics variables
	private JPanel contentPane;
	
	//db variables
	private static final String databaseUrl = "jdbc:derby:SlopeStoreDatabase;create=true";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("hello");
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			//statement.execute(SqlStores.dropTable());
			//statement.execute(SqlItems.dropTable());
			//statement.execute(SqlInventory.dropTable());
			
			statement.execute(SqlStores.createTable());
			statement.execute(SqlItems.createTable());
			//statement.execute(SqlInventory.createTable());
			
			statement.execute(SqlStores.insertData());
			statement.execute(SqlItems.insertData());
			//ResultSet resultSet = statement.executeQuery(SqlStudent.getAll());
			
			printAllQueryResults(SqlStores.getAll(), SqlItems.getAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//initializeJFrame();
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
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
