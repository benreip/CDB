package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.modele.Companie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CompanieDAO {
		final Logger logger = LoggerFactory.getLogger(CompanieDAO.class);
		private  final String ALLCOMPANIES =  "SELECT * FROM company";
		private  final String FINDCOMPANYBYID = "SELECT * FROM company where id =";
		
		public CompanieDAO() {};
		
		public List<Companie> getAllCompanies()  {
			List<Companie> l = null;
			try {
			ResultSet rs = BddConnection.login.createStatement().executeQuery(ALLCOMPANIES);
			List<Companie> toReturn = new ArrayList();
			  while(rs.next()) {
				  Companie c = new Companie();
				  c.setCompanieId(rs.getInt(1));
				  c.setCompanieName(rs.getString(2));
				  toReturn.add(c);
				  }
			  return toReturn; } 
			catch (SQLException e) {
				logger.error("erreur lors de l'appel à la base pour les noms de compagnies");
				e.printStackTrace();
			  }
			
			return l;
			
		}
		
		public Companie findCompanyById (int id) {
			Companie d = new Companie();
			try {
			ResultSet rs = BddConnection.login.createStatement().executeQuery(FINDCOMPANYBYID+id);
			Companie c = new Companie();
			if(rs.first()) {
			c.setCompanieId(rs.getInt(1));
			c.setCompanieName(rs.getString(2));
			}
			return c; } 
			catch (SQLException e) {
				logger.error("erreur lors de l'appel à la base pour les noms de compagnies");
				e.printStackTrace();
				}
			return d;
		}
				
}