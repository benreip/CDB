package com.excilys.formation.cdb.mapper;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.modele.Computer;

public class MapperComputer {
	final static Logger logger = LoggerFactory.getLogger(MapperComputer.class);




	public  ComputerDTO toDto (final Computer d ) {
		final ComputerDTO c = new ComputerDTO();
		c.setComputerDtoName(d.getName());
		c.setComputerdtoid(Integer.toString(d.getId()));
		if (d.getIntroduced()!=null) {
			c.setComputerdtointroductedin(d.getIntroduced().toString());
		}
		if (d.getDiscontinued()!=null) {
			c.setComputerdtodiscontinuedin(d.getDiscontinued().toString());
		}
		if (d.getCompany()!=null) {
			c.setCompany(MapperCompanieDTO.convertToDtocompanie(d.getCompany()));
		}
		return c;
	}


	public static  Computer toEntity (final ComputerDTO d ) {
		final Computer c = new Computer();
		if(d.getComputerdtoid()!= null) {
			c.setId(Integer.parseInt(d.getComputerdtoid()));
		}
		c.setName(d.getComputerDtoName());
		if (d.getComputerdtointroductedin()!=null && !d.getComputerdtointroductedin().equals("")) {
			c.setIntroduced(LocalDate.parse(d.getComputerdtointroductedin()));
		}
		if (d.getComputerdtodiscontinuedin()!=null && !d.getComputerdtodiscontinuedin().contentEquals(""))  {
			c.setDiscontinued(LocalDate.parse(d.getComputerdtodiscontinuedin()));
		}
		if (d.getCompany()!=null && !d.getCompany().getCompaniedtoid().contentEquals("")) {
			c.setCompany(MapperCompanieDTO.convertDtoTocompanie(d.getCompany()));

		}
		return c;

	}

}
