package com.excilys.formation.cdb.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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



	public List<Computer> findComputers (final Page page,final Integer from, final Integer to) {

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		final Root<Computer> root = criteriaQuery.from(Computer.class);
		criteriaQuery.select(root);
		if(page.getAscending().equals("DESC")) {
			if(page.getColonne().equals("company")) {
				criteriaQuery.orderBy(cb.desc(root.get(page.getColonne()).get("companiename")));
			}
			else {
				criteriaQuery.orderBy(cb.desc(root.get(page.getColonne())));
			}
		}
		else {
			if(page.getColonne().equals("company")) {
				criteriaQuery.orderBy(cb.asc(root.get(page.getColonne()).get("companiename")));
			}
			else {
				criteriaQuery.orderBy(cb.asc(root.get(page.getColonne())));
			}
		}
		final TypedQuery <Computer> complist= em.createQuery(criteriaQuery).setFirstResult(from).setMaxResults(to);
		return complist.getResultList();
	}


	public int getNumberOfComputers() {

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		final Root<Computer> root = criteriaQuery.from(Computer.class);
		criteriaQuery.select(cb.count(root));
		return em.createQuery(criteriaQuery).getSingleResult().intValue();

	}


	public Computer findComputerById(final int a) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		final Root<Computer> root = criteriaQuery.from(Computer.class);
		final Predicate byId = cb.equal(root.get("id"),a);
		criteriaQuery.select(root).where(byId);
		TypedQuery <Computer> complist= null;
		complist = em.createQuery(criteriaQuery);
		return complist.getSingleResult();
	}

	@Transactional
	public int deleteComputerById (final int id) {
		final Computer c = findComputerById(id);
		em.remove(c);
		return 1;
	}



	@Transactional
	public Computer updateAll (final Computer c) {
		em.merge(c);
		return c;
	}


	public List<Computer> searchByName ( String research,final Page page){
		research = "%"+research+"%";
		final Integer offset = (page.getCurrentPage()-1)* page.getNb_entries_per_page();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		final Root<Computer> root = criteriaQuery.from(Computer.class);
		criteriaQuery.select(root);
		final Join <Computer,Companie> joinLeft = root.join("company",JoinType.LEFT);
		final Predicate onComputerName = cb.like(root.get("name"), research);
		final Predicate onCompanyName = cb.like(joinLeft.get("companiename"), research);
		final Predicate orSearch = cb.or(onComputerName,onCompanyName);
		criteriaQuery.select(root).where(orSearch);
		if(page.getAscending().equals("DESC")) {
			if(page.getColonne().equals("company")) {
				criteriaQuery.orderBy(cb.desc(root.get(page.getColonne()).get("companiename")));
			}
			else {
				criteriaQuery.orderBy(cb.desc(root.get(page.getColonne())));
			}
		}
		else {
			if(page.getColonne().equals("company")) {
				criteriaQuery.orderBy(cb.asc(root.get(page.getColonne()).get("name")));
			}
			else {
				criteriaQuery.orderBy(cb.asc(root.get(page.getColonne())));
			}
		}
		final TypedQuery <Computer> complist= em.createQuery(criteriaQuery).setFirstResult(offset).setMaxResults(page.getNb_entries_per_page());

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
	@Transactional
	public Computer create(final Computer c) {
		em.persist(c);
		return c;
	}


	public int getNumberOfComputersBySearch( String research) {
		research = "%"+research+"%";
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		final Root<Computer> root = criteriaQuery.from(Computer.class);
		final Join <Computer,Companie> joinLeft = root.join("company",JoinType.LEFT);
		final Predicate onComputerName = cb.like(root.get("name"), research);
		final Predicate onCompanyName = cb.like(joinLeft.get("companiename"), research);
		final Predicate orSearch = cb.or(onComputerName,onCompanyName);
		criteriaQuery.select(cb.count(root)).where(orSearch);
		return em.createQuery(criteriaQuery).getSingleResult().intValue();


	}

	/*public List<Computer> getComputersOrderBy (final String colonne,final String ascending,final Integer from, final Integer to) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Computer> criteriaQuery = cb.createQuery(Computer.class);
		final Root<Computer> root = criteriaQuery.from(Computer.class);
		final Join<Computer,Companie> leftJoin = root.join("company",JoinType.LEFT);
		final Predicate computerName = cb.like(root.get("name"), "%"+search+"%");
		final Predicate companyName = cb.like(leftJoin.get("name"), "%"+search+"%");
		criteriaQuery.select(root).where(cb.or(computerName,companyName));
		if(ascending.equals("DESC")) {
			if(getAttribute(page).equals("company")) {
				criteriaQuery.orderBy(cb.desc(root.get("name"));
			}
			else {
				criteriaQuery.orderBy(cb.desc(root.get(getAttribute(page))));
			}
		}
		else {
			if(getAttribute(page).equals("company")) {
				criteriaQuery.orderBy(cb.asc(root.get(getAttribute(page)).get("name")));
			}
			else {
				criteriaQuery.orderBy(cb.asc(root.get(getAttribute(page))));
			}
		}
		final TypedQuery <Computer> computers = em.createQuery(criteriaQuery).setFirstResult(offset).setMaxResults(page.getItemsByPage());
		return computers.getResultList();
	}*/



	private LocalDate convert (final String date) {
		return LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}



}