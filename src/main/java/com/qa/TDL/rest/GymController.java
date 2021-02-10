package com.qa.TDL.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.TDL.DTO.GymDTO;
import com.qa.TDL.persistence.domain.Gym;
import com.qa.TDL.service.GymService;

@RestController
@RequestMapping("/gym")
public class GymController {
	private GymService srvc;
	
	@Autowired
	public GymController(GymService srvc) {
		super();
		this.srvc = srvc;
	}
	
	@PostMapping ("/create")
	public ResponseEntity <GymDTO> create(@RequestBody Gym gym){
		GymDTO created = this.srvc.create(gym);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
		
	}
	
	
	

}
