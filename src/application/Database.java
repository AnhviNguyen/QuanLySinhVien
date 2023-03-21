package application;


import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	
	public static Connection connectDb() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connect = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"+
					                   "databaseName=studentData;user=sa;password=12345");
			return connect;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

