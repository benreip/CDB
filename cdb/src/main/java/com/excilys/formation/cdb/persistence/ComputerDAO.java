package com.excilys.formation.cdb.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
import com.excilys.formation.cdb.modele.Companie;
import com.excilys.formation.cdb.modele.Computer;
import com.excilys.formation.cdb.modele.Page;




@Repository
public class ComputerDAO {
	@PersistenceContext
	EntityManager em;

	MapperComputer mcdao = new MapperComputer();
	BddConnection login = BddConnection.getDbConnection();
	private  final String FIND_COMPUTERBYID = " SELECT * FROM computer where computer.id = ";
	final static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	private final NamedParameterJdbcTemplate jdbcTemplate;
	public ComputerDAO(final DataSource dataSource) {
		jdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
	}



	public List<Computer> galageget (final Integer from, final Integer to) {

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		final Root<Computer> root = criteriaQuery.from(Computer.class);
		criteriaQuery.select(root);
		TypedQuery <Computer> complist= null;
		complist = em.createQuery(criteriaQuery).setFirstResult(from).setMaxResults(to);
		return complist.getResultList();
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


	public int deleteComputerById (final int id) {
		final SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", id);
		final String sql = "DELETE FROM computer where id = :id";
		return jdbcTemplate.update(sql, parameters);
	}




	public Computer updateAll (final Computer c) {
		System.out.println(c);
		final SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("name",c.getName())
				.addValue("introduced", c.getIntroduced()==null?null:Date.valueOf(c.getIntroduced()))
				.addValue("discontinued",c.getDiscontinued()==null?null:Date.valueOf(c.getDiscontinued()))
				.addValue("idcompany", c.getCompany()==null?null:c.getCompany())
				.addValue("id",c.getId());
		System.out.println(parameters);
		final String sql = "UPDATE computer SET name= :name, introduced = :introduced, discontinued = :discontinued, company_id = :idcompany where id = :id";
		jdbcTemplate.update(sql,parameters);
		return c;
	}


	public List<Computer> searchByName (final String research,final Page page){
		final Integer offset = (page.getCurrentPage()-1)* page.getNb_entries_per_page();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		final Root<Computer> root = criteriaQuery.from(Computer.class);
		criteriaQuery.select(root);
		final Join<Companie, Computer> companyParty = root.join("company", JoinType.LEFT);
		final Predicate byComputerName = cb.like(root.get("name"), research);
		final Predicate byCompanyName = cb.like(companyParty.get("name"), research);
		final Predicate orSearch = cb.or(byComputerName, byCompanyName);
		criteriaQuery.where(orSearch);
		TypedQuery <Computer> complist= null;
		complist = em.createQuery(criteriaQuery).setFirstResult(offset).setMaxResults(page.getNb_entries_per_page());
		return complist.getResultList();

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
				.addValue("idcompany", c.getCompany()==null?null:c.getCompany());
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



	private LocalDate convert (final String date) {
		return LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}



}
