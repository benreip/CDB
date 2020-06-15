package Persistence;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;

public class BddConnection {
	public static BddConnection db;
	public Connection login;
	private String URL =  "jdbc:mysql://localhost:3306/computer-database-db";
	public final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String USERNAME = "admincdb";
	private final static String PASSWORD = "qwerty1234";
	/* On crée la connexion à la DB*/
	private BddConnection() {
	}
		
	private void getInstance() {
		try {
			Class.forName(DRIVER).newInstance();
			this.login = (Connection)DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
	
	}	
	public static synchronized BddConnection getDbConnection() {
		if (db ==null) {
			db = new BddConnection();
		}
		return db;
	}
			
			
			
			
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
