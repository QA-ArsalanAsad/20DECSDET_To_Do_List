package com.qa.TDL.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.TDL.persistence.domain.Gym;

@Repository
public interface GymRepo extends JpaRepository <Gym, Long>{

}
