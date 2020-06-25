package excilys.formation.cdb.persistence;
import static org.junit.Assert.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.formation.cdb.mapper.MapperComputerDAO;
import com.excilys.formation.cdb.modele.Computer;


public class ComputerMapperTest {
	
	private  final int ATTRIBUT_ID_COMPUTER = 1;
    private  final int ATTRIBUT_NAME = 2;
    private  final int ATTRIBUT_INTRODUCED = 3;
    private  final int ATTRIBUT_DISCONTINUED = 4;
    private  final int ATTRIBUT_COMPANY_ID = 5;
    
    private  final Integer idComputer = 10;
    private  final String nameComputer = "Computer";
    private  final LocalDate introduced = LocalDate.of(2019, 8, 8);
    private  final LocalDate discontinued = LocalDate.of(2020, 8, 8);
    private  final Integer idCompany = 20;
    
    private ResultSet resultSet = Mockito.mock(ResultSet.class);

	@Before
	public void setUp() throws Exception {
		Mockito.when(resultSet.getInt(ATTRIBUT_ID_COMPUTER)).thenReturn(idComputer);
		Mockito.when(resultSet.getString(ATTRIBUT_NAME)).thenReturn(nameComputer);
	}

	@Test
	public void mapComputer() throws SQLException {
		Mockito.when(resultSet.getDate(ATTRIBUT_INTRODUCED)).thenReturn(Date.valueOf(introduced));
		Mockito.when(resultSet.getDate(ATTRIBUT_DISCONTINUED)).thenReturn(Date.valueOf(discontinued));
		Mockito.when(resultSet.getInt(ATTRIBUT_COMPANY_ID)).thenReturn(idCompany);
		Computer computer = MapperComputerDAO.mapComputers(resultSet);
		Computer computerExpected = new Computer(idComputer,nameComputer,introduced,discontinued,idCompany);
		assertEquals(computerExpected,computer);
	}
	
	@Test
	public void mapComputerIdCompanyNull() throws SQLException {
		Mockito.when(resultSet.getDate(ATTRIBUT_INTRODUCED)).thenReturn(Date.valueOf(introduced));
		Mockito.when(resultSet.getDate(ATTRIBUT_DISCONTINUED)).thenReturn(Date.valueOf(discontinued));
		Mockito.when(resultSet.getInt(ATTRIBUT_COMPANY_ID)).thenReturn(0);
		Computer computer = MapperComputerDAO.mapComputers(resultSet);
		Computer computerExpected = new Computer(idComputer,nameComputer);
		computerExpected.setComputerintroductedin(introduced);
		computerExpected.setComputerdiscontinuedin(discontinued);
		assertEquals(computerExpected,computer);
	}
	
	@Test
	public void mapComputerIntroducedNull() throws SQLException {
		Mockito.when(resultSet.getDate(ATTRIBUT_INTRODUCED)).thenReturn(null);
		Mockito.when(resultSet.getDate(ATTRIBUT_DISCONTINUED)).thenReturn(Date.valueOf(discontinued));
		Mockito.when(resultSet.getInt(ATTRIBUT_COMPANY_ID)).thenReturn(idCompany);
		Computer computer = MapperComputerDAO.mapComputers(resultSet);
		Computer computerExpected = new Computer(idComputer,nameComputer);
		computerExpected.setComputerdiscontinuedin(discontinued);
		computerExpected.setComputercompanieid(idCompany);
		assertEquals(computerExpected,computer);	
	}
	
	@Test
	public void mapComputerDiscontinuedNull() throws SQLException {
		Mockito.when(resultSet.getDate(ATTRIBUT_INTRODUCED)).thenReturn(Date.valueOf(introduced));
		Mockito.when(resultSet.getDate(ATTRIBUT_DISCONTINUED)).thenReturn(null);
		Mockito.when(resultSet.getInt(ATTRIBUT_COMPANY_ID)).thenReturn(idCompany);
		Computer computer = MapperComputerDAO.mapComputers(resultSet);
		Computer computerExpected = new Computer(idComputer,nameComputer);
		computerExpected.setComputercompanieid(idCompany);
		computerExpected.setComputerintroductedin(introduced);
		assertEquals(computerExpected,computer);
		
	}

}