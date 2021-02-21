package com.qa.TDL.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.TDL.DTO.EquipmentDTO;

public class EquipmentDTOTest {

	private EquipmentDTO testDTO;

	@BeforeEach
	public void beforeTest() {
		testDTO = new EquipmentDTO();

	}

	@Test
	public void hashCodeTest() {
		testDTO.hashCode();
		assertTrue(testDTO instanceof EquipmentDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void toStringTest() {
		testDTO.toString();
		assertTrue(testDTO instanceof EquipmentDTO);
		assertNotNull(testDTO);

	}

	@Test
	public void constructorTest() {
		testDTO = new EquipmentDTO();
		assertTrue(testDTO instanceof EquipmentDTO);
		assertNotNull(testDTO);

	}

}
