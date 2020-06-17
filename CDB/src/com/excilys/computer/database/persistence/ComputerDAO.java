package com.excilys.computer.database.persistence;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.excilys.computer.database.modele.Computer;
import com.excilys.computer.database.ui.User;


public class ComputerDAO {
	private  final String INSERT_COMPUTER = "INSERT INTO computer (name) VALUES ()";
	private  final String All_COMPUTERS = "SELECT * FROM computer";
	private  final String FIND_COMPUTERBYID = " SELECT * FROM computer where computer.id = ";
	private  final String DELETE_COMPUTERBYID = "DELETE FROM computer WHERE computer.id = ";
	
	public ComputerDAO() {}
	
	// Diverses requêtes, inutile de détailler
	
	public   List<Computer> getComputers() {
		List<Computer> l = null;
		try {Statement stmt = BddConnection.login.createStatement();
		ResultSet rs = stmt.executeQuery(All_COMPUTERS);
		List<Computer> toReturn = new ArrayList<>();
		 while (rs.next()) {
			Computer c = new Computer();
			c.setComputerid(rs.getInt(1));
			c.setComputername(rs.getString(2));
			c.setComputerintroductedin(rs.getDate(3).toLocalDate());
			c.setComputerdiscontinuedin(rs.getDate(4).toLocalDate());
			c.setComputercompanieid(rs.getInt(5));
			
			toReturn.add(c);
		 }
	return toReturn;

	} catch (SQLException e) { e.printStackTrace();}
		return l;
	}
	public  Computer findComputerById(int a)  {
		Computer c = null;
		try {Statement stmt = BddConnection.login.createStatement();
		ResultSet rs = stmt.executeQuery(FIND_COMPUTERBYID+a);
		c = new Computer();
		if(rs.first()) {
			c.setComputername(rs.getString(2));
		}

		
	} catch (SQLException e) {e.printStackTrace();}
		return c;}
	
	 public int updateName(int id, String newname) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET name =" + newname +" where id ="+id);
		stmt.setInt(1, id);
		stmt.setString(2, newname);
		return stmt.executeUpdate();
	}
	
	public int deleteComputerById (int id) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement(DELETE_COMPUTERBYID+id);
		stmt.setInt(1, id);
		return stmt.executeUpdate();
	}
	
	public int updateDateSortie ( int id, LocalDate date) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET introduced ="+ date+ "  where id ="+id);
		stmt.setInt(1, id);
		stmt.setDate(2, Date.valueOf(date));
		return stmt.executeUpdate();
	}
	
	public int updateDateFin ( int id, LocalDate date) throws SQLException {
		
		PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET discontinued ="+date+ "where id ="+id);
		stmt.setInt(1, id);
		stmt.setDate(2, Date.valueOf(date));
		return stmt.executeUpdate();
	}
	
	public int updateFabricant (int id, int company_id) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET company_id ="+company_id+" where id ="+id);
		stmt.setInt(1, id);
		stmt.setInt(2, company_id);
		return stmt.executeUpdate();
	}
	
	public int insertComputer (String computername) throws SQLException {
		PreparedStatement stmt = BddConnection.login.prepareStatement(INSERT_COMPUTER);
		stmt.setString(1,computername);
		return stmt.executeUpdate();
	}
	


public static void main(String[] args) {
	// TODO Auto-generated method stub
	ComputerDAO cp= new ComputerDAO();
	System.out.println(cp.getComputers());
	
}
}
