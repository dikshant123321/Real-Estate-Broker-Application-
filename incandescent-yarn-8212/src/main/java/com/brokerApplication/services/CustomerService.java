package com.brokerApplication.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;


public interface CustomerService {

	public Customer addCustomer(Customer c);
	
	public Customer editCustomer(Customer c);
	
	public Customer removeCustomer(Integer id);
	
	public Customer viewCustomerById(Integer id);
	
	public List<Customer> viewAllCustomers();
	
	public List<Property> viewAllProptiesByCustomerId(Integer id);
	
	public List<Deal> viewAllDealsByCustomerId(Integer id);
//	
//	public ResponseEntity<Deal> buyOrRentProperty(Integer customerId, Integer PropertyId);
//	
//	
	
}
