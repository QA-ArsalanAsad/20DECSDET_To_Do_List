package com.qa.TDL.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Gym {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String type;
	
	@OneToMany(mappedBy ="gym", fetch=FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Equipment> equipment;
	
	
	public Gym(Long id, @NotNull String name, @NotNull String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}
	

	public Gym(@NotNull String name, @NotNull String type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	//Again below will contain all the required methods as in GymDTO to show what LOMBOK is doing

 
//	public Gym() {
//		super();
//		
//	}
//
//
//	public Long getId() {
//		return id;
//	}
//
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//
//	public String getName() {
//		return name;
//	}
//
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//
//	public String getType() {
//		return type;
//	}
//
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//
//	public List<Equipment> getEquipment() {
//		return equipment;
//	}
//
//
//	public void setEquipment(List<Equipment> equipment) {
//		this.equipment = equipment;
//	}
//
//
//	@Override
//	public String toString() {
//		return "Gym [id=" + id + ", name=" + name + ", type=" + type + ", equipment=" + equipment + "]";
//	}
//
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((type == null) ? 0 : type.hashCode());
//		return result;
//	}
//
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Gym other = (Gym) obj;
//		if (equipment == null) {
//			if (other.equipment != null)
//				return false;
//		} else if (!equipment.equals(other.equipment))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		if (type == null) {
//			if (other.type != null)
//				return false;
//		} else if (!type.equals(other.type))
//			return false;
//		return true;
//	}
//	
	
	
	

}

