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
				+ "VALUES ('Custom Camber Snowboard', 'Burton', 599.99, 'L'),"
				+ "('Throwback Snowboard', 'Burton', 149.99, 'M')," 
				+ "('West Bound Snowboard', 'Never Summer', 599.99, 'L')," 
				+ "('Snowshot Snow Coat', 'Patagonia', 268.99, 'M')," 
				+ "('Cloud Bank Goretex Snow Coat', 'Mountain Hardware', 280.99, 'M')," 
				+ "('Snow Pants', 'Burton', 89.99, 'M')," 
				+ "('SLX Snowboard Boots', 'Burton', 609.99, 'L'),"
				+ "('Imperial Snowboard Boots', 'Burton', 339.99, 'M'),"
				+ "('Sage Snowboard Boots', 'Ride', 609.99, 'L'),"
				+ "('X Est Snowboard Bindings', 'Burton', 499.99, 'L'),"
				+ "('Escapade Snowboard Bindings', 'Burton', 329.99, 'S'),"
				+ "('P100 Adult Skis With Bindings', 'Rossignol', 399.99, 'M'),"
				+ "('Deacon 76 Pro Skis', 'Voelkl', 849.99, 'M')," 
				+ "('Enforcer 100 Skis With Bindings', 'Nordica', 629.99, 'S')," 
				+ "('Speed Ski Boots', 'Rossignol', 179.99, 'M'),"
				+ "('S Pro 120 Ski Boots', 'Salomon', 454.99, 'M'),"
				+ "('Speed Machine 130 Ski Boots', 'Nordica', 489.99, 'M'),"
				+ "('Lab Shift Ski Bindings', 'Salomon', 469.99, 'M')";
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

