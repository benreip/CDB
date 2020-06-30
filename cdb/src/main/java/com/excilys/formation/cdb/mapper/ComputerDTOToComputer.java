package com.excilys.formation.cdb.mapper;

import java.time.LocalDate;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.modele.Computer;

public class ComputerDTOToComputer {
	public  Computer convertDtoToComputer (ComputerDTO d ) {
		Computer c = new Computer();
		c.setComputername(d.getComputerDtoName());
		c.setComputercompanieid(Integer.parseInt(d.getComputerdtocompanieid()));
		if (d.getComputerdtointroductedin()!=null) {
		c.setComputerintroductedin(LocalDate.parse(d.getComputerdtointroductedin()));
		}
		if (d.getComputerdtodiscontinuedin()!=null) {
		c.setComputerdiscontinuedin(LocalDate.parse(d.getComputerdtodiscontinuedin()));
		}
		if (d.getComputerdtocompanieid()!=null) {
		c.setComputercompanieid(Integer.parseInt(d.getComputerdtocompanieid()));
		}
		return c;
	}

}
