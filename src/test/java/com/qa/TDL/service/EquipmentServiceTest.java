package com.qa.TDL.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.TDL.DTO.EquipmentDTO;
import com.qa.TDL.persistence.domain.Equipment;
import com.qa.TDL.persistence.repo.EquipmentRepo;

@SpringBootTest
@ActiveProfiles("dev")
public class EquipmentServiceTest {

	private final Equipment T_E_1 = new Equipment("Dumbbells", 30);
	private final Equipment T_E_2 = new Equipment("Bench", 90);
	private final Equipment T_E_1N = new Equipment(null, "BarBell", 45);
	private final Equipment T_E_1U = new Equipment(1L, T_E_1N.getType(), T_E_1N.getPrice());
	private final List<Equipment> listEquipment = List.of(T_E_1, T_E_2);

	@Autowired
	EquipmentService eSrvc;

	@MockBean
	EquipmentRepo eRepo;

	@Autowired
	private ModelMapper mapper;

	private EquipmentDTO mapToDTO(Equipment equipment) {
		return this.mapper.map(equipment, EquipmentDTO.class);
	}

	@Test
	void testCreate() throws Exception {
		final Equipment TEST_E_N = new Equipment(null, "Bench", 90);
		final Equipment TEST_E_S = new Equipment(1L, "Bench", 90);
		when(this.eRepo.save(TEST_E_N)).thenReturn(TEST_E_S);
		assertThat(this.eSrvc.create(TEST_E_N)).isEqualTo(this.mapToDTO(TEST_E_S));
		verify(this.eRepo, atLeastOnce()).save(TEST_E_N);
	}

	@Test
	void testReadAll() throws Exception {
		List<EquipmentDTO> eDTOs = listEquipment.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.eRepo.findAll()).thenReturn(listEquipment);
		assertThat(this.eSrvc.readAll()).isEqualTo(eDTOs);
		verify(this.eRepo, atLeastOnce()).findAll();
	}

	@Test
	void testReadLatest() throws Exception {
		final Long id = 2L;
		Equipment T_E_ID = new Equipment(id, "BarBell", 65);
		when(this.eRepo.findById(id)).thenReturn(Optional.of(T_E_ID));
		assertThat(this.eSrvc.readLatest(id)).isEqualTo(this.mapToDTO(T_E_ID));
		verify(this.eRepo, atLeastOnce()).findById(id);

	}

	@Test
	void testUpdate() throws Exception {
		final Long id = 1L;
		when(this.eRepo.findById(id)).thenReturn(Optional.of(T_E_1));
		when(this.eRepo.save(T_E_1U)).thenReturn(T_E_1U);
		assertThat(this.eSrvc.update(this.mapToDTO(T_E_1U), id)).isEqualTo(this.mapToDTO(T_E_1));
		verify(this.eRepo, atLeastOnce()).findById(id);
		verify(this.eRepo, atLeastOnce()).save(T_E_1U);

	}

	@Test
	void testDelete() throws Exception {
		final Long id = 2L;
		when(this.eRepo.existsById(id)).thenReturn(false);
		assertThat(this.eSrvc.delete(id)).isEqualTo(true);
		verify(this.eRepo, atLeastOnce()).existsById(id);

	}

}
