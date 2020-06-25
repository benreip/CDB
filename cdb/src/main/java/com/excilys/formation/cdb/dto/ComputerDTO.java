package com.excilys.formation.cdb.dto;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.mapper.MapperComputerDTO;
import com.excilys.formation.cdb.modele.Computer;

public class ComputerDTO {
			/* Va mapper les objets DAO vers des objets DTO pour le service */
	
		private String computerdtoid;
		private String computerdtoname;
		private String computerdtointroductedin;
		private String computerdtodiscontinuedin;
		private String computerdtocompanieid;
		MapperComputerDTO cdto = new MapperComputerDTO();
	
	
		public ComputerDTO() {
			
		}
		
		public void setComputerDtoName(String name) {
			this.computerdtoname=name;
		}
		
		public String getComputerDtoName() {
			return computerdtoname;
		}
		

		public String getComputerdtoid() {
			return computerdtoid;
		}

		public void setComputerdtoid(String computerdtoid) {
			this.computerdtoid = computerdtoid;
		}

		public String getComputerdtointroductedin() {
			return computerdtointroductedin;
		}

		public void setComputerdtointroductedin(String computerdtointroductedin) {
			this.computerdtointroductedin = computerdtointroductedin;
		}

		public String getComputerdtodiscontinuedin() {
			return computerdtodiscontinuedin;
		}

		public void setComputerdtodiscontinuedin(String computerdtodiscontinuedin) {
			this.computerdtodiscontinuedin = computerdtodiscontinuedin;
		}

		public String getComputerdtocompanieid() {
			return computerdtocompanieid;
		}

		public void setComputerdtocompanieid(String computerdtocompanieid) {
			this.computerdtocompanieid = computerdtocompanieid;
		}
		
		public List<ComputerDTO> convertAllComputer(List<Computer> c) {
			List<ComputerDTO> toReturn = new ArrayList<>();
			for ( Computer d : c ) {
				toReturn.add(cdto.convertToDtocomputer(d));
			}
			return toReturn;
				
		}
		
		public  ComputerDTO convertOneComputer (Computer c) {
			ComputerDTO objectdto = new ComputerDTO();
			objectdto = cdto.convertToDtocomputer(c);
			return objectdto;
		}
		@Override
		public String toString() {
			return "Computer [ id=," + this.computerdtoid + ",Name =  " + this.computerdtoname + " ,Sorti en : " + this.computerdtointroductedin + ", Arrêté en :" + this.computerdtodiscontinuedin + ", Id de la compagnie : " + this.computerdtocompanieid+ "\n";
		}
		
}
