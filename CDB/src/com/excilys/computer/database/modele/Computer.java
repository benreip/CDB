package com.excilys.computer.database.modele;
import java.time.LocalDate;

public class Computer {
	private int computerid;
	private String computername;
	private LocalDate computerintroductedin;
	private LocalDate computerdiscontinuedin;
	private Integer computercompanieid;
	
	/* Getter & Setter */
	
	public int getComputerid() {
		return computerid;
	}
	public void setComputerid(final int computerid) {
		this.computerid = computerid;
	}
	public String getComputername() {
		return computername;
	}
	public void setComputername(final String computername) {
		this.computername = computername;
	}
	public LocalDate getComputerintroductedin() {
		return computerintroductedin;
	}
	public void setComputerintroductedin(final LocalDate computerintroductedin) {
		this.computerintroductedin = computerintroductedin;
	}
	public LocalDate getComputerdiscontinuedin() {
		return computerdiscontinuedin;
	}
	public void setComputerdiscontinuedin(final LocalDate computerdiscontinuedin) {
		this.computerdiscontinuedin = computerdiscontinuedin;
	}
	public int getComputercompanieid() {
		return computercompanieid;
	}
	public void setComputercompanieid(final Integer computercompanieid) {
		this.computercompanieid = computercompanieid;
	}
	

	
	@Override
	public String toString() {
		return "Computer [ id=," + this.computerid + ",Name =  " + this.computername + " ,Sorti en : " + this.computerintroductedin + ", Arrêté en :" + this.computerdiscontinuedin + ", Id de la compagnie : " + this.computercompanieid+ "\n";
	}
	
	
}
