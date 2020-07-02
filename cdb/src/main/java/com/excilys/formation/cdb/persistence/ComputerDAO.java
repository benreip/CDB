package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;

import com.excilys.formation.cdb.mapper.MapperComputerDAO;
import com.excilys.formation.cdb.modele.Computer;




public class ComputerDAO {
	MapperComputerDAO mcdao = new MapperComputerDAO();
	BddConnection login = BddConnection.getDbConnection();
	private  final String FIND_COMPUTERBYID = " SELECT * FROM computer where computer.id = ";
	final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	private  final String All_COMPUTERS_LIMIT = "SELECT * FROM computer LIMIT ? , ?";
	public ComputerDAO() {
	}
	
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
	
	public List<Computer> getComputers(Integer from, Integer to) {
		List<Computer> toReturn = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = BddConnection.login.prepareStatement(All_COMPUTERS_LIMIT);
			preparedStatement.setInt(1, from);
			preparedStatement.setInt(2, to);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				toReturn.add(mcdao.mapComputers(rs));
			}
			return toReturn;
		} catch (SQLException e) {
			logger.error("erreur lors de l'appel à la base pour les informations des ordinateurs");
			e.getMessage();
			e.printStackTrace();
			return toReturn;
		}
	}
	
	public int getNumberOfComputers() {
		int count = -1;
		try {
			PreparedStatement stmt = BddConnection.login.prepareStatement("SELECT COUNT(*) from computer");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                count = rs.getInt(1);
            }
			return count;
			} catch (SQLException e) { 
				logger.error("erreur lors du calcul d'ordinateurs \n");
				e.printStackTrace();
		}
		return count;
	}
	
	public  Computer findComputerById(int a)  {
		Computer d = null;
		try {
			PreparedStatement preparedStatement = BddConnection.login.prepareStatement(FIND_COMPUTERBYID+a,ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = preparedStatement.executeQuery();
		if(rs.first()) {
			logger.info("Ordinateur {} chargé avec succès ! \n",a);
			return mcdao.mapComputers(rs);
			} 
		} catch (SQLException e) {
		logger.error("erreur lors de l'appel à la base pour les informations sur l'ordinateur demandé \n");
		e.printStackTrace();
	} return d;
}
	
	 public int updateName(int id, String newname) {
		try {PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET name = ? where id ="+id);
		stmt.setString(1, newname);
		logger.info("Nom mis à jour sur l'ordinateur {} . Nouveau nom set sur {} \n",id,newname);
		return stmt.executeUpdate();
		} catch (SQLException e) {
			logger.error("erreur lors de l'update du nom sur l'ordinateur spécifié \n");
			e.printStackTrace();}
		return 1;
	}
	
	public int deleteComputerById (int id) {
		try { PreparedStatement stmt = BddConnection.login.prepareStatement("DELETE FROM computer WHERE computer.id ="+id );
		logger.info("ordinateur avec l'id {} supprimé ! \n",id);
		return stmt.executeUpdate();
		} catch (SQLException e) {
			logger.error("erreur lors de la suppression de l'ordinateur demandé \n");
			e.printStackTrace();
			}
		return 0;
	}
	
	public int updateDateSortie ( int id, String date) {
		try {
			LocalDate d = convert(date);
			PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET introduced = ? where id ="+id );
			stmt.setDate(1, Date.valueOf(d));
			logger.info("date de sortie l'ordinateur {} mise à jour !\n",id);
		return stmt.executeUpdate();} 
		catch (SQLException e) {
			logger.error("erreur lors de l'update sur la date de sortie de l'ordinateur spécifié \n");
			e.printStackTrace();}
		return 0;
	}
	
	public int updateDateFin ( int id, String date) {
		try {
		LocalDate d = convert (date);	
		PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET discontinued = ? where id =" + id);
		stmt.setDate(1, Date.valueOf(d));
		logger.info("date de fin de l'ordinateur {} mise à jour !\n",id);
		return stmt.executeUpdate();
		} catch (SQLException e) {
			logger.error("erreur sur l'update de la date de fin de l'ordinateur spécifié\n");
			e.printStackTrace();}
		return 0;
	}
	
	public Computer updateFabricant (int id, int company_id) {
		Computer c = null;
		try {PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET company_id ="+ company_id+" where id ="+id );
		stmt.executeUpdate();
		logger.info("update sur l'ordinateur {} réussie avec succès !\n",id);
		return this.findComputerById(id);}
		catch(SQLException e) {
			logger.error("erreur lors de l'update sur le fabricant de l'ordinateur spécifié \n");
			e.printStackTrace();
			}
		return c;
	}
	
	public Computer updateAll (Computer c) {
		Computer d = null;
		try {
			String request = "UPDATE computer SET name = ' " + c.getComputername() +"'";
			if (c.getComputerintroductedin() != null ) {
				request += ", introduced = '" + Date.valueOf(c.getComputerintroductedin()) + "'";
			}
			if (c.getComputerdiscontinuedin() != null ) {
				request += ", discontinued = '" + Date.valueOf(c.getComputerdiscontinuedin()) + "'";
			}
			if (c.getComputercompanieid() != null ) {
				request += ", company_id = '" + c.getComputercompanieid() + "'";
			}
			request += "WHERE id = " + c.getComputerid();
			PreparedStatement stmt = BddConnection.login.prepareStatement(request);
			stmt.executeUpdate();
			return c;
			} catch (SQLException e) {
				logger.error("Insertion echouée !");
				e.printStackTrace();
				return d;
			}
	}
	
	public int insertComputer (String computername) {
		try {PreparedStatement stmt = BddConnection.login.prepareStatement("INSERT INTO computer (name) VALUES (?)");
		stmt.setString(1,computername);
		logger.info("Insertion du nouvel ordinateur réussie avec succès \n");
		return stmt.executeUpdate();}
		catch (SQLException e) {
			logger.error("erreur lors de l'insertion du nouvel ordinateur \n");
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Computer> searchByName (String research,Integer from, Integer to, String colonne, String ascending){
		List<Computer> toReturn = new ArrayList<>();
		try{
			PreparedStatement stmt = BddConnection.login.prepareStatement("SELECT * from computer LEFT JOIN company on computer.company_id = company.id  where computer.name like '%"+research+"%' or company.name LIKE '%"+ research+ "%' ORDER BY "+colonne+" "+ascending+" LIMIT ?,?");
			stmt.setInt(1, from);
			stmt.setInt(2, to);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				toReturn.add(mcdao.mapComputers(rs));
			}
			return toReturn;
		} catch (SQLException e) {
			logger.error("Erreur lors de la recherche de l'ordinateur \n");
			e.printStackTrace();
			return toReturn;
		}
		
	}
	
	public int deleteCascade (int idcomp) {
		try {
			BddConnection.login.setAutoCommit(false);
			PreparedStatement stmt = BddConnection.login.prepareStatement("DELETE FROM computer where computer.company_id = ?");
			stmt.setInt(1, idcomp);
			int success = stmt.executeUpdate();
			if (success == 1) {
				stmt = BddConnection.login.prepareStatement("DELETE FROM company where id = ?");
				stmt.setInt(1,idcomp);
				success = stmt.executeUpdate();
				if (success == 1 ) {
					BddConnection.login.commit();
					return 1;
				} else {
					BddConnection.login.rollback();
					return 0;
				}
			} else {
				BddConnection.login.rollback();
				return 0;
			}
			
		} catch (SQLException e) {
			logger.error("Erreur lors de l'opération, tout est annulé \n");
			e.printStackTrace();
			try {BddConnection.login.rollback();} catch (SQLException f) {f.printStackTrace();} 
			return 0;
		}
		
		
		
	}
	
	public Computer create(Computer c) {
		Computer comp = new Computer();
		try {PreparedStatement stmt = BddConnection.login.prepareStatement("INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?);");
		stmt.setString(1, c.getComputername());
		
		if (c.getComputerintroductedin()!=null) {
			stmt.setDate(2, Date.valueOf(c.getComputerintroductedin()));
		}
		else {
			stmt.setNull(2, Types.DATE);;
		}
		if (c.getComputerdiscontinuedin()!=null) {
			stmt.setDate(3, Date.valueOf(c.getComputerdiscontinuedin()));
		}
		else {
			stmt.setNull(3, Types.DATE);;
		}
		if (c.getComputercompanieid()!= null) {
			stmt.setInt(4,c.getComputercompanieid());
		}
		else {
			stmt.setNull(4, Types.BIGINT);;
		}
		stmt.executeUpdate();
		return c;
		
		}catch (SQLException e) {
			logger.error("erreur lors de l'insertion du nouvel ordinateur \n");
			e.printStackTrace();
		}
		return comp;

	}
	
	public int getNumberOfComputersBySearch(String research) {
		int count = -1;
		try {
			PreparedStatement stmt = BddConnection.login.prepareStatement("SELECT COUNT(*) from computer LEFT JOIN company on computer.company_id = company.id  where computer.name like '%" +research +"%' or company.name LIKE '%"+ research+ "%'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                count = rs.getInt(1);
            }
			return count;
			} catch (SQLException e) { 
				logger.error("erreur lors du calcul d'ordinateurs \n");
				e.printStackTrace();
		}
		return count;
	}
	
	public List<Computer> getComputersOrderBy (String colonne,String ascending,Integer from, Integer to) {
		List<Computer> toReturn = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = BddConnection.login.prepareStatement("SELECT * FROM computer LEFT JOIN company on computer.company_id = company.id ORDER BY "+colonne+" "+ascending+" LIMIT ?,?" );
			preparedStatement.setInt(1, from);
			preparedStatement.setInt(2, to);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				toReturn.add(mcdao.mapComputers(rs));
			}
			return toReturn;
		} catch (SQLException e) {
			logger.error("erreur lors de l'appel à la base pour les informations des ordinateurs");
			e.getMessage();
			e.printStackTrace();
			return toReturn;
		}
	}

	
	private LocalDate convert (String date) {
		return LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComputerDAO cdao= new ComputerDAO();
	System.out.println(cdao.deleteCascade(1));

	}
	
	

}
