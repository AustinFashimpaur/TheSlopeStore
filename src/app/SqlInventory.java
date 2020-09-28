package app;

public class SqlInventory {
	/**
	 * Creates an Inventory Table With the columns StoreID, ItemID,
	 * and Quantity
	 * @return sql command to create the Student table.
	 */
	public static String createTable() {
		return "CREATE TABLE Iventory (" 
				+ "StoreID int," 
				+ "ItemID int," 
				+ "Quantity int)";
	}
	
	/**
	 * Gets info from existing Inventory table currently in the database
	 * @return sql command to retrieve info from current Inventory table
	 */
	public static String getAll() {
		return "SELECT * FROM Iventory";
	}
	
	/**
	 * Drops the current Inventory Table Currently in the database.
	 * @return sql Command to drop the Inventory Table.
	 */
	public static String dropTable() {
		return "DROP TABLE Inventory";
	}
}

