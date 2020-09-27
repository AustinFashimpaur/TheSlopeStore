package app;

public class SqlItems {

	public static String createTable = "CREATE TABLE Items (" 
			+ "ID int not null primary key " 
            + "  GENERATED ALWAYS AS IDENTITY" 
            + "  (START WITH 100000, INCREMENT BY 1),"  
			+ "Name varchar(255),"  
			+ "LastName varchar(255),"
			+ "Major varchar(255),"
			+ "GradYear int)";

}
