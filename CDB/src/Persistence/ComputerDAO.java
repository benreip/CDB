package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Modele.Computer;


public class ComputerDAO {
	
	private BddConnection conn = BddConnection.getDbConnection();
	
	private static final String INSERT_COMPUTER = "INSERT INTO computer (name) VALUES (?)";
	private static final String All_COMPUTERS = "SELECT * FROM computer";
	private static final String FIND_COMPUTER = " SELECT * FROM computer where computer.id = ?";
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE computer.id = ?";
	private static final String UPDATE_NAME = "UPDATE computer SET name = ? where id =? ";
	private static final String UPDATE_DATESORTIE= "UPDATE computer SET introduced = ? where id =? ";
	private static final String UPDATE_DATEFIN = "UPDATE computer SET discontinued = ? where id =? ";
	private static final String UPDATE_FABRICANT = "UPDATE computer SET name = ? where company_id =? ";
	
	public ComputerDAO() {}
	
	public ResultSet getComputers() throws SQLException {
		Statement stmt = conn.login.createStatement();
		return stmt.executeQuery(All_COMPUTERS);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println(getComputers());
		
}
}
