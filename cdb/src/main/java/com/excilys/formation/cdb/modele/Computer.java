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
	
	
	
	
	public Computer() {
		
	}	
	/* Getter & Setter */
	
	public Computer(Integer computerid, String computername, LocalDate computerintroductedin,
			LocalDate computerdiscontinuedin, Integer computercompanieid) {
		super();
		this.computerid = computerid;
		this.computername = computername;
		this.computerintroductedin = computerintroductedin;
		this.computerdiscontinuedin = computerdiscontinuedin;
		this.computercompanieid = computercompanieid;
	}
	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((computercompanieid == null) ? 0 : computercompanieid.hashCode());
		result = prime * result + ((computerdiscontinuedin == null) ? 0 : computerdiscontinuedin.hashCode());
		result = prime * result + ((computerid == null) ? 0 : computerid.hashCode());
		result = prime * result + ((computerintroductedin == null) ? 0 : computerintroductedin.hashCode());
		result = prime * result + ((computername == null) ? 0 : computername.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (computercompanieid == null) {
			if (other.computercompanieid != null)
				return false;
		} else if (!computercompanieid.equals(other.computercompanieid))
			return false;
		if (computerdiscontinuedin == null) {
			if (other.computerdiscontinuedin != null)
				return false;
		} else if (!computerdiscontinuedin.equals(other.computerdiscontinuedin))
			return false;
		if (computerid == null) {
			if (other.computerid != null)
				return false;
		} else if (!computerid.equals(other.computerid))
			return false;
		if (computerintroductedin == null) {
			if (other.computerintroductedin != null)
				return false;
		} else if (!computerintroductedin.equals(other.computerintroductedin))
			return false;
		if (computername == null) {
			if (other.computername != null)
				return false;
		} else if (!computername.equals(other.computername))
			return false;
		return true;
	}

	public Computer(Integer computerid, String computername) {
		super();
		this.computerid = computerid;
		this.computername = computername;
	}

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
			if(computerintroductedin.compareTo(this.computerdiscontinuedin) <= 0) {
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
			if(computerdiscontinuedin.compareTo(this.computerintroductedin) >= 0) {
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