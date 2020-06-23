package com.excilys.formation.cdb.modele;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Computer {
	private Integer computerid;
	private String computername;
	private LocalDate computerintroductedin;
	private LocalDate computerdiscontinuedin;
	private Integer computercompanieid;
	final Logger logger = LoggerFactory.getLogger(Computer.class);
	
	/* Getter & Setter */
	
	public Integer getComputerid() {
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
		if(this.computerdiscontinuedin != null) {
			if(computerintroductedin.compareTo(this.computerdiscontinuedin) < 0) {
				this.computerintroductedin = computerintroductedin;
			}
			else {
				throw new IllegalArgumentException("Introduced date must be lesser than discontinued");
			}
		}
		else {
			this.computerintroductedin = computerintroductedin;
		}
	}
	public LocalDate getComputerdiscontinuedin() {
		return computerdiscontinuedin;
	}
	public void setComputerdiscontinuedin(final LocalDate computerdiscontinuedin) {
		if(this.computerintroductedin != null) {
			if(computerdiscontinuedin.compareTo(this.computerintroductedin) > 0) {
				this.computerdiscontinuedin = computerdiscontinuedin;
			}
			else {
				throw new IllegalArgumentException("Introduced date must be lesser than discontinued");
			}
		}
		else {
			this.computerdiscontinuedin = computerdiscontinuedin;
		}
	}
	public Integer getComputercompanieid() {
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