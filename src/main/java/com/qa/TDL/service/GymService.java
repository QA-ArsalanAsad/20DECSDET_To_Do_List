package com.qa.TDL.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.TDL.DTO.GymDTO;
import com.qa.TDL.persistence.domain.Gym;
import com.qa.TDL.persistence.repo.GymRepo;

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
	
	
	

}
