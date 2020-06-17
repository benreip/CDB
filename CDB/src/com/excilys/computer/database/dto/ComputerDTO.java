package com.excilys.computer.database.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computer.database.modele.Computer;

public class ComputerDTO {
			/* Va mapper les objets DAO vers des objets DTO pour le service */
	
		private int computerdtoid;
		private String computerdtoname;
		private LocalDate computerdtointroductedin;
		private LocalDate computerdtodiscontinuedin;
		private Integer computerdtocompanieid;
	
	
		public ComputerDTO() {
			
		}
		
		public void setComputerDtoName(String name) {
			this.computerdtoname=name;
		}
		
		public String setComputerDtoName() {
			return computerdtoname;
		}
		

		public int getComputerdtoid() {
			return computerdtoid;
		}

		public void setComputerdtoid(int computerdtoid) {
			this.computerdtoid = computerdtoid;
		}

		public LocalDate getComputerdtointroductedin() {
			return computerdtointroductedin;
		}

		public void setComputerdtointroductedin(LocalDate computerdtointroductedin) {
			this.computerdtointroductedin = computerdtointroductedin;
		}

		public LocalDate getComputerdtodiscontinuedin() {
			return computerdtodiscontinuedin;
		}

		public void setComputerdtodiscontinuedin(LocalDate computerdtodiscontinuedin) {
			this.computerdtodiscontinuedin = computerdtodiscontinuedin;
		}

		public Integer getComputerdtocompanieid() {
			return computerdtocompanieid;
		}

		public void setComputerdtocompanieid(Integer computerdtocompanieid) {
			this.computerdtocompanieid = computerdtocompanieid;
		}
		
		public  ComputerDTO convertToDtocomputer (Computer computer ) {
			ComputerDTO c = new ComputerDTO();
			c.setComputerDtoName(computer.getComputername());
			c.setComputerdtoid(computer.getComputerid());
			if (computer.getComputerintroductedin()!=null) {
			c.setComputerdtointroductedin(computer.getComputerintroductedin());
			}
			if (computer.getComputerdiscontinuedin()!=null) {
			c.setComputerdtodiscontinuedin(computer.getComputerdiscontinuedin());
			}
			c.setComputerdtocompanieid(computer.getComputercompanieid());
			return c;
		}
		
		public  List<ComputerDTO> convertAllComputer(List<Computer> c) {
			List<ComputerDTO> toReturn = new ArrayList<>();
			for ( Computer d : c ) {
				toReturn.add(convertToDtocomputer(d));
			}
			return toReturn;
				
		}
}
