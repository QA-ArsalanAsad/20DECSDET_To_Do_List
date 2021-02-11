package com.qa.TDL.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.TDL.DTO.EquipmentDTO;
import com.qa.TDL.persistence.domain.Equipment;
import com.qa.TDL.persistence.repo.EquipmentRepo;

@Service
public class EquipmentService {

	private EquipmentRepo eRepo;

	private ModelMapper mapper;

	private EquipmentDTO mapToDTO(Equipment equipment) {
		return this.mapper.map(equipment, EquipmentDTO.class);
	}

	@Autowired
	public EquipmentService(EquipmentRepo eRepo, ModelMapper mapper) {
		super();
		this.eRepo = eRepo;
		this.mapper = mapper;
	}

	public EquipmentDTO create(Equipment equipment) {
		return this.mapToDTO(this.eRepo.save(equipment));
	}

	public List<EquipmentDTO> readAll() {
		return this.eRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public EquipmentDTO readLatest(Long id) {
		return this.mapToDTO(this.eRepo.findById(id).orElseThrow());
	}

}
