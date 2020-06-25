package com.excilys.formation.cdb.mapper;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.modele.Computer;

public class MapperComputerDTO {

	
	public  ComputerDTO convertToDtocomputer (Computer d ) {
		ComputerDTO c = new ComputerDTO();
		c.setComputerDtoName(d.getComputername());
		c.setComputerdtoid(Integer.toString(d.getComputerid()));
		if (d.getComputerintroductedin()!=null) {
		c.setComputerdtointroductedin(d.getComputerintroductedin().toString());
		}
		if (d.getComputerdiscontinuedin()!=null) {
		c.setComputerdtodiscontinuedin(d.getComputerdiscontinuedin().toString());
		}
		if (d.getComputercompanieid()!=null) {
		c.setComputerdtocompanieid(Integer.toString(d.getComputercompanieid()));
		}
		return c;
	}
}
