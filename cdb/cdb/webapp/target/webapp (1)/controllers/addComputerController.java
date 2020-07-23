package com.excilys.formation.cdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.cdb.dto.CompanieDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.modele.Computer;
import com.excilys.formation.cdb.service.ComputerService;

@Controller
@RequestMapping("/addComputer")
public class addComputerController {
	@Autowired
	private ComputerService service;

	@GetMapping
	public String displayAddComputer (final Model model) {
		final List<CompanieDTO> cdtos = service.afficheListeCompanie();
		model.addAttribute("companies",cdtos);
		model.addAttribute("cdto",new ComputerDTO());
		return "addComputer";
	}

	@PostMapping
	public String addComputer(@ModelAttribute("cdto") final ComputerDTO cdto,
			final Model model) {
		try {
			final Computer comptoadd = service.mappingDtoToComputer(cdto);
			service.insertComputerwebUI(comptoadd);
			final String success = "Computer " + comptoadd.getName() + " was succesfully added";
			model.addAttribute("success",success);
		} catch (final IllegalArgumentException e) {
			model.addAttribute("error",e.getMessage());
			model.addAttribute("newComputer",cdto);
		}
		model.addAttribute("computerName",cdto.getComputerDtoName());
		model.addAttribute("introduced",cdto.getComputerdtointroductedin());
		model.addAttribute("discontinued",cdto.getComputerdtodiscontinuedin());
		return "addComputer";
	}
}
