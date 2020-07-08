package com.excilys.formation.cdb.dto;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.mapper.MapperCompanieDTO;
import com.excilys.formation.cdb.modele.Companie;


public class CompanieDTO {
	private String id;
	private String name;
	MapperCompanieDTO cdto = new MapperCompanieDTO(); 
	
	public String getCompaniedtoid() {
		return id;
	}
	public void setCompaniedtoid(String companiedtoid) {
		this.id = companiedtoid;
	}
	public String getCompaniedtoname() {
		return name;
	}
	public void setCompaniedtoname(String companiedtoname) {
		this.name = companiedtoname;
	}
	
	public List<CompanieDTO> convertAllCompanie(List<Companie> c) {
		List<CompanieDTO> toReturn = new ArrayList<>();
		for ( Companie d : c ) {
			toReturn.add(cdto.convertToDtocompanie(d));
		}
		return toReturn;}
		
		public CompanieDTO convertOneCompanie (Companie c) {
			CompanieDTO objectdto = new CompanieDTO();
			objectdto = cdto.convertToDtocompanie(c);
			return objectdto;
		}
			
	
	public String toString() {
		return "Companie [ id=," +this.id+ " Nom de la compagnie : "+ this.name+ "\n";}
}
	