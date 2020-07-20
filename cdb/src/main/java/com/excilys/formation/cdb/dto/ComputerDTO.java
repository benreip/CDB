package com.excilys.formation.cdb.dto;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.mapper.MapperComputer;
import com.excilys.formation.cdb.modele.Computer;

public class ComputerDTO {
	/* Va mapper les objets DAO vers des objets DTO pour le service */

	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private CompanieDTO company;
	MapperComputer mapping = new MapperComputer();


	public ComputerDTO() {

	}

	public void setComputerDtoName(final String name) {
		this.name=name;
	}

	public String getComputerDtoName() {
		return name;
	}


	public String getComputerdtoid() {
		return id;
	}

	public void setComputerdtoid(final String computerdtoid) {
		this.id = computerdtoid;
	}

	public String getComputerdtointroductedin() {
		return introduced;
	}

	public void setComputerdtointroductedin(final String computerdtointroductedin) {
		this.introduced = computerdtointroductedin;
	}

	public String getComputerdtodiscontinuedin() {
		return discontinued;
	}

	public void setComputerdtodiscontinuedin(final String computerdtodiscontinuedin) {
		this.discontinued = computerdtodiscontinuedin;
	}

	public CompanieDTO getCompany() {
		return company;
	}

	public void setCompany(final CompanieDTO company) {
		this.company = company;
	}

	public List<ComputerDTO> convertAllComputer(final List<Computer> c) {
		final List<ComputerDTO> toReturn = new ArrayList<>();
		for ( final Computer d : c ) {
			toReturn.add(mapping.toDto(d));
		}
		return toReturn;

	}

	public  ComputerDTO convertOneComputer (final Computer c) {
		ComputerDTO objectdto = new ComputerDTO();
		objectdto = mapping.toDto(c);
		return objectdto;
	}
	@Override
	public String toString() {

		return "Computer [ id=" + this.id + ",Name =  " + this.name + " ,Sorti en : " + this.introduced + ", Arrêté en :" + this.discontinued + ", Id de la compagnie : " + this.company+ "\n";
	}

}
