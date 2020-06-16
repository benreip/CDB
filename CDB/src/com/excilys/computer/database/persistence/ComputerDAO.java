package com.excilys.computer.database.persistence;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class ComputerDAO {
	
	private BddConnection conn = BddConnection.getDbConnection();
	
	private  final String INSERT_COMPUTER = "INSERT INTO computer (name) VALUES (?)";
	private  final String All_COMPUTERS = "SELECT * FROM computer";
	private  final String FIND_COMPUTERBYID = " SELECT * FROM computer where computer.id = ?";
	private  final String DELETE_COMPUTERBYID = "DELETE FROM computer WHERE computer.id = ?";
	private  final String UPDATE_NAMEBYID = "UPDATE computer SET name = ? where id =? ";
	private  final String UPDATE_DATESORTIE_ID= "UPDATE computer SET introduced = ? where id =? ";
	private  final String UPDATE_DATEFIN_ID = "UPDATE computer SET discontinued = ? where id =? ";
	private  final String UPDATE_FABRICANT = "UPDATE computer SET name = ? where company_id =? ";
	
	public ComputerDAO() {}
	
	// Diverses requêtes, inutile de détailler
	
	public  ResultSet getComputers() throws SQLException {
		Statement stmt = conn.login.createStatement();
		return stmt.executeQuery(All_COMPUTERS);
	}
	
	public ResultSet findComputerById(int id) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement(FIND_COMPUTERBYID);
		stmt.setInt(1, id);
		return stmt.executeQuery();
	}
	
	public int updateName(int id, String newname) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement(UPDATE_NAMEBYID);
		stmt.setInt(1, id);
		stmt.setString(2, newname);
		return stmt.executeUpdate();
	}
	
	public int deleteComputerById (int id) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement(DELETE_COMPUTERBYID);
		stmt.setInt(1, id);
		return stmt.executeUpdate();
	}
	
	public int updateDateSortie ( int id, Date date) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement(UPDATE_DATESORTIE_ID);
		stmt.setInt(1, id);
		stmt.setDate(2,  (java.sql.Date) date);
		return stmt.executeUpdate();
	}
	
	public int updateDateFin ( int id, Date date) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement(UPDATE_DATEFIN_ID);
		stmt.setInt(1, id);
		stmt.setDate(2,  (java.sql.Date) date);
		return stmt.executeUpdate();
	}
	
	public int updateFabricant (int id, int company_id) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement(UPDATE_FABRICANT);
		stmt.setInt(1, id);
		stmt.setInt(2, company_id);
		return stmt.executeUpdate();
	}
	
	public int insertComputer (String computername) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement(INSERT_COMPUTER);
		stmt.setString(1,computername);
		return stmt.executeUpdate();
	}
	
}
