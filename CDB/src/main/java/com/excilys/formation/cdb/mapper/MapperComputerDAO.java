package com.excilys.formation.cdb.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.modele.Computer;

public class MapperComputerDAO {
	final Logger logger = LoggerFactory.getLogger(MapperComputerDAO.class);
	public Computer mapComputers (ResultSet rs) {
		Computer c = new Computer();
		try {
			c.setComputerid(rs.getInt(1));
			c.setComputername(rs.getString(2));
			if (rs.getDate(3) != null) {
				c.setComputerintroductedin(rs.getDate(3).toLocalDate());
			}
			if (rs.getDate(4) != null) {
				c.setComputerdiscontinuedin(rs.getDate(4).toLocalDate());
			}
			if (rs.getInt(5) != 0) {
				c.setComputercompanieid(rs.getInt(5));
			}	
			return c; } catch (SQLException e) {logger.error("erreur lors de l'ajout, ajout d'un objet null"); return c;}
	}
	
}
