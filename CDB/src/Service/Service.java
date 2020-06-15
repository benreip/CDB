package Service;

import java.util.Date;

import Modele.Companie;
import Modele.Computer;

public class Service {
	public Computer createComputer(String name, Date introduced, Date discontinued, long companyId, String companyName, long ... computer_id) throws Exception {
		
		//LocalDate intro = mapperDates.fromStringToLocalDate(introduced);
		//LocalDate disco = mapperDates.fromStringToLocalDate(discontinued);
		//validation.validationIntroDisco(intro, disco);
		
		//String companyName=companyDao.findCompany(cpanyId);
		//Company cpany = this.createCompany(cpanyId,companyName);
		Companie company = new Companie();	
		Computer computer =  new Computer();
		return computer;
	}
	
	/***
	 * @return 
	 * A positive number, if a positive number of rows are affected by the operation, and the operation is not a mass delete on a segmented table space.
	 * 0, if no rows are affected by the operation.
	 * -1, if the operation is a mass delete on a segmented table space.
	 * @throws SQLException 
	 */
	
	public int addComputer(Computer computer) throws SQLException {
		ComputerDao computerDao = new ComputerDao();
		int error = computerDao.addComputer(computer);
		return error;
	}
	
	public int deleteComputer(String computerId) throws SQLException {
		ComputerDao computerDao = new ComputerDao();
		long cputerId = Long.parseLong(computerId);
		int error = computerDao.deleteComputer(cputerId);
		return error;
	}
}
