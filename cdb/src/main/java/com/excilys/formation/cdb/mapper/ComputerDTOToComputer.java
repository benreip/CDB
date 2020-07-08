package com.excilys.formation.cdb.mapper;

import java.time.LocalDate;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.modele.Computer;

public class ComputerDTOToComputer {
	public  Computer convertDtoToComputer (ComputerDTO d ) {
		Computer c = new Computer();
		c.setId(Integer.parseInt(d.getComputerdtoid()));
		c.setName(d.getComputerDtoName());
		if (d.getComputerdtointroductedin()!=null) {
		c.setIntroduced(LocalDate.parse(d.getComputerdtointroductedin()));
		}
		if (d.getComputerdtodiscontinuedin()!=null) {
		c.setDiscontinued(LocalDate.parse(d.getComputerdtodiscontinuedin()));
		}
		if (d.getComputerdtocompanieid()!=null) {
		c.setIdcompany(Integer.parseInt(d.getComputerdtocompanieid()));
		}
		return c;
	}

}
