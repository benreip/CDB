package com.excilys.formation.cdb.mapper;



import com.excilys.formation.cdb.dto.CompanieDTO;
import com.excilys.formation.cdb.modele.Companie;

public class MapperCompanieDTO {
	public static CompanieDTO convertToDtocompanie (final Companie companie ) {
		final CompanieDTO c = new CompanieDTO();
		c.setCompaniedtoname(companie.getCompanieName());
		c.setCompaniedtoid(Integer.toString(companie.getCompanieId()));
		return c;
	}

	public static Companie convertDtoTocompanie (final CompanieDTO companie ) {
		final Companie c = new Companie();
		c.setCompanieName(companie.getCompaniedtoname());
		c.setCompanieId(Integer.parseInt(companie.getCompaniedtoid()));
		return c;
	}
}