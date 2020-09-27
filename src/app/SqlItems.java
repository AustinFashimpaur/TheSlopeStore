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
		return "INSERT INTO Stores (ProductName, BrandName, Price, Size)" 
				+ "VALUES ('The Slope Store', 'Sandy', 'UT'),"
				+ "('The Slope Store', 'Park City', 'UT')," 
				+ "('The Slope Store', 'Orem', 'UT')," 
				+ "('The Slope Store', 'Mammoth Lakes', 'CA'),"
				+ "('The Slope Store', 'Denver', 'CO')";
	}

}
