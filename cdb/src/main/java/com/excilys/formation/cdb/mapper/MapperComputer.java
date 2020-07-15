package com.excilys.formation.cdb.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.modele.Computer;

public class MapperComputer {
	final static Logger logger = LoggerFactory.getLogger(MapperComputer.class);
	
	


	public  ComputerDTO toDto (Computer d ) {
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
	
	
		public  Computer toEntity (ComputerDTO d ) {
			Computer c = new Computer();
			if(d.getComputerdtoid()!= null) {
			c.setId(Integer.parseInt(d.getComputerdtoid()));
			}
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


