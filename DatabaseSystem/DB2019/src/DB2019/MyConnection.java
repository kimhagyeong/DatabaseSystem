package DB2019;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	public static StringBuilder sb = new StringBuilder();
	public static Connection makeConnection() {
		Connection con = null;
		try {
			// load and register JDBC Driver
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //everyone same
		
			// url= protocol- hostname/server name: port # ; databseName=
	
			String url ="jdbc:sqlserver://LAPTOP-6M03DT2B\\SQLEXPRESS01:1433;databaseName=DreamHome01";
			con=DriverManager.getConnection(url,"sa","1234");
			System.out.println("Connection Established ...");
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Connection failed ...");
		}
		
		return con;
		
		
	}

}
