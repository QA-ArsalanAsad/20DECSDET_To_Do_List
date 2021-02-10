package com.qa.TDL.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Equipment {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String type;
	@NotNull
	private int price;
	@ManyToOne
	private Gym gym = null;
	
	public Equipment(@NotNull String type, @NotNull int price, Gym gym) {
		super();
		this.type = type;
		this.price = price;
		this.gym = gym;
	}

	public Equipment(Long id, @NotNull String type, @NotNull int price, Gym gym) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
		this.gym = gym;
	}
	
	

}
