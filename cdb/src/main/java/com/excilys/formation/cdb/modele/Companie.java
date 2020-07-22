package com.excilys.formation.cdb.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="company")
public class Companie {
	@Id
	@Column(name="id")
	private Integer companieid;
	@Column(name="name")
	private String companiename;


	/* Setter et Getter*/

	public int getCompanieId() {
		return companieid;
	}

	public String getCompanieName() {
		return companiename;
	}

	public void setCompanieName(final String companiename) {
		this.companiename= companiename;
	}

	public void setCompanieId(final int companieid) {
		this.companieid=companieid;
	}

	public Companie(final Integer companieid, final String companiename) {
		super();
		this.companieid = companieid;
		this.companiename = companiename;
	}

	public Companie() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companieid == null) ? 0 : companieid.hashCode());
		result = prime * result + ((companiename == null) ? 0 : companiename.hashCode());
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
		final Companie other = (Companie) obj;
		if (companieid == null) {
			if (other.companieid != null)
				return false;
		} else if (!companieid.equals(other.companieid))
			return false;
		if (companiename == null) {
			if (other.companiename != null)
				return false;
		} else if (!companiename.equals(other.companiename))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Companie [ id=," +this.companieid+ " Nom de la compagnie : "+ this.companiename+ "\n";

	}
}