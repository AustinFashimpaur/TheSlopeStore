package app;

/**
 * Provides SQL statements to create, drop, fill, and query Stores table
 * 
 * @author Trevor Colton and Austin Fashimpaur
 *
 */
public class SqlStores {

	/**
	 * Creates a Stores Table With the columns Id, StoreName, City,
	 * and State
	 * @return sql command to create the Student table.
	 */
	public static String createTable() {
		return "CREATE TABLE Stores (" 
				+ "ID int not null primary key " 
				+ "  GENERATED ALWAYS AS IDENTITY"
				+ "  (START WITH 1, INCREMENT BY 1)," 
				+ "StoreName varchar(255)," 
				+ "City varchar(255),"
				+ "State varchar(255))";
	}

	/**
	 * Inserts five rows into the existing Table in the database
	 * @return sql command to insert the Stores Table
	 */
	public static String insertData() {
		return "INSERT INTO Stores (Name, City, State)" 
				+ "VALUES ('The Slope Store', 'Sandy', 'UT'),"
				+ "('The Slope Store', 'Park City', 'UT')," 
				+ "('The Slope Store', 'Orem', 'UT')," 
				+ "('The Slope Store', 'Mammoth Lakes', 'CA'),"
				+ "('The Slope Store', 'Denver', 'CO')";
	}
	
	/**
	 * Gets info from existing Stores table currently in the database
	 * @return sql command to retrieve info from current Stores table
	 */
	public static String getAll() {
		return "SELECT * FROM Stores";
	}
	
	/**
	 * Drops the current Stores Table Currently in the database.
	 * @return sql Command to drop the Stores Table.
	 */
	public static String dropTable() {
		return "DROP TABLE Stores";
	}
	
}
