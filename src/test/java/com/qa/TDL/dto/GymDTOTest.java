package com.qa.TDL.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.TDL.DTO.GymDTO;

// should be a test profile, change active profile to test
public class GymDTOTest {

	private GymDTO testDTO;

	@BeforeEach
	public void beforeTest() {
		testDTO = new GymDTO();

	}

	@Test
	public void hashCodeTest() {
		testDTO.hashCode();
	}

	@Test
	public void toStringTest() {
		testDTO.toString();

	}

	@Test
	public void constructorTest() {
		testDTO = new GymDTO();
		assertTrue(testDTO instanceof GymDTO);
		assertNotNull(testDTO);

	}

}
