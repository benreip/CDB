package com.excilys.formation.cdb.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.modele.Computer;

public class MapperComputerDAO {
	final static Logger logger = LoggerFactory.getLogger(MapperComputerDAO.class);
	public static Computer mapComputers (ResultSet rs) {
		Computer c = new Computer();
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
			return c; } catch (SQLException e) {logger.error("erreur lors de l'ajout, ajout d'un objet null"); return c;}
	}
	
}
