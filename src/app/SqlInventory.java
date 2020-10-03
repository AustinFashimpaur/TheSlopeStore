package app;

public class SqlInventory {
	/**
	 * Creates an Inventory Table With the columns StoreID, ItemID,
	 * and Quantity
	 * @return sql command to create the Student table.
	 */
	public static String createTable() {
		return "CREATE TABLE Inventory (" 
				+ "StoreID int," 
				+ "ItemID int," 
				+ "Quantity int)";
	}
	
	public static String insertData() {
		return "INSERT INTO Inventory (ItemID, StoreID, Quantity)"
				+ "VALUES (100001, 1, 10),"
				+ "(100002, 2, 10),"
				+ "(100003, 2, 10),"
				+ "(100004, 1, 10),"
				+ "(100005, 1, 10),"
				+ "(100006, 3, 10),"
				+ "(100007, 3, 10),"
				+ "(100008, 4, 10),"
				+ "(100009, 5, 10)";
	}
	
	public static String getItemAndStore() {
		return "SELECT Items.ProductName, Items.BrandName, Items.Price, Items.Size, Stores.City, Stores.State"
				+ "FROM ((Items "
				+ "INNER JOIN Inventory ON Items.ID = Inventory.StoreID)"
				+ "INNER JOIN Stores ON Inventory.ItemID = Stores.ID) ";
	}
	
	/**
	 * Gets info from existing Inventory table currently in the database
	 * @return sql command to retrieve info from current Inventory table
	 */
	public static String getAll() {
		return "SELECT * FROM Inventory";
	}
	
	/**
	 * Drops the current Inventory Table Currently in the database.
	 * @return sql Command to drop the Inventory Table.
	 */
	public static String dropTable() {
		return "DROP TABLE Inventory";
	}
}

