package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.entities.PropertySchedule;
import com.brokerApplication.exceptions.PropertyException;
import com.brokerApplication.services.BrokerServices;
import com.brokerApplication.services.CustomerService;
import com.brokerApplication.services.DealService;
import com.brokerApplication.services.PropertyScheduleService;
import com.brokerApplication.services.PropertyService;

@RestController
public class AdminController implements AdminControllerInterface{
	
	@Autowired
	private BrokerServices brokerServices;
	
	@Autowired 
	private PropertyScheduleService pss;
	
	@Autowired
	private CustomerService cs;
	
	@Autowired
	private DealService ds;
	
	@Autowired
	private PropertyService ps;
	
	@GetMapping("/admin/brokers")
	public ResponseEntity<List<Broker>> getAllBrokersHandler(){
		List<Broker> brokers= brokerServices.listAllBrokers();
		return new ResponseEntity<>(brokers,HttpStatus.OK);
	}
	
	

	@Override
	@GetMapping("/admin/property/propertySchedule")
	public ResponseEntity<List<PropertySchedule>> getScheduleOfPropertyById(@RequestParam Integer propertyId) {
		
		return new ResponseEntity<>(pss.getScheduleOfPropertyById(propertyId), HttpStatus.OK);
		
	}


	@Override
	@GetMapping("admin/property/propertySchedules")
	public ResponseEntity<List<PropertySchedule>> getAllPropertySchedules() {
		
		return new ResponseEntity<>(pss.getAllPropertySchedules(), HttpStatus.OK);
		
	}



	@Override
	@GetMapping("/admin/customers")
	public ResponseEntity<List<Customer>> viewAllCustomers() {
		return new ResponseEntity<>(cs.viewAllCustomers(), HttpStatus.OK);
	}



	@Override
	@GetMapping("/admin/deals")
	public ResponseEntity<List<Deal>> getAllDeals() {
		return new ResponseEntity<>(ds.getAllDeals(), HttpStatus.OK);
	}



	@Override
	@GetMapping("/admin/property")
	public ResponseEntity<Property> viewPropertyById(Integer propertyId) throws PropertyException {
		return new ResponseEntity<>(ps.viewPropertyById(propertyId), HttpStatus.OK);
	}



	@Override
	@GetMapping("/admin/properties")
	public ResponseEntity<List<Property>> viewListOfProperties() throws PropertyException {
		return new ResponseEntity<>(ps.viewListOfProperties(), HttpStatus.OK);
	}



	@Override
	@GetMapping("/admin/broker/{brokerId}")
	public ResponseEntity<Broker> getBrokerById(Integer brokerId) {
		return new ResponseEntity<>(brokerServices.viewBrokerById(brokerId), HttpStatus.OK);
	}



	@Override
	@GetMapping("/admin/customer/{customerId}")
	public ResponseEntity<Customer> getCustomerById(Integer customerId) {
		return new ResponseEntity<>(cs.viewCustomerById(customerId), HttpStatus.OK);
	}



	@Override
	@GetMapping("/admin/deal/{dealId}")
	public ResponseEntity<Deal> getDealById(Integer dealId) {
		return new ResponseEntity<>(ds.getDealbyID(dealId), HttpStatus.OK);
	}


}
