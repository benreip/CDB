package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.dto.CompanieDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerDTOToComputer;
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
	ComputerDTOToComputer mapping = new ComputerDTOToComputer();
	public Service() {}
	
	
	public List<ComputerDTO> afficheListeComputer(Integer page,Integer nb_entries_per_page)  {
			Integer offset = (page-1)*nb_entries_per_page;
		 return cdto.convertAllComputer(cdao.getComputers( offset, nb_entries_per_page));
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
	
	public Integer getComputersNbPages(Integer nb_entries_per_page) {
		Integer nbEntries = cdao.getNumberOfComputers();
		Integer nbPages = nbEntries/nb_entries_per_page;
		return nbEntries%nb_entries_per_page == 0?nbPages:nbPages+1;
	}
	
	public Computer mappingDtoToComputer(ComputerDTO compdto) {
		return mapping.convertDtoToComputer(compdto);
	}
	
	public Computer updateAllwebUI (Computer c) {
		return cdao.updateAll(c);
	}
	
	public Computer insertComputerwebUI (Computer c) {
		return cdao.create(c);
	}

}