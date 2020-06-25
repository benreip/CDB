package com.excilys.formation.cdb.modele;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class ComputerTest {

	private Computer computer;

	@Before
	public void setUp() throws Exception {
		this.computer = new Computer();
	}

	@Test
	public void setIntroduced() {
		computer.setComputerdiscontinuedin(LocalDate.of(2023, 8, 8));
		computer.setComputerintroductedin(LocalDate.of(2020, 7, 7));
		assertEquals(LocalDate.of(2020, 7, 7),computer.getComputerintroductedin());
	}
	
	@Test
	public void setIntroducedAndDicontinuedNull() {
		computer.setComputerintroductedin(LocalDate.of(2020, 7, 7));
		assertEquals(LocalDate.of(2020, 7, 7),computer.getComputerintroductedin());
	}

	@Test(expected=IllegalArgumentException.class)
	public void setIntroducedIllegalArgumentException() {
		computer.setComputerdiscontinuedin(LocalDate.of(2019,8,8));
		computer.setComputerintroductedin(LocalDate.of(2020, 8, 8));
		assertEquals(LocalDate.of(2020, 8, 8),computer.getComputerintroductedin());
	}

	@Test
	public void setDiscontinued(){
		computer.setComputerintroductedin(LocalDate.of(2019, 8, 8));
		computer.setComputerdiscontinuedin(LocalDate.of(2020, 8, 8));
		assertEquals(LocalDate.of(2020, 8, 8),computer.getComputerdiscontinuedin());
	}

	@Test 
	public void setDiscontinuedAndIntroducedNull() {
		computer.setComputerdiscontinuedin(LocalDate.of(2020, 8, 8));
		assertEquals(LocalDate.of(2020, 8, 8), computer.getComputerdiscontinuedin());
	}

	@Test(expected=IllegalArgumentException.class)
	public void setDiscontinuedIllegalArgumentException() {
		computer.setComputerintroductedin(LocalDate.of(2020, 8, 8));
		computer.setComputerdiscontinuedin(LocalDate.of(2019, 8, 8));
		assertEquals(LocalDate.of(2019, 8, 8),computer.getComputerdiscontinuedin());
	}
}