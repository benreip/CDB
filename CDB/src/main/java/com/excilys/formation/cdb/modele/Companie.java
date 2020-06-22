package com.excilys.formation.cdb.modele;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Companie {
	private int companieid;
	private String companiename;
	final Logger logger = LoggerFactory.getLogger(Companie.class);
	
	/* Setter et Getter*/
	
	public int getCompanieId() {
		return companieid;
	}
	
	public String getCompanieName() {
		return companiename;
	}
	
	public void setCompanieName(final String companiename) {
		this.companiename= companiename;
		logger.info("Compagnie name set to {}.",companiename);
	}
	
	public void setCompanieId(final int companieid) {
		this.companieid=companieid;
		logger.info("Compagnie id set to {}.",companieid);
	}
	
	@Override
	public String toString() {
		return "Companie [ id=," +this.companieid+ " Nom de la compagnie : "+ this.companiename+ "\n";
	
}
}