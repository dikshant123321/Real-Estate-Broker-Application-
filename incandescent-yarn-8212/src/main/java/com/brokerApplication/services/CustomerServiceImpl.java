package com.brokerApplication.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.BrokerNotification;
import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.CustomerNotification;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.NotificationSatus;
import com.brokerApplication.entities.Property;
import com.brokerApplication.entities.UserRoleType;
import com.brokerApplication.exceptions.BrokerException;
import com.brokerApplication.exceptions.CustomerException;
import com.brokerApplication.repositorys.CustomerNotificationRepository;
import com.brokerApplication.repositorys.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository cr;

	@Autowired
	private BrokerServices bs;
	
	@Autowired
	private CustomerNotificationRepository cnr;
	
	@Override
	public Customer addCustomer(Customer c) {

		if(c == null) throw new CustomerException("Customer can not be null");
		c.setRole(UserRoleType.Customer);
		if(c.getRole()!=UserRoleType.Customer) throw new CustomerException("Customer can't be converted to broker");
		c = cr.save(c);
		
		return c;

	}

	@Override
	public Customer editCustomer(Customer c) {

		if(c == null) throw new CustomerException("Customer can not be null");
		if(c.getRole()!=UserRoleType.Customer) throw new CustomerException("Customer can't be converted to broker");
		
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

	@Override
	public Deal deleteCustomerDealById(Integer customerId, Integer dealId) {

		Customer customer = viewCustomerById(customerId);
		
		for(Deal d: customer.getListOfDeals()) {
			
			if(d.getDealid() == dealId) {
				
				Deal deal = d;
				customer.getListOfDeals().remove(d);
				cr.save(customer);
				bs.sendNotificationToBrokerAboutDeal(deal.getBroker().getUserId(), new BrokerNotification(deal.getBroker().getUserId(), dealId, LocalDateTime.now(), null));;
				return d;
				
			}
			
		}
		
		throw new BrokerException("Customer with Id: "+customerId+" does not have Deal with id: "+dealId);
		
	}	
	
	@Override
	public Property removeCustomerPropertyById(Integer customerId, Integer propertyId) {

		Customer customer = viewCustomerById(customerId);
		
		for(Property p: customer.getListOfProperties()) {
			
			if(p.getPropertyId() == propertyId) {
				
				customer.getListOfDeals().remove(p);
				cr.save(customer);
				return p;
				
			}
			
		}
		
		throw new BrokerException("Customer with Id: "+customerId+" does not have Property with id: "+propertyId);
		
	}
	
	@Override
	public void sendNotificationToCustomerAboutDeal(Integer customerId,CustomerNotification customerNotification) {
		
		Customer customer = viewCustomerById(customerId);
		
		customer.getNotifications().add(customerNotification);
		cr.save(customer);
		
	}
	
	@Override
	public CustomerNotification seeCustomerNotificationByBy(Integer customerId, Integer notificationId) {
		
		Customer customer = viewCustomerById(customerId);
		
		for(CustomerNotification cn: customer.getNotifications()) {
			
			if(cn.getCustomerNotificationId() == notificationId) {
				
				cn.setNotificationSatus(NotificationSatus.SEEN);
				cr.save(customer);
				
				return cnr.save(cn);
			}
			
		}
		
		throw new CustomerException("Customer with Id "+customerId+" has no Notification with Id "+notificationId);
	
	}

	@Override
	public List<CustomerNotification> viewCustomerAllNotificationById(Integer customerId) {
		
		Customer customer = viewCustomerById(customerId);
		
		return customer.getNotifications();
	}
	
}
