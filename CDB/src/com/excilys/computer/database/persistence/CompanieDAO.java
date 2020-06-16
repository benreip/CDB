package com.excilys.computer.database.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computer.database.modele.Companie;
import com.excilys.computer.database.modele.Computer;
import com.mysql.jdbc.PreparedStatement;


public class CompanieDAO {
		private BddConnection conn = BddConnection.getDbConnection();
		
		private  final String ALLCOMPANIES =  "SELECT * FROM company";
		private  final String FINDCOMPANYBYID = "SELECT * FROM company where id = ?";
		
		public CompanieDAO() {};
		
		public List<Companie> getAllCompanies() throws SQLException {
			Statement stmt = BddConnection.login.createStatement();
			ResultSet rs = stmt.executeQuery(ALLCOMPANIES);
			List<Companie> toReturn = new ArrayList();
			  while(rs.next()) {
				  Companie c = new Companie();
				  c.setCompanieId(rs.getInt(1));
				  c.setCompanieName(rs.getString(2));
				  }
			  return toReturn;
		}
		
		public Companie findCompanyById (int id) throws SQLException {
			PreparedStatement stmt = (PreparedStatement) BddConnection.login.prepareStatement(FINDCOMPANYBYID);
			ResultSet rs =stmt.executeQuery(FINDCOMPANYBYID);
			Companie c = new Companie();
			c.setCompanieName(rs.getString(1));
			return c;
		}
		
		
		
}
