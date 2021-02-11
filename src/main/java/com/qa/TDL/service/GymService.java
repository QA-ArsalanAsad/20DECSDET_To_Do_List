package com.qa.TDL.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.TDL.DTO.GymDTO;
import com.qa.TDL.exceptions.GymExceptions;
import com.qa.TDL.persistence.domain.Gym;
import com.qa.TDL.persistence.repo.GymRepo;
import com.qa.TDL.utils.SpringUtils;

@Service
public class GymService {

	private GymRepo repo;

	private ModelMapper mapper;

	private GymDTO mapToDTO(Gym gym) {
		return this.mapper.map(gym, GymDTO.class);
	}

	@Autowired
	public GymService(GymRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public GymDTO create(Gym gym) {
		return this.mapToDTO(this.repo.save(gym));
	}

	public List<GymDTO> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public GymDTO readLatest(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(GymExceptions::new));
	}

	public GymDTO update(GymDTO gymDTO, Long id) {
		Gym update = this.repo.findById(id).orElseThrow(GymExceptions::new);
		SpringUtils.mergeNotNull(gymDTO, update);
		return this.mapToDTO(this.repo.save(update));
	}

	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
