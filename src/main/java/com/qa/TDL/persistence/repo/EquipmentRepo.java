package com.qa.TDL.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.TDL.persistence.domain.Equipment;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Long> {

}
