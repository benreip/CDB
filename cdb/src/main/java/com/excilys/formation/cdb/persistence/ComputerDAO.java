package com.excilys.formation.cdb.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jca.cci.InvalidResultSetAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.mapper.MapperComputer;
import com.excilys.formation.cdb.modele.Computer;
import com.excilys.formation.cdb.modele.Page;



@Repository
public class ComputerDAO {
	MapperComputer mcdao = new MapperComputer();
	BddConnection login = BddConnection.getDbConnection();
	private  final String FIND_COMPUTERBYID = " SELECT * FROM computer where computer.id = ";
	final static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	private final NamedParameterJdbcTemplate jdbcTemplate;
	public ComputerDAO(final DataSource dataSource) {
		jdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
	}

	// Diverses requêtes, inutile de détailler


	public List<Computer> geget(final Integer from,final Integer to) {

		final String sql = "select id,name,introduced,discontinued,company_id from computer LIMIT "+from+", "+to;
		try
		{
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<Computer>>() {
				@Override
				public List<Computer> extractData(final ResultSet rs) throws SQLException {
					final List<Computer> result = new ArrayList();
					while (rs.next()) {
						result.add(toEntity(rs));
					}
					return result;
				}
			}


					); } catch (final InvalidResultSetAccessException e) 
		{
						throw new RuntimeException(e);
		} 
		catch (final DataAccessException e)
		{
			throw new RuntimeException(e);
		} 
	}

	public int getNumberOfComputers() {
		try {
			final SqlParameterSource parameters = new MapSqlParameterSource();
			final int result = jdbcTemplate.queryForObject(
					"SELECT COUNT(id) FROM computer",parameters,Integer.class);
			return result;
		}

		catch (final DataAccessException e)
		{
			throw new RuntimeException(e);
		}
	}


	public Computer findComputerById(final int a) {
		final String sql="SELECT id,name,introduced,discontinued,company_id FROM computer WHERE ID = "+a;
		try
		{
			return jdbcTemplate.query(sql, new ResultSetExtractor<Computer>() {
				@Override
				public Computer extractData(final ResultSet rs) throws SQLException {
					Computer result = null;
					while (rs.next()) {
						result = toEntity(rs);
					}
					return result;
				}
			}

					); } catch (final InvalidResultSetAccessException e) 
		{
						throw new RuntimeException(e);
		} 
		catch (final DataAccessException e)
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

	public int deleteComputerById (final int id) {
		final SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", id);
		final String sql = "DELETE FROM computer where id = :id";
		return jdbcTemplate.update(sql, parameters);
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


	public Computer updateAll (final Computer c) {
		System.out.println(c);
		final SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("name",c.getName())
				.addValue("introduced", c.getIntroduced()==null?null:Date.valueOf(c.getIntroduced()))
				.addValue("discontinued",c.getDiscontinued()==null?null:Date.valueOf(c.getDiscontinued()))
				.addValue("idcompany", c.getIdcompany()==null?null:c.getIdcompany())
				.addValue("id",c.getId());
		System.out.println(parameters);
		final String sql = "UPDATE computer SET name= :name, introduced = :introduced, discontinued = :discontinued, company_id = :idcompany where id = :id";
		jdbcTemplate.update(sql,parameters);
		return c;
	}


	public List<Computer> searchByName (final String research,final Page page){
		final Integer offset = (page.getCurrentPage()-1)* page.getNb_entries_per_page();
		final String sql = "SELECT * from computer LEFT JOIN company on computer.company_id = company.id  where computer.name like '%"+research+"%' or company.name LIKE '%"+ research+ "%' ORDER BY "+page.getColonne()+" "+page.getAscending()+" LIMIT "+offset +" , "+page.getNb_entries_per_page();
		try
		{
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<Computer>>() {
				@Override
				public List<Computer> extractData(final ResultSet rs) throws SQLException {
					final List<Computer> result = new ArrayList();
					while (rs.next()) {
						result.add(toEntity(rs));
					}
					return result;
				}
			}


					); } catch (final InvalidResultSetAccessException e) 
		{
						throw new RuntimeException(e);
		} 
		catch (final DataAccessException e)
		{
			throw new RuntimeException(e);
		} 
	}


	public int deleteCascade (final int idcomp) {
		try {
			BddConnection.login.setAutoCommit(false);
			PreparedStatement stmt = BddConnection.login.prepareStatement("DELETE FROM computer where company_id ="+idcomp);
			stmt.executeUpdate();
			stmt = BddConnection.login.prepareStatement("DELETE FROM company where id = ?");
			stmt.setInt(1,idcomp);
			stmt.executeUpdate();
			BddConnection.login.commit();
			return 1;
		} catch (final SQLException e) {
			logger.error("Erreur lors de l'opération, tout est annulé \n");
			e.printStackTrace();
			try {BddConnection.login.rollback();} catch (final SQLException f) {f.printStackTrace();} 
			return 0;
		}



	}

	public Computer create(final Computer c) {
		final SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("name",c.getName())
				.addValue("introduced", c.getIntroduced()==null?null:Date.valueOf(c.getIntroduced()))
				.addValue("discontinued",c.getDiscontinued()==null?null:Date.valueOf(c.getDiscontinued()))
				.addValue("idcompany", c.getIdcompany()==null?null:c.getIdcompany());
		final String sql = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(:name,:introduced,:discontinued,:idcompany)";
		jdbcTemplate.update(sql,parameters);

		return c;
	}


	public int getNumberOfComputersBySearch(final String research) {
		final SqlParameterSource parameters = new MapSqlParameterSource();
		final String sql="SELECT COUNT(*) from computer LEFT JOIN company on computer.company_id = company.id  where computer.name like '%" +research +"%' or company.name LIKE '%"+ research+ "%'";
		try {
			final int result = jdbcTemplate.queryForObject(
					sql,parameters,Integer.class);
			return result;
		}

		catch (final DataAccessException e)
		{
			throw new RuntimeException(e);
		}

	}

	public List<Computer> getComputersOrderBy (final String colonne,final String ascending,final Integer from, final Integer to) {
		final String sql="SELECT * FROM computer LEFT JOIN company on computer.company_id = company.id ORDER BY "+colonne+" "+ascending+" LIMIT ?,?";
		try
		{
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<Computer>>() {
				@Override
				public List<Computer> extractData(final ResultSet rs) throws SQLException {
					final List<Computer> result = new ArrayList();
					while (rs.next()) {
						result.add(toEntity(rs));
					}
					return result;
				}
			}


					); } catch (final InvalidResultSetAccessException e) 
		{
						throw new RuntimeException(e);
		} 
		catch (final DataAccessException e)
		{
			throw new RuntimeException(e);
		}
	}

	public static  Computer toEntity (final ResultSet rs) {
		final Computer c = new Computer();
		try {
			c.setId(rs.getInt(1));
			c.setName(rs.getString(2));
			if (rs.getDate(3) != null) {
				c.setIntroduced(rs.getDate(3).toLocalDate());
			}
			if (rs.getDate(4) != null) {
				c.setDiscontinued(rs.getDate(4).toLocalDate());
			}
			if (rs.getInt(5) != 0) {
				c.setIdcompany(rs.getInt(5));
			}	
			return c; } catch (final SQLException e) {logger.error("erreur lors de l'ajout, ajout d'un objet null"); return c;}
	}


	private LocalDate convert (final String date) {
		return LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}


	public static void main(final String[] args) {
		final List<String> drinks = Arrays.asList("can","cup");
		for (final int container =0;container<drinks.size();)
			System.out.println(drinks.get(container));
	}

}
