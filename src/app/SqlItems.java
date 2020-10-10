package app;

/**
 * Provides SQL statements to create, drop, fill, and query Items table
 * @author Trevor Colton and Austin Fashimpaur
 *
 */
public class SqlItems {

	/**
	 * Creates an Items Table With the columns Id, ProductName, BrandName,
	 * and Price.
	 * @return sql command to create the Student table.
	 */
	public static String createTable () {
		return "CREATE TABLE Items (" 
			 + "ID int not null primary key " 
             + "  GENERATED ALWAYS AS IDENTITY" 
             + "  (START WITH 100000, INCREMENT BY 1),"  
			 + "ProductName varchar(255),"  
			 + "BrandName varchar(255),"
			 + "Price double,"
			 + "Size varchar(255))";
	}
	
	/**
	 * Inserts rows into the existing Items Table in the database
	 * @return sql command to insert the Items Table
	 */
	public static String insertData() {
		return "INSERT INTO Items (ProductName, BrandName, Price, Size)" 
				+ "VALUES ('Snowboard', 'Burton', 299.99, 'L'),"
				+ "('Snow Coat', 'Burton', 129.99, 'M')," 
				+ "('Snow Pants', 'Burton', 89.99, 'M')," 
				+ "('Snowboard Shoes', 'Burton', 169.99, 'L'),"
				+ "('Snowboard Bindings', 'Burton', 79.99, 'L'),"
				+ "('Skis', 'Burton', 349.95, 'M'),"
				+ "('Snow Coat', 'Burton', 119.99, 'S')," 
				+ "('Snow Pants', 'Burton', 79.99, 'S')," 
				+ "('Ski Shoes', 'Burton', 159.99, 'M'),"
				+ "('Ski Bindings', 'Burton', 89.99, 'M')";
	}
	
	/**
	 * Gets info from existing Items table in the database
	 * @return sql command to retrieve info from current Items table
	 */
	public static String getAll() {
		return "SELECT * FROM Items";
	}
	
	/**
	 * Drops the current Items Table Currently in the database.
	 * @return sql Command to drop the Items Table.
	 */
	public static String dropTable() {
		return "DROP TABLE Items";
	}
	
}

