package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jca.cci.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;

import com.excilys.formation.cdb.mapper.MapperComputerDAO;
import com.excilys.formation.cdb.modele.Computer;



@Repository
public class ComputerDAO {
	MapperComputerDAO mcdao = new MapperComputerDAO();
	BddConnection login = BddConnection.getDbConnection();
	private  final String FIND_COMPUTERBYID = " SELECT * FROM computer where computer.id = ";
	final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	private JdbcTemplate jdbcTemplate;
	public ComputerDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// Diverses requêtes, inutile de détailler
	
	
	public List<Computer> geget(Integer from,Integer to) {
		
		String sql = "select id,name,introduced,discontinued,company_id from computer LIMIT "+from+", "+to;
		try
		{
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Computer>>() {
				@Override
				public List<Computer> extractData(ResultSet rs) throws SQLException {
				List<Computer> result = new ArrayList();
			while (rs.next()) {
				result.add(mcdao.mapComputers(rs));
			}
			return result;
		}
		}
				

		); } catch (InvalidResultSetAccessException e) 
		{
		    throw new RuntimeException(e);
		} 
		catch (DataAccessException e)
		{
		    throw new RuntimeException(e);
		} 
	}
	
	public int getNumberOfComputers() {
		try {
		int result = jdbcTemplate.queryForObject(
			    "SELECT COUNT(id) FROM computer",Integer.class);
		return result;
		}
		
		catch (DataAccessException e)
		{
		    throw new RuntimeException(e);
		}
	}
	
	
	public Computer findComputerById(int a) {
		String sql="SELECT id,name,introduced,discontinued,company_id FROM computer WHERE ID = "+a;
		try
		{
		return jdbcTemplate.query(sql, new ResultSetExtractor<Computer>() {
				@Override
				public Computer extractData(ResultSet rs) throws SQLException {
				Computer result = null;
			while (rs.next()) {
				result =mcdao.mapComputers(rs);
			}
			return result;
		}
		}
				
		); } catch (InvalidResultSetAccessException e) 
		{
		    throw new RuntimeException(e);
		} 
		catch (DataAccessException e)
		{
		    throw new RuntimeException(e);
		} 
	}
	/*public  Computer findComputerById(int a)  {
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
}*/
	
	/* public int updateName(int id, String newname) {
		try {PreparedStatement stmt = BddConnection.login.prepareStatement("UPDATE computer SET name = ? where id ="+id);
		stmt.setString(1, newname);
		logger.info("Nom mis à jour sur l'ordinateur {} . Nouveau nom set sur {} \n",id,newname);
		return stmt.executeUpdate();
		} catch (SQLException e) {
			logger.error("erreur lors de l'update du nom sur l'ordinateur spécifié \n");
			e.printStackTrace();}
		return 1;
	}*/
	
	public int deleteComputerById (int id) {
		String sql = "DELETE FROM computer where id = ?";
		Object[] args = new Object[] {id};
		return jdbcTemplate.update(sql, args);
	}
	
	/*public int updateDateSortie ( int id, String date) {
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
	}*/
	
	
	public Computer updateAll (Computer c) {
		String sql = "UPDATE computer SET name = ' " +c.getName()+"' , introduced = '" + Date.valueOf(c.getIntroduced())+ "' , discontinued = '" + Date.valueOf(c.getDiscontinued()) + "', company_id = '" + c.getIdcompany() + "' where id =" +c.getId();
		jdbcTemplate.update(sql);
		return c;
	}

	
	public List<Computer> searchByName (String research,Integer from, Integer to, String colonne, String ascending){
		String sql = "SELECT * from computer LEFT JOIN company on computer.company_id = company.id  where computer.name like '%"+research+"%' or company.name LIKE '%"+ research+ "%' ORDER BY "+colonne+" "+ascending+" LIMIT ?,?";
		try
		{
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Computer>>() {
				@Override
				public List<Computer> extractData(ResultSet rs) throws SQLException {
				List<Computer> result = new ArrayList();
			while (rs.next()) {
				result.add(mcdao.mapComputers(rs));
			}
			return result;
		}
		}
				

		); } catch (InvalidResultSetAccessException e) 
		{
		    throw new RuntimeException(e);
		} 
		catch (DataAccessException e)
		{
		    throw new RuntimeException(e);
		} 
	}
		
	
	public int deleteCascade (int idcomp) {
		try {
			BddConnection.login.setAutoCommit(false);
			PreparedStatement stmt = BddConnection.login.prepareStatement("DELETE FROM computer where company_id ="+idcomp);
			stmt.executeUpdate();
			stmt = BddConnection.login.prepareStatement("DELETE FROM company where id = ?");
			stmt.setInt(1,idcomp);
			stmt.executeUpdate();
			BddConnection.login.commit();
			return 1;
		} catch (SQLException e) {
			logger.error("Erreur lors de l'opération, tout est annulé \n");
			e.printStackTrace();
			try {BddConnection.login.rollback();} catch (SQLException f) {f.printStackTrace();} 
			return 0;
		}
		
		
		
	}
	
	public Computer create(Computer c) {
	    jdbcTemplate.update(
	      "INSERT INTO computer VALUES (?, ?, ?, ?,?)", c.getId(), c.getName() , c.getIntroduced(), c.getDiscontinued(),c.getIdcompany());
	    return c;
	}
	
	
	public int getNumberOfComputersBySearch(String research) {
		int count = -1;
		String sql="SELECT COUNT(*) from computer LEFT JOIN company on computer.company_id = company.id  where computer.name like '%" +research +"%' or company.name LIKE '%"+ research+ "%'";
		try {
		int result = jdbcTemplate.queryForObject(
			    sql,Integer.class);
		return result;
		}
		
		catch (DataAccessException e)
		{
		    throw new RuntimeException(e);
		}
		
	}
	
	public List<Computer> getComputersOrderBy (String colonne,String ascending,Integer from, Integer to) {
		String sql="SELECT * FROM computer LEFT JOIN company on computer.company_id = company.id ORDER BY "+colonne+" "+ascending+" LIMIT ?,?";
		try
		{
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Computer>>() {
				@Override
				public List<Computer> extractData(ResultSet rs) throws SQLException {
				List<Computer> result = new ArrayList();
			while (rs.next()) {
				result.add(mcdao.mapComputers(rs));
			}
			return result;
		}
		}
				

		); } catch (InvalidResultSetAccessException e) 
		{
		    throw new RuntimeException(e);
		} 
		catch (DataAccessException e)
		{
		    throw new RuntimeException(e);
		}
	}

	
	private LocalDate convert (String date) {
		return LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	
	

}
