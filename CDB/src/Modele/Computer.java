package Modele;
import java.util.Date;

public class Computer {
	public int computerid;
	public String computername;
	public Date computerintroductedin;
	public Date computerdiscontinuedin;
	public int computercompanieid;
	
	/* Getter & Setter */
	
	public int getComputerid() {
		return computerid;
	}
	public void setComputerid(int computerid) {
		this.computerid = computerid;
	}
	public String getComputername() {
		return computername;
	}
	public void setComputername(String computername) {
		this.computername = computername;
	}
	public Date getComputerintroductedin() {
		return computerintroductedin;
	}
	public void setComputerintroductedin(Date computerintroductedin) {
		this.computerintroductedin = computerintroductedin;
	}
	public Date getComputerdiscontinuedin() {
		return computerdiscontinuedin;
	}
	public void setComputerdiscontinuedin(Date computerdiscontinuedin) {
		this.computerdiscontinuedin = computerdiscontinuedin;
	}
	public int getComputercompanieid() {
		return computercompanieid;
	}
	public void setComputercompanieid(int computercompanieid) {
		this.computercompanieid = computercompanieid;
	}
	

	/* Constructeur */
	
	public Computer() {
		
	}
	
	public String ToString() {
		return "Computer [ id=," + computerid + ",Name =  " + computername + " ,Sorti en : " + computerintroductedin + ", Arrêté en :" + computerdiscontinuedin + ", Id de la compagnie : " + computercompanieid;
	}
	
	
}
