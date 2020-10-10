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
	
	/**
	 * Inserts Inventory Table data
	 * @return
	 */
	public static String insertData() {
		return "INSERT INTO Inventory (ItemID, StoreID, Quantity)"
				+ "VALUES (100000, 4, 10),"
				+ "(100001, 1, 113),"
				+ "(100002, 2, 12),"
				+ "(100003, 2, 17),"
				+ "(100004, 1, 6),"
				+ "(100005, 1, 25),"
				+ "(100006, 3, 7),"
				+ "(100007, 3, 49),"
				+ "(100008, 4, 20),"
				+ "(100009, 5, 18),"
				+ "(100010, 3, 3),"
				+ "(100011, 3, 1),"
				+ "(100012, 4, 56),"
				+ "(100013, 5, 14),"
				+ "(100014, 3, 25),"
				+ "(100015, 3, 23),"
				+ "(100016, 4, 8),"
				+ "(100017, 5, 89),"
				+ "(100018, 2, 2),"
				+ "(100019, 2, 46),"
				+ "(100020, 4, 11),"
				+ "(100021, 1, 9),"
				+ "(100022, 1, 14),"
				+ "(100023, 3, 74),"
				+ "(100024, 5, 33)";
	}
	
	/**
	 * Joins Item and Inventory tables to get the corresponding data
	 * @param storeNumber
	 * @return
	 */
	public static String getItemAndStore(int storeNumber) {
		return "SELECT ProductName, BrandName, Size, Price, Quantity, ItemID FROM Items AS c "
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

