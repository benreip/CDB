package com.excilys.formation.cdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.dto.CompanieDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.modele.Computer;
import com.excilys.formation.cdb.service.ComputerService;

@Controller
@RequestMapping("/editComputer")
public class editController {
	@Autowired
	private ComputerService service;

	@GetMapping
	public String displayEditComputer(@RequestParam(required=false,name="id") final String id, final Model model)
	{
		final List<CompanieDTO> cdtos = service.afficheListeCompanie();
		model.addAttribute("companies",cdtos);
		String error;
		if (id != null) {
			try {
				final ComputerDTO displaycomp = service.afficheListeComputerByID(Integer.parseInt(id));
				model.addAttribute("computer",displaycomp);
				model.addAttribute("cdto",new ComputerDTO());
			} catch (final NumberFormatException e) {
				error = "id is not a correct id";
				model.addAttribute("error",error);
			}
		}
		return "editComputer";
	}

	@PostMapping
	public String editComputer(@RequestParam(required=false,name="id") final String id,@ModelAttribute("cdto") final ComputerDTO cdto, 
			final Model model){
		try {
			final Computer comptoedit = service.mappingDtoToComputer(cdto);
			comptoedit.setId(Integer.parseInt(id));
			service.updateAllwebUI(comptoedit);
			final String success = "Computer " + comptoedit.getName() + " was succesfully edited";
			model.addAttribute("success",success);
		} catch (final IllegalArgumentException e) {
			model.addAttribute("error",e.getMessage());
			model.addAttribute("newComputer",cdto);
		}
		return "editComputer";
	}

}
