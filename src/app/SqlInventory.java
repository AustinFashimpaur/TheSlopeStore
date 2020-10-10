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
				+ "(100002, 2, 12),"
				+ "(100003, 2, 17),"
				+ "(100004, 1, 6),"
				+ "(100005, 1, 25),"
				+ "(100006, 3, 10),"
				+ "(100007, 3, 10),"
				+ "(100008, 4, 10),"
				+ "(100009, 5, 10),"
				+ "(100010, 3, 10),"
				+ "(100011, 3, 10),"
				+ "(100012, 4, 10),"
				+ "(100013, 5, 10),"
				+ "(100014, 3, 10),"
				+ "(100015, 3, 10),"
				+ "(100016, 4, 10),"
				+ "(100017, 5, 10),"
				+ "(100018, 5, 10)";
	}
	
	public static String getItemAndStore(int storeNumber) {
		return "SELECT ProductName, BrandName, Size, Price, Quantity FROM Items AS c "
				   + "INNER JOIN "
				   + "Inventory AS o "
				   + "ON c.ID = o.ItemID "
				   + "LEFT JOIN "
				   + "Stores AS s "
				   + "ON o.StoreID = s.ID "
				   + "WHERE o.StoreID=" + storeNumber;
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

