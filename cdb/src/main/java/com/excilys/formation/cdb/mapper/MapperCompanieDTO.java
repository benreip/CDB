package com.excilys.formation.cdb.mapper;



import com.excilys.formation.cdb.dto.CompanieDTO;
import com.excilys.formation.cdb.modele.Companie;

public class MapperCompanieDTO {
	public CompanieDTO convertToDtocompanie (Companie companie ) {
		CompanieDTO c = new CompanieDTO();
		c.setCompaniedtoname(companie.getCompanieName());
		c.setCompaniedtoid(Integer.toString(companie.getCompanieId()));
		return c;
	}
}
