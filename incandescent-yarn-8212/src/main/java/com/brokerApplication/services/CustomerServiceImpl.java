package com.brokerApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.CustomerException;
import com.brokerApplication.repositorys.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
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

	@Override
	public Property addNewPropertyById(Integer customerId, Property property) {
		
		Customer customer = viewCustomerById(customerId);
		
		//Verify
		
		customer.getListOfProperties().add(property);
		
		customer = cr.save(customer);
		
		return property;
	}

//	@Override
//	public Property editCustomerPropertyById(Integer customerId, Property property) {
//		
//		Customer customer = viewCustomerById(customerId);
//		
//		//Verify
//		
//		Property updatedProperty = null;
//		
//		for(Property p: customer.getListOfProperties()) {
//			
//			if(property.getPropertyId() == p.getPropertyId()) {
//				
//				updatedProperty = p;
//				p = property;
//				break;
//				
//			}
//			
//		}
//		
//		if(updatedProperty == null) throw new CustomerException("Customer with Id "+customerId+" doesn't have Property with Id "+property.getPropertyId());
//		
//		customer = cr.save(customer);
//		
//		return property;
//		
//	}

	@Override
	public Property viewCustomerPropertyById(Integer customerId, Integer propertyId) {
		
		Customer customer = viewCustomerById(customerId);
		
		for(Property p: customer.getListOfProperties()) {
			
			if(p.getPropertyId() == propertyId) return p;
			
		}
		
		throw new CustomerException("Customer with Id "+customerId+" doesn't have Property with Id "+propertyId);
	}
	
	@Override
	public Deal addNewDealById(Integer customerId, Deal deal) {
		
		Customer customer = viewCustomerById(customerId);
		
		//Verify
		
		customer.getListOfDeals().add(deal);
		
		customer = cr.save(customer);
		
		return deal;
		
	}

	@Override
	public Deal editCustomerDealById(Integer customerId, Deal deal) {
		
		Customer customer = viewCustomerById(customerId);
		
		//Verify
		
		Deal updatedDeal = null;
		
		for(Deal d: customer.getListOfDeals()) {
			
			if(deal.getDealid() == d.getDealid()) {
				
				updatedDeal = d;
				d = deal;
				break;
				
			}
			
		}
		
		if(updatedDeal == null) throw new CustomerException("Customer with Id "+customerId+" doesn't have Deal with Id "+deal.getDealid());
		
		customer = cr.save(customer);
		
		return deal;

	}	
	
}
