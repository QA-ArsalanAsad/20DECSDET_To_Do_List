package com.qa.TDL.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.TDL.DTO.GymDTO;
import com.qa.TDL.persistence.domain.Gym;
import com.qa.TDL.service.GymService;

@SpringBootTest
@ActiveProfiles("dev")
public class GymControllerTest {

	private final Gym T_GYM_1 = new Gym(1L, "Manchester", "GymWhale");
	private final Gym T_GYM_2 = new Gym(2L, "London", "CleanGym");
	private final List<Gym> listGym = List.of(T_GYM_1, T_GYM_2);

	@Autowired
	GymController controller;
	@MockBean
	private GymService srvc;
	@Autowired
	private ModelMapper mapper;

	private GymDTO mapToDTO(Gym gym) {
		return this.mapper.map(gym, GymDTO.class);
	}

	@Test
	void testCreate() throws Exception {
		when(this.srvc.create(T_GYM_1)).thenReturn(this.mapToDTO(T_GYM_1));
		assertThat(new ResponseEntity<GymDTO>(this.mapToDTO(T_GYM_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(T_GYM_1));
		verify(this.srvc, Mockito.times(1)).create(T_GYM_1);

	}

	@Test
	void testReadAll() throws Exception {
		List<GymDTO> gymDTOs = listGym.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.srvc.readAll()).thenReturn(gymDTOs);
		assertThat(this.controller.readAll()).isEqualTo(new ResponseEntity<>(gymDTOs, HttpStatus.OK));
		verify(this.srvc, atLeastOnce()).readAll();

	}

	@Test
	void testReadLatest() throws Exception {
		final Long id = 2L;
		when(this.srvc.readLatest(id)).thenReturn(this.mapToDTO(T_GYM_2));
		assertThat(new ResponseEntity<GymDTO>(this.mapToDTO(T_GYM_2), HttpStatus.OK))
				.isEqualTo(this.controller.readLatest(id));
		verify(this.srvc, atLeastOnce()).readLatest(id);
	}

	@Test
	void testUpdate() throws Exception {
		final Long id = 1L;
		final GymDTO gymDTO = null;
		when(this.srvc.update(gymDTO, id)).thenReturn(this.mapToDTO(T_GYM_1));
		assertThat(new ResponseEntity<GymDTO>(this.mapToDTO(T_GYM_1), HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(id, gymDTO));
		verify(this.srvc, atLeastOnce()).update(gymDTO, id);

	}

	@Test
	void testDelete() throws Exception {
		when(this.srvc.delete(T_GYM_2.getId())).thenReturn(false);
		assertThat(this.controller.delete(T_GYM_2.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		verify(this.srvc, atLeastOnce()).delete(T_GYM_2.getId());
	}

	@Test
	void testDelete2() throws Exception {
		when(this.srvc.delete(T_GYM_2.getId())).thenReturn(true);
		assertThat(this.controller.delete(T_GYM_2.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.GONE));
		verify(this.srvc, atLeastOnce()).delete(T_GYM_2.getId());
	}

}
