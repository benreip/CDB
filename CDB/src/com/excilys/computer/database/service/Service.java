package com.excilys.computer.database.service;

import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.computer.database.dto.*;
import com.excilys.computer.database.persistence.CompanieDAO;
import com.excilys.computer.database.persistence.ComputerDAO;

public class Service {
	ComputerDTO cdto = new ComputerDTO();
	ComputerDAO cdao = new ComputerDAO();
	CompanieDAO compdao = new CompanieDAO();
	CompanieDTO compdto = new CompanieDTO();
	public Service() {}
	
	
	public void afficheListeComputer()  {
		 
			System.out.println(cdto.convertAllComputer(cdao.getComputers()));
	}
	
	public void afficheListeCompanie()  {
		try {System.out.println(compdto.convertAllCompanie(compdao.getAllCompanies()));
	}
		catch (SQLException e) {System.out.println("erreur ");}	}		
	
	public void afficheListeCompanieByID(int a) {
		try {System.out.println(compdto.convertToDtocompanie(compdao.findCompanyById(a)));
		
		}  catch (SQLException e) {System.out.println("erreur ");}
	}
	
	public void afficheListeComputerByID(int a) {
		System.out.println(cdto.convertToDtocomputer(cdao.findComputerById(a)));
		
		} 
	
	public void updateName(int id, String name)  {
			try {
				cdao.updateName(id,name);
				System.out.println("update réalisé avec succès");
			} catch(SQLException e) {
				System.out.println("erreur update");
			}	
	}		
	public void deleteByID(int id) {
		try {
			cdao.deleteComputerById(id);
			System.out.println("Delete réalisé avec succès");
		} catch(SQLException e) {
			System.out.println("erreur sur le delete");
		}
	}
	
	public void updateDateSortie(int id, LocalDate date) {
		try  {
			cdao.updateDateSortie(id, date);
		
		System.out.println("Update réalisé avec succès");
		} catch (SQLException e) { System.out.println("erreur lors de l'update de la date de sortie");}
	}
	
	public void updateDateFIn(int id, LocalDate date) {
		try  {
			cdao.updateDateFin(id, date);
		
		System.out.println("Update réalisé avec succès");
		} catch (SQLException e) { System.out.println("erreur lors de l'update de la date de fin");}
	}
	
	
	public void updateFabricant(int id, int idcomp) {
		
		try  {
			cdao.updateFabricant(id, idcomp);
		
		System.out.println("Update réalisé avec succès");
		} catch (SQLException e) { System.out.println("erreur lors de l'update de la date de sortie");}
	}

	public void  insertcomputer (String computername) {
		try  {
			cdao.insertComputer(computername);
		
		System.out.println("Update réalisé avec succès");
		} catch (SQLException e) { System.out.println("erreur lors de l'insert");}
	}

}