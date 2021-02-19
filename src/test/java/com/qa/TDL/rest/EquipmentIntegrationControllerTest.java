package com.qa.TDL.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.TDL.DTO.EquipmentDTO;
import com.qa.TDL.persistence.domain.Equipment;
import com.qa.TDL.persistence.domain.Gym;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = { "classpath:gym-schema.sql",
		"classpath:gym-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class EquipmentIntegrationControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ModelMapper mapper;

	private EquipmentDTO mapToDTO(Equipment equipment) {
		return this.mapper.map(equipment, EquipmentDTO.class);
	}

	@Autowired
	private ObjectMapper jsonifier;
	private final Equipment T_EQ_1 = new Equipment(1L, "Dumbbells", 30);
	private final Equipment T_EQ_2 = new Equipment(2L, "Bench", 90);
	private final Equipment T_EQ_3 = new Equipment(3L, "Barbell", 40);
	private final Gym T_GYM_1 = new Gym(1L, "Manchester", "GymWhale");
	private final Gym T_GYM_2 = new Gym(2L, "Birmingham", "JDBCGym");
	private final Gym T_GYM_3 = new Gym(3L, "London", "CleanGym");
	private final List<Gym> listGym = List.of(T_GYM_1, T_GYM_2, T_GYM_3);
	private final List<Equipment> listEquipment = List.of(T_EQ_1, T_EQ_2, T_EQ_3);
	private final String URI = "/equipment";

	@Test
	void testCreate() throws Exception {
		EquipmentDTO TEST_EQ = mapToDTO(new Equipment("Dumbbells", 30));
		EquipmentDTO TEST_EQ_SAVED = mapToDTO(new Equipment("Dumbbells", 30));
		String testEquipmentToJSON = this.jsonifier.writeValueAsString(TEST_EQ);
		TEST_EQ_SAVED.setId(4L);
		String testEquipmentSavedToJSON = this.jsonifier.writeValueAsString(TEST_EQ_SAVED);
		RequestBuilder rB = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testEquipmentToJSON);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testEquipmentSavedToJSON);
		this.mvc.perform(rB).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testReadLatest() throws Exception {

		RequestBuilder rB = get(URI + "/read/" + T_EQ_1.getId()).accept(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatus = status().isOk();
		this.mvc.perform(rB).andExpect(checkStatus)
				.andExpect(content().json(this.jsonifier.writeValueAsString(this.mapToDTO(T_EQ_1))));

	}

	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete(URI + "/delete/" + T_EQ_1.getId())).andExpect(status().isGone());
	}

}
