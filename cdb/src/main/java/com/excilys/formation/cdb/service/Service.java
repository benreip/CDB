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
	
	private static final Integer NB_ELEMENTS_BY_PAGE = 10;
	
	public List<ComputerDTO> afficheListeComputer(Integer page)  {
			Integer offset = (page-1)*NB_ELEMENTS_BY_PAGE;
		 return cdto.convertAllComputer(cdao.getComputers( offset, NB_ELEMENTS_BY_PAGE));
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
	
	public Integer getComputersNbPages() {
		Integer nbEntries = cdao.getNumberOfComputers();
		Integer nbPages = nbEntries/NB_ELEMENTS_BY_PAGE;
		return nbEntries%NB_ELEMENTS_BY_PAGE == 0?nbPages:nbPages+1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Service s= new Service();
	System.out.println(s.afficheListeComputer(1));

	}

}