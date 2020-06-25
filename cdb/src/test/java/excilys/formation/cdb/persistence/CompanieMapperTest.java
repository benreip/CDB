package excilys.formation.cdb.persistence;



import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.formation.cdb.mapper.MapperCompanieDAO;
import com.excilys.formation.cdb.modele.Companie;


public class CompanieMapperTest {
	private static final int ATTRIBUT_ID_COMPANY = 1;
	private static final int ATTRIBUT_NAME_COMPANY = 2;
	
	private static final Integer idCompany = 10;
	private static final String companyName = "Company";
	
	private ResultSet resultSet = Mockito.mock(ResultSet.class);

	@Before
	public void setUp() throws Exception {
		Mockito.when(resultSet.getInt(ATTRIBUT_ID_COMPANY)).thenReturn(idCompany);
		Mockito.when(resultSet.getString(ATTRIBUT_NAME_COMPANY)).thenReturn(companyName);
	}

	@Test
	public void resultToObject() throws SQLException {
		Companie companie = MapperCompanieDAO.mapCompanie(resultSet);
		Companie companyExpected = new Companie(idCompany,companyName);
		assertEquals(companyExpected,companie);
	}

}