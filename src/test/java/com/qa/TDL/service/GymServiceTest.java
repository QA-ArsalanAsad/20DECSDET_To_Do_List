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

import com.qa.TDL.DTO.GymDTO;
import com.qa.TDL.persistence.domain.Gym;
import com.qa.TDL.persistence.repo.GymRepo;

@SpringBootTest
@ActiveProfiles("dev")
public class GymServiceTest {

	private final Gym T_GYM_1 = new Gym("Manchester", "GymWhale");
	private final Gym T_GYM_2 = new Gym("London", "CleanGym");
	private final Gym T_GYM_1N = new Gym(null, "Manchester", "JDBCGym");
	private final Gym T_GYM_1U = new Gym(1L, T_GYM_1N.getName(), T_GYM_1N.getType());
	private final List<Gym> listGym = List.of(T_GYM_1, T_GYM_2);

	@Autowired
	GymService gSrvc;

	@MockBean
	GymRepo gRepo;

	@Autowired
	private ModelMapper mapper;

	private GymDTO mapToDTO(Gym gym) {
		return this.mapper.map(gym, GymDTO.class);
	}

	@Test
	void testCreate() throws Exception {
		final Gym TEST_GYM_N = new Gym(null, "Manchester", "GymWhale");
		final Gym TEST_GYM_S = new Gym(1L, "Manchester", "GymWhale");
		when(this.gRepo.save(TEST_GYM_N)).thenReturn(TEST_GYM_S);
		assertThat(this.gSrvc.create(TEST_GYM_N)).isEqualTo(this.mapToDTO(TEST_GYM_S));
		verify(this.gRepo, atLeastOnce()).save(TEST_GYM_N);
	}

	@Test
	void testReadAll() throws Exception {
		List<GymDTO> gymDTOs = listGym.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.gRepo.findAll()).thenReturn(listGym);
		assertThat(this.gSrvc.readAll()).isEqualTo(gymDTOs);
		verify(this.gRepo, atLeastOnce()).findAll();
	}

	@Test
	void testReadLatest() throws Exception {
		final Long id = 2L;
		Gym T_GYM_ID = new Gym(id, "London", "CleanGym");
		when(this.gRepo.findById(id)).thenReturn(Optional.of(T_GYM_ID));
		assertThat(this.gSrvc.readLatest(id)).isEqualTo(this.mapToDTO(T_GYM_ID));
		verify(this.gRepo, atLeastOnce()).findById(id);

	}

	@Test
	void testUpdate() throws Exception {
		final Long id = 1L;
		when(this.gRepo.findById(id)).thenReturn(Optional.of(T_GYM_1));
		when(this.gRepo.save(T_GYM_1U)).thenReturn(T_GYM_1U);
		assertThat(this.gSrvc.update(this.mapToDTO(T_GYM_1U), id)).isEqualTo(this.mapToDTO(T_GYM_1));
		verify(this.gRepo, atLeastOnce()).findById(id);
		verify(this.gRepo, atLeastOnce()).save(T_GYM_1U);

	}

	@Test
	void testDelete() throws Exception {
		final Long id = 2L;
		when(this.gRepo.existsById(id)).thenReturn(false);
		assertThat(this.gSrvc.delete(id)).isEqualTo(true);
		verify(this.gRepo, atLeastOnce()).existsById(id);

	}

}
