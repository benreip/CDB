package com.excilys.computer.database.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.excilys.computer.database.modele.Computer;


public class ComputerDTO {
			/* Va mapper les objets DAO vers des objets DTO pour le service */
	
		private int computerdtoid;
		private String computerdtoname;
		private Date computerdtointroductedin;
		private Date computerdtodiscontinuedin;
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

		public Date getComputerdtointroductedin() {
			return computerdtointroductedin;
		}

		public void setComputerdtointroductedin(Date computerdtointroductedin) {
			this.computerdtointroductedin = computerdtointroductedin;
		}

		public Date getComputerdtodiscontinuedin() {
			return computerdtodiscontinuedin;
		}

		public void setComputerdtodiscontinuedin(Date computerdtodiscontinuedin) {
			this.computerdtodiscontinuedin = computerdtodiscontinuedin;
		}

		public Integer getComputerdtocompanieid() {
			return computerdtocompanieid;
		}

		public void setComputerdtocompanieid(Integer computerdtocompanieid) {
			this.computerdtocompanieid = computerdtocompanieid;
		}
		
		public ComputerDTO convertToDto (Computer computer ) {
			ComputerDTO c = new ComputerDTO();
			c.setComputerDtoName(computer.getComputername());
			c.setComputerdtoid(computer.getComputerid());
			c.setComputerdtointroductedin(computer.getComputerintroductedin());
			c.setComputerdtodiscontinuedin(computer.getComputerdiscontinuedin());
			c.setComputerdtocompanieid(computer.getComputercompanieid());
			return c;
		}
		
		public List<ComputerDTO> convertAll(List<Computer> c) {
			List<ComputerDTO> toReturn = new ArrayList<>();
			for ( Computer d : c ) {
				toReturn.add(convertToDto(d));
			}
			return toReturn;
				
		}
}
