package com.excilys.formation.cdb.persistence;

import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class BddConnection {
	public  static BddConnection db;
	public static  Connection login;
	private Logger logger = LoggerFactory.getLogger(BddConnection.class);
	private HikariDataSource ds = new HikariDataSource(new HikariConfig("/datasource.properties"));
	
	private BddConnection() {
	}
		
	private void getInstance() {
		try {
			this.login = ds.getConnection();
		} catch (Exception sqle) {
			logger.error("Erreur lors de la connection à la databse");
			sqle.printStackTrace();
}
	
	}	
	public static  BddConnection getDbConnection() {
		if (db ==null) {
			db = new BddConnection();
			db.getInstance();
		}
		return db;
	}
			
			
	public void closeConnection() {
		
			
	}
	
	
	
	
	
	
	
	}
