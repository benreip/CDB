package com.excilys.formation.cdb.modele;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Computer {
	private Integer id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Integer idcompany;
	final Logger logger = LoggerFactory.getLogger(Computer.class);
	
	public Computer() {
		
	}

	public Computer(Integer id, String name, LocalDate introduced, LocalDate discontinued, Integer idcompany) {
		this.id=id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.idcompany = idcompany;
	}	
	/* Getter & Setter */
	
	public Computer(String computername, LocalDate computerintroductedin,
			LocalDate computerdiscontinuedin, Integer computercompanieid) {
		this.name = computername;
		this.introduced = computerintroductedin;
		this.discontinued = computerdiscontinuedin;
		this.idcompany = computercompanieid;
	}
	

	public Computer (Integer id,String name ) {
		this.id=id;
		this.name=name;
	}

	

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		if(this.discontinued != null) {
			if(introduced.compareTo(this.discontinued) <= 0) {
				this.introduced = introduced;
			}
			else {
				throw new IllegalArgumentException("Introduced date must be lesser than discontinued");
			}
		}
		else {
			this.introduced = introduced;
		}
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		if(this.introduced != null) {
			if(this.introduced.compareTo(discontinued) <= 0) {
				this.discontinued = discontinued;
			}
			else {
				throw new IllegalArgumentException("Discontinued date must be greater than introduced");
			}
		}
		else {
			this.discontinued = discontinued;
		}
	}

	public Integer getIdcompany() {
		return idcompany;
	}

	public void setIdcompany(Integer idcompany) {
		this.idcompany = idcompany;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idcompany == null) ? 0 : idcompany.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idcompany == null) {
			if (other.idcompany != null)
				return false;
		} else if (!idcompany.equals(other.idcompany))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Computer [ id=," + this.id + ",Name =  " + this.name + " ,Sorti en : " + this.introduced + ", Arrêté en :" + this.discontinued + ", Id de la compagnie : " + this.idcompany+ "\n";
	}
	
	
}