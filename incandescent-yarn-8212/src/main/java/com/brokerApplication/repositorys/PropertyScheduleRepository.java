package com.brokerApplication.repositorys;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brokerApplication.entities.PropertySchedule;

@Repository
public interface PropertyScheduleRepository extends JpaRepository<PropertySchedule, Integer>{
	
	List<PropertySchedule> findByPropertyId(Integer propertyId);
	
	@Query("SELECT ps FROM PropertySchedule ps WHERE ps.propertyId = ?1 AND (ps.startDate BETWEEN ?2 AND ?3 OR ps.endDate Between ?2 AND ?3)")
	List<PropertySchedule> findByPropertyIdAndBetweenStartDateAndEndDate(Integer propertyId, LocalDate startDate, LocalDate endDate);
	
}
