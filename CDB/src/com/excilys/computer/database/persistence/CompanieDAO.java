package com.excilys.computer.database.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.excilys.computer.database.modele.Companie;
import com.mysql.jdbc.PreparedStatement;


public class CompanieDAO {
		private BddConnection conn = BddConnection.getDbConnection();
		
		private  final String ALLCOMPANIES =  "SELECT * FROM company";
		private  final String FINDCOMPANYBYID = "SELECT * FROM company where id = ?";
		
		public CompanieDAO() {};
		
		public ResultSet getAllCompanies() throws SQLException {
			Statement stmt = BddConnection.login.createStatement();
			return stmt.executeQuery(ALLCOMPANIES);
		}
		
		public ResultSet findCompanyById (int id) throws SQLException {
			PreparedStatement stmt = (PreparedStatement) BddConnection.login.prepareStatement(FINDCOMPANYBYID);
			stmt.setInt(1, id);
			return stmt.executeQuery();
		}
}
