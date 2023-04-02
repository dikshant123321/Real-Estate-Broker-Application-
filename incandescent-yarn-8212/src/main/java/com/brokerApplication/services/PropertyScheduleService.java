package com.brokerApplication.services;

import java.time.LocalDate;
import java.util.List;

import com.brokerApplication.entities.PropertySchedule;

public interface PropertyScheduleService {
	
	//internal use only
	public PropertySchedule markPropertyScheduled(PropertySchedule propertySchedule);
	public PropertySchedule removePropertySchedule(Integer propertyScheduleId);
	public boolean checkIsPropertyScheduledByPropertyIdAndBetweenStartDateAndEndDate(Integer propertyId, LocalDate startDate, LocalDate endDate);
	
	//for public use
	public List<PropertySchedule> getScheduleOfPropertyById(Integer propertyId);
	
	//for admin
	public List<PropertySchedule> getAllPropertySchedules();
	
}
