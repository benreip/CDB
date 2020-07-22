package com.excilys.formation.cdb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.modele.Page;
import com.excilys.formation.cdb.service.ComputerService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private ComputerService service;
	private Page page;

	@GetMapping
	public String displayComputers(@RequestParam(required=false, name="colonne") final String colonne,
			@RequestParam(required=false,name = "ascending") final String ascending,
			@RequestParam(required=false,name = "search") final String searchReq,
			@RequestParam(required=false,name = "nbByPage") final String nb_entries_per_page,
			@RequestParam(required = false, name ="page") final  String currentPage,
			final Model modele){
		List<ComputerDTO> cdtos = new ArrayList<>();
		page = createCurrentPage(nb_entries_per_page,currentPage,
				colonne,ascending);
		String search ="";
		if (searchReq != null) {
			search = searchReq;
			cdtos = service.searchByName(search,page);
			modele.addAttribute("nbComputers", service.numberOfComputersBySearch(search));
			modele.addAttribute("control_page",service.getComputersNbPagesSearch(page,search));
		} else {
			cdtos = service.afficheListeComputer(page);
			modele.addAttribute("control_page",service.getComputersNbPages(page));
			modele.addAttribute("nbComputers",service.numberOfComputers());
		}
		modele.addAttribute("search",search);
		modele.addAttribute("page",page);
		modele.addAttribute("computers",cdtos);
		return "dashboard";

	}


	@PostMapping
	public String deleteComputers(@RequestParam(name="selection") final String selection) {
		if (selection != null && !selection.equals("")) {
			final String comptodel = selection;
			final List<Integer> delete = Stream.of(comptodel.split(","))
					.map(Integer::parseInt)
					.collect(Collectors.toList());
			delete.stream().forEach(id->service.deleteByID(id));
		}
		return "redirect:/dashboard";
	}

	public Page createCurrentPage (final String nbByPage, final String currentPage, final String colonne, final String ascending) {
		final Page page = new Page();
		page.setCurrentPage(1);
		page.setNb_entries_per_page(10);
		page.setColonne("id");
		page.setAscending("ASC");

		if (nbByPage!=null) {
			page.setNb_entries_per_page(Integer.parseInt(nbByPage));
		}

		if (colonne!=null) {
			page.setColonne(colonne);
		}

		if(ascending!=null) {
			page.setAscending(ascending);
		}

		if (currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage));
		}
		return page;
	}

}
