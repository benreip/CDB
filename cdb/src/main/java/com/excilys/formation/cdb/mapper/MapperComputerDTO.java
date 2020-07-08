package com.excilys.formation.cdb.mapper;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.modele.Computer;

public class MapperComputerDTO {

	
	public  ComputerDTO convertToDtocomputer (Computer d ) {
		ComputerDTO c = new ComputerDTO();
		c.setComputerDtoName(d.getName());
		c.setComputerdtoid(Integer.toString(d.getId()));
		if (d.getIntroduced()!=null) {
		c.setComputerdtointroductedin(d.getIntroduced().toString());
		}
		if (d.getDiscontinued()!=null) {
		c.setComputerdtodiscontinuedin(d.getDiscontinued().toString());
		}
		if (d.getIdcompany()!=null) {
		c.setComputerdtocompanieid(Integer.toString(d.getIdcompany()));
		}
		return c;
	}
}
