package com.qa.TDL.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.TDL.DTO.EquipmentDTO;
import com.qa.TDL.persistence.domain.Equipment;
import com.qa.TDL.service.EquipmentService;

@SpringBootTest
@ActiveProfiles("dev")
public class EquipmentControllerTest {
	private final Equipment T_E_1 = new Equipment(1L, "Dumbbels", 40);
	private final Equipment T_E_2 = new Equipment(2L, "Bench", 90);
	private final List<Equipment> listEquipment = List.of(T_E_1, T_E_2);

	@Autowired
	EquipmentController eController;

	@MockBean
	private EquipmentService eSrvc;

	@Autowired
	private ModelMapper mapper;

	private EquipmentDTO mapToDTO(Equipment equipment) {
		return this.mapper.map(equipment, EquipmentDTO.class);
	}

	@Test
	void testCreate() throws Exception {
		when(this.eSrvc.create(T_E_1)).thenReturn(this.mapToDTO(T_E_1));
		assertThat(new ResponseEntity<EquipmentDTO>(this.mapToDTO(T_E_1), HttpStatus.CREATED))
				.isEqualTo(this.eController.create(T_E_1));
		verify(this.eSrvc, atLeastOnce()).create(T_E_1);

	}

	@Test
	void testReadAll() throws Exception {
		List<EquipmentDTO> eDTOs = listEquipment.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.eSrvc.readAll()).thenReturn(eDTOs);
		assertThat(this.eController.readAll()).isEqualTo(new ResponseEntity<>(eDTOs, HttpStatus.OK));
		verify(this.eSrvc, atLeastOnce()).readAll();
	}

	@Test
	void testReadLatest() throws Exception {
		final Long id = 2L;
		when(this.eSrvc.readLatest(id)).thenReturn(this.mapToDTO(T_E_2));
		assertThat(this.eController.readLatest(id))
				.isEqualTo(new ResponseEntity<EquipmentDTO>(this.mapToDTO(T_E_2), HttpStatus.OK));
		verify(this.eSrvc, atLeastOnce()).readLatest(id);
	}

	@Test
	void testUpdate() throws Exception {
		final Long id = 1L;
		final EquipmentDTO eDTO = null;
		when(this.eSrvc.update(eDTO, id)).thenReturn(this.mapToDTO(T_E_1));
		assertThat(new ResponseEntity<EquipmentDTO>(this.mapToDTO(T_E_1), HttpStatus.ACCEPTED))
				.isEqualTo(this.eController.update(id, eDTO));
		verify(this.eSrvc, atLeastOnce()).update(eDTO, id);

	}

	@Test
	void testDelete() throws Exception {
		when(this.eSrvc.delete(T_E_2.getId())).thenReturn(false);
		assertThat(this.eController.delete(T_E_2.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		verify(this.eSrvc, atLeastOnce()).delete(T_E_2.getId());
	}

}
