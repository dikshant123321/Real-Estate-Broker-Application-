package com.brokerApplication.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.PropertySchedule;
import com.brokerApplication.exceptions.PropertyScheduleException;
import com.brokerApplication.repositorys.PropertyScheduleRepository;

@Service
public class PropertyScheduleServiceImpl implements PropertyScheduleService {

	@Autowired
	PropertyScheduleRepository psr;
	
	@Override
	public PropertySchedule markPropertyScheduled(PropertySchedule propertySchedule) {
		
		return psr.save(propertySchedule);
		
	}

	@Override
	public PropertySchedule removePropertySchedule(Integer propertyScheduleId) {
		
		PropertySchedule propertySchedule = psr.findById(propertyScheduleId).get();
		psr.deleteById(propertyScheduleId);
		return propertySchedule;
		
	}
	
	@Override
	public boolean checkIsPropertyScheduledByPropertyIdAndBetweenStartDateAndEndDate(Integer propertyId,
			LocalDate startDate, LocalDate endDate) {
		List<PropertySchedule> list = psr.findByPropertyIdAndBetweenStartDateAndEndDate(propertyId, startDate, endDate);
		
		if(!list.isEmpty()) throw new PropertyScheduleException("There's already "+list.size()+" reservations are done in between "+startDate+" and "+ endDate+".");
		
		return true;
	}

	@Override
	public List<PropertySchedule> getScheduleOfPropertyById(Integer propertyId) {
		return psr.findByPropertyId(propertyId);
	}

	@Override
	public List<PropertySchedule> getAllPropertySchedules() {
		return psr.findAll();
	}


}
