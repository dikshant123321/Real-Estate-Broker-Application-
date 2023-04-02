package com.brokerApplication.services;

import java.util.List;
import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.CustomerNotification;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;


public interface CustomerService {

	public Customer addCustomer(Customer c);
	public Customer editCustomer(Customer c);
	public Customer removeCustomer(Integer id);
	public Customer viewCustomerById(Integer id);
	public CustomerNotification seeCustomerNotificationByBy(Integer customerId, Integer notificationId);
	public List<CustomerNotification> viewCustomerAllNotificationById(Integer customerId);
	public List<Property> viewAllProptiesByCustomerId(Integer id);
	public List<Deal> viewAllDealsByCustomerId(Integer id);
	public Property viewCustomerPropertyById(Integer customerId, Integer propertyId);
	
	public List<Customer> viewAllCustomers();

	public Property addNewPropertyById(Integer customerId, Property property);
	public Property removeCustomerPropertyById(Integer customerId, Integer propertyId);
	public Deal addNewDealById(Integer customerId, Deal deal);
	public Deal editCustomerDealById(Integer customerId, Deal deal);
	public Deal deleteCustomerDealById(Integer customerId, Integer dealId);
	public void sendNotificationToCustomerAboutDeal(Integer customerId, CustomerNotification customerNotification);
	
}
