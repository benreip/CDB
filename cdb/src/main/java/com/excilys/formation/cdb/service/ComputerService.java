package com.excilys.formation.cdb.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.dto.CompanieDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.MapperComputer;
import com.excilys.formation.cdb.modele.Computer;
import com.excilys.formation.cdb.modele.Page;
import com.excilys.formation.cdb.persistence.CompanieDAO;
import com.excilys.formation.cdb.persistence.ComputerDAO;


@Service
public class ComputerService {
	ComputerDTO cdto = new ComputerDTO();
	@Autowired
	ComputerDAO cdao;
	@Autowired
	CompanieDAO compdao;
	CompanieDTO compdto = new CompanieDTO();
	MapperComputer mapping= new MapperComputer();

	public ComputerService() {}


	public List<ComputerDTO> afficheListeComputer(final Page page)  {
		final Integer offset = (page.getCurrentPage()-1)*page.getNb_entries_per_page();
		return cdto.convertAllComputer(cdao.galageget( offset, page.getNb_entries_per_page()));
	}

	public List<CompanieDTO> afficheListeCompanie()  {
		return compdto.convertAllCompanie(compdao.getAllCompanies());

	}	

	public int numberOfComputersBySearch (final String research) {
		return cdao.getNumberOfComputersBySearch(research);
	}

	public List<ComputerDTO> searchByName(final String research, final Page page) {
		return cdto.convertAllComputer(cdao.searchByName(research,page));
	}

	public CompanieDTO afficheListeCompanieByID(final int a) {
		return compdto.convertOneCompanie(compdao.findCompanyById(a));


	}

	public ComputerDTO afficheListeComputerByID(final int a) {
		return cdto.convertOneComputer(cdao.findComputerById(a));
	} 


	public int deleteByID(final int id) {
		return cdao.deleteComputerById(id);

	}


	public int numberOfComputers() {
		return cdao.getNumberOfComputers();
	}

	public Integer getComputersNbPages(final Page page) {
		final Integer nbEntries = cdao.getNumberOfComputers();
		final Integer nbPages = nbEntries/page.getNb_entries_per_page();
		return nbEntries%page.getNb_entries_per_page() == 0?nbPages:nbPages+1;
	}

	public Integer getComputersNbPagesSearch(final Page page, final String search) {
		final Integer nbEntries = cdao.getNumberOfComputersBySearch(search);
		final Integer nbPages = nbEntries/page.getNb_entries_per_page();
		return nbEntries%page.getNb_entries_per_page() == 0?nbPages:nbPages+1;
	}

	public List<ComputerDTO> displayComputerOrderBy(final String colonne,final String ascending,final Integer page,final Integer nb_entries_per_page) {
		final Integer offset = (page-1)*nb_entries_per_page;
		return cdto.convertAllComputer(cdao.getComputersOrderBy(colonne, ascending, offset, nb_entries_per_page));
	}

	public Computer mappingDtoToComputer(final ComputerDTO compdto) {
		return mapping.toEntity(compdto);
	}

	public Computer updateAllwebUI (final Computer c) {
		return cdao.updateAll(c);
	}

	public Computer insertComputerwebUI (final Computer c) {
		return cdao.create(c);
	}
	public static void main(final String[] args) {
		final ComputerService s = new ComputerService();
		System.out.println(s.afficheListeCompanie());
	}

}