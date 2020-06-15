package Modele;

public class Companie {
	private int companieid;
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
	
	
}
