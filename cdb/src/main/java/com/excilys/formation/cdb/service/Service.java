package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.dto.CompanieDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.modele.Companie;
import com.excilys.formation.cdb.modele.Computer;
import com.excilys.formation.cdb.persistence.CompanieDAO;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public class Service {
	ComputerDTO cdto = new ComputerDTO();
	ComputerDAO cdao = new ComputerDAO();
	CompanieDAO compdao = new CompanieDAO();
	CompanieDTO compdto = new CompanieDTO();
	Computer c = new Computer();
	Companie cp = new Companie();
	public Service() {}
	
	
	public List<ComputerDTO> afficheListeComputer(int pc, int pas)  {
		 return cdto.convertAllComputer(cdao.getComputers( pc, pas));
	}
	
	public List<CompanieDTO> afficheListeCompanie()  {
		return compdto.convertAllCompanie(compdao.getAllCompanies());
	
	}	
	
	public CompanieDTO afficheListeCompanieByID(int a) {
		return compdto.convertOneCompanie(compdao.findCompanyById(a));
	
	
	}
	
	public ComputerDTO afficheListeComputerByID(int a) {
		return cdto.convertOneComputer(cdao.findComputerById(a));
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
	
	
	public ComputerDTO updateFabricant(int id, int idcomp) {
			return cdto.convertOneComputer(cdao.updateFabricant(id, idcomp));
	}

	public int  insertcomputer (String computername) {
			cdao.insertComputer(computername);
			return 1;
	}
	
	public int numberOfComputers() {
		return cdao.getNumberOfComputers();
	}
	
	

}