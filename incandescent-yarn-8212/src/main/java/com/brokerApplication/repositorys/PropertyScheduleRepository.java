package com.brokerApplication.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brokerApplication.entities.PropertySchedule;

@Repository
public interface PropertyScheduleRepository extends JpaRepository<PropertySchedule, Integer>{
	
	
	
}
