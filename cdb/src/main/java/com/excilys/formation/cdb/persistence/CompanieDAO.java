package com.excilys.formation.cdb.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.modele.Companie;


@Repository
public class CompanieDAO {
	@PersistenceContext
	EntityManager em;
	final Logger logger = LoggerFactory.getLogger(CompanieDAO.class);
	private  final String ALLCOMPANIES =  "SELECT * FROM company";
	private  final String FINDCOMPANYBYID = "SELECT * FROM company where id =";
	public CompanieDAO() {};

	public List<Companie> getAllCompanies () {

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Companie> criteriaQuery = cb.createQuery(Companie.class);
		final Root<Companie> root = criteriaQuery.from(Companie.class);
		criteriaQuery.select(root);

		final TypedQuery <Companie> complist= em.createQuery(criteriaQuery);
		return complist.getResultList();}

	public Companie findCompanyById(final int a) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Companie> criteriaQuery = cb.createQuery(Companie.class);
		final Root<Companie> root = criteriaQuery.from(Companie.class);
		final Predicate byId = cb.equal(root.get("id"),a);
		criteriaQuery.select(root).where(byId);
		TypedQuery <Companie> complist= null;
		complist = em.createQuery(criteriaQuery);
		return complist.getSingleResult();
	}

}