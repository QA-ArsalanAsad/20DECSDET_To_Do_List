package com.qa.TDL.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

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

}
