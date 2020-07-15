package com.excilys.formation.cdb.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.mapper.MapperCompanieDAO;
import com.excilys.formation.cdb.mapper.MapperComputer;
import com.excilys.formation.cdb.modele.Companie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class CompanieDAO {
		final Logger logger = LoggerFactory.getLogger(CompanieDAO.class);
		private  final String ALLCOMPANIES =  "SELECT * FROM company";
		private  final String FINDCOMPANYBYID = "SELECT * FROM company where id =";
		MapperCompanieDAO mcdao = new MapperCompanieDAO();
		public CompanieDAO() {};
		
		public List<Companie> getAllCompanies()  {
			List<Companie> toReturn = new ArrayList();
			try {
			ResultSet rs = BddConnection.login.createStatement().executeQuery(ALLCOMPANIES);
			  while(rs.next()) {
				  toReturn.add(mcdao.mapCompanie(rs));
				  }
			  return toReturn; } 
			catch (SQLException e) {
				logger.error("erreur lors de l'appel à la base pour les noms de compagnies");
				e.printStackTrace();
				return toReturn;
			  }	
		}
		
		public Companie findCompanyById (int id) {
			Companie d = new Companie();
			try {
				PreparedStatement preparedStatement = BddConnection.login.prepareStatement(FINDCOMPANYBYID+id,ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = preparedStatement.executeQuery();
			if(rs.first()) {
				return mcdao.mapCompanie(rs);
			} } 
			catch (SQLException e) {
				logger.error("erreur lors de l'appel à la base pour les noms de compagnies");
				e.printStackTrace();
				}
			return d;
		}
				
}