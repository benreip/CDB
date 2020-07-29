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

import com.excilys.formation.cdb.modele.Role;
import com.excilys.formation.cdb.modele.User;

@Repository
public class UserDAO {

	@PersistenceContext
	EntityManager em;

	final static Logger logger = LoggerFactory.getLogger(UserDAO.class);

	public User create(final User u) {
		em.persist(u);
		return u;
	}

	public User findUserByName(final String username) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
		final Root<User> root = criteriaQuery.from(User.class);
		final Predicate byName = cb.equal(root.get("username"),username);
		criteriaQuery.select(root).where(byName);
		final TypedQuery <User> u= em.createQuery(criteriaQuery);
		return u.getSingleResult();
	}

	public List<Role> getRoles () {

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Role> criteriaQuery = cb.createQuery(Role.class);
		final Root<Role> root = criteriaQuery.from(Role.class);
		criteriaQuery.select(root);
		final TypedQuery <Role> roleList= em.createQuery(criteriaQuery);
		return roleList.getResultList();
	}
}
