package com.excilys.formation.cdb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.cdb.dto.CompanieDTO;
import com.excilys.formation.cdb.service.ComputerService;

@RestController
@RequestMapping("/companies")
public class CompanieRESTController {
	@Autowired
	private ComputerService service;

	@RequestMapping(method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	List<CompanieDTO> getCompanies(){
		List<CompanieDTO> companieList = new ArrayList();
		companieList = service.afficheListeCompanie();
		return companieList;
	}
}
