package com.excilys.formation.cdb.controllers;

import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.MapperComputer;
import com.excilys.formation.cdb.modele.Page;
import com.excilys.formation.cdb.service.ComputerService;

@RestController
@RequestMapping("/computer")
public class ComputerRESTController {
	public ComputerService computerService;
	@Autowired
	public ComputerRESTController(final ComputerService computerService) {
		this.computerService=computerService;
	}

	@GetMapping(value = { "/page" }, produces = "application/json")
	public List<ComputerDTO> listComputersPage(@RequestParam(required=false, name="nbByPage" ,defaultValue = "10") final String nbByPage,
			@RequestParam(required=false, name="currentPage",defaultValue = "1") final String currentPage,
			@RequestParam(required=false, name="colonne",defaultValue = "") final String colonne,
			@RequestParam(required=false, name="search",defaultValue = "") final String searchParam) { 

		final Page page = createCurrentPage(nbByPage,currentPage,colonne,searchParam);
		final List<ComputerDTO> complist = computerService.searchByName(searchParam, page);
		return complist;
	}

	@GetMapping(value = { "/number" }, produces = "application/json")
	public Integer numberComputers(@RequestParam(required=false, name="search",defaultValue = "") final String searchParam) {
		if(searchParam.isEmpty()) {
			return computerService.numberOfComputers();
		}
		else {
			return computerService.numberOfComputersBySearch(searchParam);
		}
	}

	@GetMapping(value ="/findComp/{id}", produces = "application/json")
	public ComputerDTO getComputer(@PathVariable final Integer id) {
		final ComputerDTO compdto= computerService.afficheListeComputerByID(id);
		return compdto;
	}

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public ResponseEntity<String> deleteComputer(@PathVariable final Integer id) {
		if(computerService.deleteByID(id) != 0) {
			return ResponseEntity.ok("{ok : true}");

		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Computer is not found is the database");
		}


	}

	@PostMapping(value = { "/create" }, produces = "application/json")
	public ResponseEntity<String> createComputer(@RequestBody final ComputerDTO dto) {

		if(dto != null){
			computerService.insertComputerwebUI(MapperComputer.toEntity(dto));
			return ResponseEntity.ok("{ok : true}");
		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{error : insertion failed");
		}
	}

	@PutMapping(value = { "/update/{id}" }, produces = "application/json")
	public ResponseEntity<String> updateComputer(@RequestBody final ComputerDTO dto) {
		try {
			if(computerService.updateAllwebUI(MapperComputer.toEntity(dto)) != null) {
				return ResponseEntity.ok("{ok : true}");

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Computer is not found is the database");
			}
		} catch (final IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{error : IllegalArgumentException");
		}catch (final DateTimeParseException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{error : DateTimeParseException");
		}
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
