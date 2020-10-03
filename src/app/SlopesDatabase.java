package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import org.apache.derby.iapi.sql.PreparedStatement;

/**
 * Houses the database functions for SlopeStoreDatabase
 * @author Trevor Colton & Austin Fashimpaur
 *
 */
public class SlopesDatabase {
	//db variables
	private static final String databaseUrl = "jdbc:derby:SlopeStoreDatabase;create=true";
	
	/**
	 * Retrieves all items from the Item SQL table and adds them to a TableModel to be displayed on a JTable
	 * @return Updated Table Model
	 */
	public static DefaultTableModel getAllItems() {
		DefaultTableModel model = new DefaultTableModel(new String[] {"Name", "Brand", "Size", "Price", "Item ID"}, 0);
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			//fill model with items from Item table
			ResultSet rs = statement.executeQuery(SqlItems.getAll());
			
			while (rs.next()) {
				String name = rs.getString("ProductName");
				String brand = rs.getString("BrandName");
				String price = "$" + rs.getString("Price");
				String size = rs.getString("Size");
				String id = rs.getString("ID");
				model.addRow(new Object[] {name, brand, size, price, id});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * Retrieves each stores city and state from the SQL Stores table and adds them to an array.
	 * @return Array of stores
	 */
	public static String[] getAllStores() {
		String[] stores = new String[6];
		int index = 1;
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			stores[0] = "All";
			//fill array with stores from store table
			ResultSet rs = statement.executeQuery(SqlStores.getAll());
			while (rs.next()) {
				stores[index] = rs.getString("City") + ", " + rs.getString("State");
				index++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stores;
	}
	
	/**
	 * Inserts a new row into the Items Table and updates Inventory Table with new ID
	 * @param productName
	 * @param brandName
	 * @param price
	 * @param size
	 */
	public static void addItemRow(String productName, String brandName, String price, String size, String qty, String storeID) {
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			String insertItem = "INSERT INTO Items (ProductName, BrandName, Price, Size)" 
					+ "VALUES ('" + productName + "', '" + brandName + "', " + price + ", '" + size + "')";
			
			//Adds row and retrieves newly generated ID
			java.sql.PreparedStatement ps = connection.prepareStatement(insertItem, Statement.RETURN_GENERATED_KEYS);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			int temp = 0;
			if(rs.next()) {
				temp = rs.getInt(1);
			}
			String generatedID = Integer.toString(temp);
			//Adds row to Inventory table with new Product ID info 
			statement.executeUpdate("INSERT INTO Inventory " + "VALUES (" + storeID + ", "+ generatedID +", "+ qty +")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes the specified row from the Items table
	 * @param id
	 */
	public static void removeItemRow(String id) {
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM Items WHERE ID = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes the specified row from the Items table
	 * @param id
	 */
	public static void removeInventoryItem(String id) {
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM Inventory WHERE ItemID = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Drops all tables and reinitializes them with data.
	 * Used for debugging. If error is thrown tables may not exist.
	 */
	public static void databaseReset() {
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			statement.execute(SqlStores.dropTable());
			statement.execute(SqlItems.dropTable());
			statement.execute(SqlInventory.dropTable());
			
			statement.execute(SqlStores.createTable());
			statement.execute(SqlItems.createTable());
			statement.execute(SqlInventory.createTable());
			
			statement.execute(SqlInventory.insertData());
			//statement.execute(SqlInventory.getItemAndStore());
			
			statement.execute(SqlStores.insertData());
			statement.execute(SqlItems.insertData());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints out all tables of provided queries
	 * @param queries
	 */
	public static void printAllQueryResults(String... queries) {
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

			System.out.print("");
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
