package com.excilys.computer.database.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.excilys.computer.database.dto.*;
import com.excilys.computer.database.modele.Companie;
import com.excilys.computer.database.modele.Computer;
import com.excilys.computer.database.persistence.CompanieDAO;
import com.excilys.computer.database.persistence.ComputerDAO;
import com.excilys.computer.database.ui.User;

public class Service {
	ComputerDTO cdto = new ComputerDTO();
	ComputerDAO cdao = new ComputerDAO();
	CompanieDAO compdao = new CompanieDAO();
	CompanieDTO compdto = new CompanieDTO();
	Computer c = new Computer();
	Companie cp = new Companie();
	public Service() {}
	
	
	public List<Computer> afficheListeComputer()  {
		 return cdao.getComputers();
	}
	
	public List<Companie> afficheListeCompanie()  {
		return compdao.getAllCompanies();
	
	}	
	
	public Companie afficheListeCompanieByID(int a) {
		return compdao.findCompanyById(a);
	
	
	}
	
	public Computer afficheListeComputerByID(int a) {
		return cdao.findComputerById(a);
	} 
	
	public int updateName(int id, String name)  {
				 return cdao.updateName(id,name);

			}	
	
	public int deleteByID(int id) {
			return cdao.deleteComputerById(id);
		
	}
	
	public int updateDateSortie(int id, String date) {
			return cdao.updateDateSortie(id, date);
		
	}
	
	public int updateDateFIn(int id, String date) {
			return cdao.updateDateFin(id, date);
	}
	
	
	public Computer updateFabricant(int id, int idcomp) {
			return cdao.updateFabricant(id, idcomp);
	}

	public int  insertcomputer (String computername) {
			cdao.insertComputer(computername);
			return 1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Service s = new Service();
		s.afficheListeComputer();

	}

}