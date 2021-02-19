package com.qa.TDL.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GymDTO {

	private Long id;
	private String name;
	private String type;
	private List<EquipmentDTO> equipment = new ArrayList<>();

}
