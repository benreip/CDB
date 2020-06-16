package com.excilys.computer.database.modele;
import java.util.Date;

public class Computer {
	private int computerid;
	private String computername;
	private Date computerintroductedin;
	private Date computerdiscontinuedin;
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
	public Date getComputerintroductedin() {
		return computerintroductedin;
	}
	public void setComputerintroductedin(final Date computerintroductedin) {
		this.computerintroductedin = computerintroductedin;
	}
	public Date getComputerdiscontinuedin() {
		return computerdiscontinuedin;
	}
	public void setComputerdiscontinuedin(final Date computerdiscontinuedin) {
		this.computerdiscontinuedin = computerdiscontinuedin;
	}
	public int getComputercompanieid() {
		return computercompanieid;
	}
	public void setComputercompanieid(final Integer computercompanieid) {
		this.computercompanieid = computercompanieid;
	}
	
	/*public Computer(int computerid,String computername,Date computerintroductedin,Date computerdiscontinuedin,int computercompanieid) {
		computerintroductedin computer
		
	}*/

	
	
	public String ToString() {
		return "Computer [ id=," + computerid + ",Name =  " + computername + " ,Sorti en : " + computerintroductedin + ", Arrêté en :" + computerdiscontinuedin + ", Id de la compagnie : " + computercompanieid;
	}
	
	
}
