package com.brokerApplication.services;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.CustomerException;
import com.brokerApplication.repositorys.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepository cr;

	@Override
	public ResponseEntity<Customer> addCustomer(Customer c) {
		
		if(c == null) throw new CustomerException("Customer can not be null");
		
		c = cr.save(c);
		
		return new ResponseEntity<>(c, HttpStatus.ACCEPTED);
		
	}

	@Override
	public ResponseEntity<Customer> editCustomer(Customer c) {
		
		if(c == null) throw new CustomerException("Customer can not be null");
		
		Optional<Customer> opt = cr.findById(c.getUserId());
		
		if(opt.isPresent()) {
			
			c = cr.save(c);
			
			return new ResponseEntity<>(c, HttpStatus.OK);
			
		}
	
		throw new CustomerException("No such customer is available");
		
	}

	@Override
	public ResponseEntity<Customer> removeCustomer(Integer id) {
		
		 Optional<Customer> opt = cr.findById(id);
		
		 if(opt.isPresent()) {
			 
			 Customer c = opt.get();
			 
			 cr.delete(c);
			 
			 return new ResponseEntity<Customer>(c, HttpStatus.OK);
			 
		 }
		 
		 throw new CustomerException("No such customer is available with id: "+id);
		 
	}

	@Override
	public ResponseEntity<Customer> viewCustomerById(Integer id) {
		Optional<Customer> opt = cr.findById(id);
		
		 if(opt.isPresent()) {
			 
			 Customer c = opt.get();
			 
			 return new ResponseEntity<Customer>(c, HttpStatus.OK);
			 
		 }
		 
		 throw new CustomerException("No such customer is available with id: "+id);
		 
	}

	@Override
	public ResponseEntity<List<Customer>> viewAllCustomers() {
		
		List<Customer> list = cr.findAll();
		
		 if(list.size()==0) throw new CustomerException("No customer is available");
		 
		 return new ResponseEntity<>(list, HttpStatus.OK);
		 
		
	}

	@Override
	public ResponseEntity<List<Property>> viewAllProptiesByCustomerId(Integer id) {
		
		Customer c = viewCustomerById(id).getBody();
		
		return new ResponseEntity<>(c.getListOfProperties(),HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<List<Deal>> viewAllDealsByCustomerId(Integer id) {

		Customer c = viewCustomerById(id).getBody();
		
		return new ResponseEntity<>(c.getListOfDeals(),HttpStatus.OK);
		
	}
	
	
	
}
