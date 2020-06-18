package com.excilys.computer.database.dto;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computer.database.modele.Companie;
import com.excilys.computer.database.persistence.ComputerDAO;
public class CompanieDTO {
	private int companiedtoid;
	private String companiedtoname;
	
	
	public int getCompaniedtoid() {
		return companiedtoid;
	}
	public void setCompaniedtoid(int companiedtoid) {
		this.companiedtoid = companiedtoid;
	}
	public String getCompaniedtoname() {
		return companiedtoname;
	}
	public void setCompaniedtoname(String companiedtoname) {
		this.companiedtoname = companiedtoname;
	}
	
	public CompanieDTO convertToDtocompanie (Companie companie ) {
		CompanieDTO c = new CompanieDTO();
		c.setCompaniedtoname(companie.getCompanieName());
		c.setCompaniedtoid(companie.getCompanieId());
		return c;
	}
	
	public List<CompanieDTO> convertAllCompanie(List<Companie> c) {
		List<CompanieDTO> toReturn = new ArrayList<>();
		for ( Companie d : c ) {
			toReturn.add(convertToDtocompanie(d));
		}
		return toReturn;
			
	}}
	
