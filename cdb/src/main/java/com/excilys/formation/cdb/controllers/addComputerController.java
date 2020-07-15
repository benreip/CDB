package com.excilys.formation.cdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.cdb.dto.CompanieDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
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
		return "addComputer";
	}

	@PostMapping
	public String addComputer(@ModelAttribute("ComputerDTO") final ComputerDTO cdto,
			final ModelMap model) {
		model.addAttribute("computerName",cdto.getComputerDtoName());
		model.addAttribute("introduced",cdto.getComputerdtointroductedin());
		model.addAttribute("discontinued",cdto.getComputerdtodiscontinuedin());
		return "addComputer";
	}
}
