package com.qa.TDL.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.TDL.DTO.GymDTO;
import com.qa.TDL.persistence.domain.Gym;
import com.qa.TDL.service.GymService;

@CrossOrigin
@RestController
@RequestMapping("/gym")
public class GymController {
	private GymService srvc;

	@Autowired
	public GymController(GymService srvc) {
		super();
		this.srvc = srvc;
	}

	@PostMapping("/create")
	public ResponseEntity<GymDTO> create(@RequestBody Gym gym) {
		GymDTO created = this.srvc.create(gym);
		return new ResponseEntity<>(created, HttpStatus.CREATED);

	}

	@GetMapping("/read")
	public ResponseEntity<List<GymDTO>> readAll() {
		return ResponseEntity.ok(this.srvc.readAll());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<GymDTO> readLatest(@PathVariable Long id) {
		return ResponseEntity.ok(this.srvc.readLatest(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<GymDTO> update(@PathVariable Long id, @RequestBody GymDTO gymDTO) {
		return new ResponseEntity<>(this.srvc.update(gymDTO, id), (HttpStatus.ACCEPTED));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GymDTO> delete(@PathVariable Long id) {
		return this.srvc.delete(id) ? new ResponseEntity<>(HttpStatus.GONE)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
