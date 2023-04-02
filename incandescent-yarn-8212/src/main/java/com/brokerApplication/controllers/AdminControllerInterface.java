package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.entities.PropertySchedule;
import com.brokerApplication.exceptions.PropertyException;

public interface AdminControllerInterface {
	
	public ResponseEntity<List<Broker>> getAllBrokersHandler();
	public ResponseEntity<Broker> getBrokerById(Integer brokerId);
	
	public ResponseEntity<List<Customer>> viewAllCustomers();
	public ResponseEntity<Customer> getCustomerById(Integer customerId);
	
	public ResponseEntity<List<Deal>> getAllDeals();
	public ResponseEntity<Deal> getDealById(Integer dealId);
	
	public ResponseEntity<Property> viewPropertyById(Integer propertyId)throws PropertyException;
	public ResponseEntity<List<Property>> viewListOfProperties()throws PropertyException;
	
	public ResponseEntity<List<PropertySchedule>> getScheduleOfPropertyById(Integer propertyId);
	public ResponseEntity<List<PropertySchedule>> getAllPropertySchedules();
}
