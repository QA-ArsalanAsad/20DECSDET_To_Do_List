package com.qa.TDL.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.TDL.DTO.EquipmentDTO;
import com.qa.TDL.persistence.domain.Equipment;
import com.qa.TDL.service.EquipmentService;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

	private EquipmentService esrvc;

	@Autowired
	public EquipmentController(EquipmentService esrvc) {
		super();
		this.esrvc = esrvc;
	}

	@PostMapping("/create")
	public ResponseEntity<EquipmentDTO> create(@RequestBody Equipment equipment) {
		EquipmentDTO create = this.esrvc.create(equipment);
		return new ResponseEntity<>(create, HttpStatus.CREATED);

	}

	@GetMapping("/read")
	public ResponseEntity<List<EquipmentDTO>> readAll() {
		return ResponseEntity.ok(this.esrvc.readAll());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<EquipmentDTO> readLatest(@PathVariable Long id) {
		return ResponseEntity.ok(this.esrvc.readLatest(id));
	}

}
