package com.brokerApplication.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;


public interface CustomerService {

	public ResponseEntity<Customer> addCustomer(Customer c);
	
	public ResponseEntity<Customer> editCustomer(Customer c);
	
	public ResponseEntity<Customer> removeCustomer(Integer id);
	
	public ResponseEntity<Customer> viewCustomerById(Integer id);
	
	public ResponseEntity<List<Customer>> viewAllCustomers();
	
	public ResponseEntity<List<Property>> viewAllProptiesByCustomerId(Integer id);
	
	public ResponseEntity<List<Deal>> viewAllDealsByCustomerId(Integer id);
//	
//	public ResponseEntity<Deal> buyOrRentProperty(Integer customerId, Integer PropertyId);
//	
//	
	
}
