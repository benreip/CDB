package com.excilys.formation.cdb.modele;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="computer")
public class Computer {
	@Id
	private Integer id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	@ManyToOne
	private Companie company;

	public Computer() {

	}

	public Computer(final Integer id, final String name, final LocalDate introduced, final LocalDate discontinued, final Companie company) {
		this.id=id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}	
	/* Getter & Setter */

	public Computer(final String computername, final LocalDate computerintroductedin,
			final LocalDate computerdiscontinuedin, final Companie company) {
		this.name = computername;
		this.introduced = computerintroductedin;
		this.discontinued = computerdiscontinuedin;
		this.company = company;
	}


	public Computer (final Integer id,final String name ) {
		this.id=id;
		this.name=name;
	}




	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(final LocalDate introduced) {
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

	public void setDiscontinued(final LocalDate discontinued) {
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

	public Companie getCompany() {
		return company;
	}

	public void setCompany(final Companie company) {
		this.company = company;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Computer other = (Computer) obj;
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
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
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
		String companyId= null;
		if(company != null) {
			companyId = String.valueOf(company.getCompanieId());
		}
		return "Computer [ id=," + this.id + ",Name =  " + this.name + " ,Sorti en : " + this.introduced + ", Arrêté en :" + this.discontinued + ", Id de la compagnie : " + companyId+ "\n";
	}


}