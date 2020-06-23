package com.excilys.formation.cdb.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.modele.Companie;


public class MapperCompanieDAO {
	final Logger logger = LoggerFactory.getLogger(MapperCompanieDAO.class);
	public Companie mapCompanie (ResultSet rs) {
		Companie c = new Companie();
		try {
			  c.setCompanieId(rs.getInt(1));
			  c.setCompanieName(rs.getString(2));	
			return c; } catch (SQLException e) {logger.error("erreur lors de l'ajout, ajout d'un objet null"); return c;}
	}
}
