package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sqlDerby.SqlStudent;

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
			
			//ResultSet resultSet = statement.executeQuery(SqlStudent.getAll());
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

}
