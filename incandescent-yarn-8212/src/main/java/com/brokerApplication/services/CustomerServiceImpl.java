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
	public Customer addCustomer(Customer c) {
		if(c == null) throw new CustomerException("Customer can not be null");
		
		c = cr.save(c);
		
		return c;
	}

	@Override
	public Customer editCustomer(Customer c) {
		if(c == null) throw new CustomerException("Customer can not be null");
		
		Optional<Customer> opt = cr.findById(c.getUserId());
		
		if(opt.isPresent()) {
			
			c = cr.save(c);
			
			return c;
			
		}
	
		throw new CustomerException("No such customer is available");
	}

	@Override
	public Customer removeCustomer(Integer id) {
		Optional<Customer> opt = cr.findById(id);
		
		 if(opt.isPresent()) {
			 
			 Customer c = opt.get();
			 
			 cr.delete(c);
			 
			 return c;
			 
		 }
		 
		 throw new CustomerException("No such customer is available with id: "+id);
	}

	@Override
	public Customer viewCustomerById(Integer id) {
		Optional<Customer> opt = cr.findById(id);
		
		 if(opt.isPresent()) {
			 
			 Customer c = opt.get();
			 
			 return c;
			 
		 }
		 
		 throw new CustomerException("No such customer is available with id: "+id);
	}

	@Override
	public List<Customer> viewAllCustomers() {
		
		List<Customer> list = cr.findAll();
		
		 if(list.size()==0) throw new CustomerException("No customer is available");
		 
		 return list;
		 
		
	}

	@Override
	public List<Property> viewAllProptiesByCustomerId(Integer id) {
		
		Customer c = viewCustomerById(id);
		
		return c.getListOfProperties();
		
	}

	@Override
	public List<Deal> viewAllDealsByCustomerId(Integer id) {
		Customer c = viewCustomerById(id);
		
		return c.getListOfDeals();
	}	
	
}
