package Modele;

public class Companie {
	public int companieid;
	public String companiename;
	
	/* Setter et Getter*/
	
	public int getCompanieId() {
		return companieid;
	}
	
	public String getCompanieName() {
		return companiename;
	}
	
	public void setCompanieName(String companiename) {
		this.companiename= companiename;
	}
	
	public void setCompanieId(int companieid) {
		this.companieid=companieid;
	}
	
	/* constructeur*/ 
	
	public Companie() {
		
	}
}
