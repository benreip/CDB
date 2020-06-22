package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.excilys.formation.cdb.modele.Computer;
import com.excilys.formation.cdb.ui.User;



public class ComputerDAO {
	
	private  final String All_COMPUTERS = "SELECT * FROM computer LIMIT 0,9";
	private  final String FIND_COMPUTERBYID = " SELECT * FROM computer where computer.id = ";
	
	private  final String All_COMPUTERS_LIMIT = "SELECT * FROM computer LIMIT ? , ?";
	public ComputerDAO() {}
	
	// Diverses requêtes, inutile de détailler
	
	/*public  ResultSet getComputers() throws SQLException {
		Statement stmt = conn.login.createStatement();
		return stmt.executeQuery(All_COMPUTERS);
	}*/
	
	/*public   List<Computer> getComputers() {
		List<Computer> toReturn = new ArrayList<>();
		try {//Statement stmt = conn.login.createStatement();
		ResultSet rs = BddConnection.login.createStatement().executeQuery(All_COMPUTERS);
		System.out.println(rs);
		 while (rs.next()) {
			Computer c = new Computer();
			c.setComputerid(rs.getInt(1));
			c.setComputername(rs.getString(2));
			if (rs.getDate(3)!= null) {
			c.setComputerintroductedin(rs.getDate(3).toLocalDate());
			}
			if (rs.getDate(4)!= null) {
			c.setComputerdiscontinuedin(rs.getDate(4).toLocalDate());
			}
			if (rs.getInt(5)!=0) {
			c.setComputercompanieid(rs.getInt(5));
			}
			toReturn.add(c);
		 }
	return toReturn;

	} catch (SQLException e) { e.printStackTrace();}
		return toReturn;
	}*/
	
	public List<Computer> getComputers(int from, int to) {
		List<Computer> toReturn = new ArrayList<>();
		try {// Statement stmt = conn.login.createStatement();
			PreparedStatement preparedStatement = BddConnection.login.prepareStatement(All_COMPUTERS_LIMIT);
			preparedStatement.setInt(1, from);
			preparedStatement.setInt(2, to);
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				Computer c = new Computer();
				c.setComputerid(rs.getInt(1));
				c.setComputername(rs.getString(2));
				if (rs.getDate(3) != null) {
					c.setComputerintroductedin(rs.getDate(3).toLocalDate());
				}
				if (rs.getDate(4) != null) {
					c.setComputerdiscontinuedin(rs.getDate(4).toLocalDate());
				}
				if (rs.getInt(5) != 0) {
					c.setComputercompanieid(rs.getInt(5));
				}
				toReturn.add(c);
			}
			return toReturn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	
	public  Computer findComputerById(int a)  {
		Computer d = null;
		try {
		ResultSet rs = BddConnection.login.createStatement().executeQuery(FIND_COMPUTERBYID+a);
		Computer c = new Computer();
		if(rs.first()) {
			c.setComputerid(rs.getInt(1));
			c.setComputername(rs.getString(2));
			if (rs.getDate(3)!=null) {
			c.setComputerintroductedin(rs.getDate(3).toLocalDate());
			}
			if (rs.getDate(4)!=null) {
			c.setComputerdiscontinuedin(rs.getDate(4).toLocalDate());
			}
			if (rs.getInt(5)!=0) {
				c.setComputercompanieid(rs.getInt(5));
			} 
			return c;
		}

		
	} catch (SQLException e) {e.printStackTrace();} return d;
}
	
	 public int updateName(int id, String newname) {
		try {PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET name = ? where id ="+id);
		stmt.setString(1, newname);
		return stmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return 1;
	}
	
	public int deleteComputerById (int id) {
		try { PreparedStatement stmt = BddConnection.login.prepareStatement("DELETE FROM computer WHERE computer.id ="+id );
		return stmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return 0;
	}
	
	public int updateDateSortie ( int id, String date) {
		try {
			LocalDate d = convert(date);
			PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET introduced = ? where id ="+id );
			stmt.setDate(1, Date.valueOf(d));
		return stmt.executeUpdate();} catch (SQLException e) {e.printStackTrace();}
		return 0;
	}
	
	public int updateDateFin ( int id, String date) {
		try {
		LocalDate d = convert (date);	
		PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET discontinued = ? where id =" + id);
		stmt.setDate(1, Date.valueOf(d));
		return stmt.executeUpdate();} catch (SQLException e) {e.printStackTrace();}
		return 0;
	}
	
	public Computer updateFabricant (int id, int company_id) {
		Computer c = null;
		try {PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET company_id ="+ company_id+" where id ="+id );
		stmt.executeUpdate();
		return this.findComputerById(id);}
		catch(SQLException e) {e.printStackTrace();}
		return c;
	}
	
	public int insertComputer (String computername) {
		try {PreparedStatement stmt = BddConnection.login.prepareStatement("INSERT INTO computer (name) VALUES (?)");
		stmt.setString(1,computername);
		return stmt.executeUpdate();}
		catch (SQLException e) { System.out.println("erreur déjà ici");}
		return 0;
	}
	
	private LocalDate convert (String date) {
		return LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	

}
