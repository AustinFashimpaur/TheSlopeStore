package app;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

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
				String price = rs.getString("Price");
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
	 * Sorts Item table by specified column and re-populates the JTable
	 * @param column
	 * @return
	 */
	public static DefaultTableModel sortByColumn(String column) {
		DefaultTableModel model = new DefaultTableModel(new String[] {"Name", "Brand", "Size", "Price", "Item ID"}, 0);
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			
			String sql = "SELECT * FROM Items "
					+ "ORDER BY " + column + " ASC";
			//fill model with items from Item table
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String name = rs.getString("ProductName");
				String brand = rs.getString("BrandName");
				String price = rs.getString("Price");
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
	 * Updates a single item in the table Items with the adjusted values
	 * @param id
	 * @param name
	 * @param brand
	 * @param price
	 * @param size
	 */
	public static void updateItem(String id, String name, String brand, String price, String size) {
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
				
			String sqlUpdate = "UPDATE Items "
								+ "SET ProductName = '" + name + "', BrandName = '" + brand + "', Price = " + price + ", Size = '" + size + "' "
								+ "WHERE ID = " + id;
			statement.executeUpdate(sqlUpdate);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates Inventory with item's storeID and qty
	 * @param id
	 * @param qty
	 * @param store
	 */
	public static void updateItemInventory(String id, String qty, String store) {
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
				
			String sqlUpdate = "UPDATE Inventory "
								+ "SET StoreID = " + store + ", Quantity = " + qty + " "
								+ "WHERE ItemID = " + id;
			statement.executeUpdate(sqlUpdate);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	 * Retrieves the quantity and store values of a specific item 
	 * from Table Inventory and returns them in an array
	 * @param id Item ID
	 * @return Array with qty and store number
	 */
	public static String[] getItemQtyAndStore(String id) {
		String[] qtyStore = new String [2];
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			String sql = "SELECT  * FROM Inventory WHERE ItemID = " + id;
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				qtyStore[0] = rs.getString("Quantity");
				qtyStore[1] = rs.getString("StoreID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qtyStore;
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
	 * Updates table by replacing item ID with quantity column and filters by supplied store number
	 * @param storeNumber, auto incremented and can be found in SqlStores
	 * @return filtered table model
	 */
	public static DefaultTableModel filterByStore(int storeNumber) {
		DefaultTableModel model = new DefaultTableModel(new String[] {"Name", "Brand", "Size", "Price","Item ID", "Quantity"}, 0);
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			//fill table with join statement from SqlInventory, filtered by store number supplied by MainWindow
			ResultSet rs = statement.executeQuery(SqlInventory.getItemAndStore(storeNumber));
			
			while (rs.next()) {
				String name = rs.getString("ProductName");
				String brand = rs.getString("BrandName");
				String price = rs.getString("Price");
				String size = rs.getString("Size");
				String id = rs.getString("ItemID");
				String quantity = rs.getString("Quantity");
				model.addRow(new Object[] {name, brand, size, price, id, quantity});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
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
	
	/*
	 * Used to initialize the database tables with data if
	 * they do not exist.
	 */
	public static void databaseInit() {
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
			if(!isTableExist("Stores")) {
			statement.execute(SqlStores.createTable());
			statement.execute(SqlStores.insertData());
			}
			
			if(!isTableExist("Items")) {
			statement.execute(SqlItems.createTable());
			statement.execute(SqlItems.insertData());
			}
			
			if(!isTableExist("Inventory")) {
			statement.execute(SqlInventory.createTable());
			statement.execute(SqlInventory.insertData());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Checks to see if supplied table exists and returns boolean
	 * @param sTablename string of table name
	 * @return if table exists or not
	 * @throws SQLException
	 */
	public static boolean isTableExist(String sTablename) throws SQLException{
		try(Connection connection = DriverManager.getConnection(databaseUrl);
				Statement statement = connection.createStatement();) {
	        DatabaseMetaData dbmd = connection.getMetaData();
	        ResultSet rs = dbmd.getTables(null, null, sTablename.toUpperCase(),null);
	        
	        if(rs.next())
	        {
	        	System.out.println(sTablename + " exists, using pre-existing table.");
	            return true;
	        }
	        else
	        {
	        	System.out.println(sTablename + " does not exist, creating...");
	            return false;
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
