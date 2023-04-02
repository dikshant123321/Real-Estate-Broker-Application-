package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.CustomerNotification;
import com.brokerApplication.entities.CustomerOffer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.PaymentDetails;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.PropertyException;

public interface CustomerControllerInterface {
	
	public ResponseEntity<Customer> createCustomerAccount(Customer customer);
	public ResponseEntity<Customer> viewCustomerProfileById(Integer customerId);
	public ResponseEntity<Customer> editCustomerProfile(Customer customer);
	public ResponseEntity<Customer> deleteCustomerAccountById(Integer customerId);
	public ResponseEntity<List<Property>> viewAllProptiesByCustomerId(Integer id);
	public ResponseEntity<List<Deal>> viewAllDealsByCustomerId(Integer id);
	public ResponseEntity<Property> viewCustomerPropertyById(Integer customerId, Integer propertyId);
	public ResponseEntity<CustomerNotification> seeCustomerNotificationByBy(Integer customerId, Integer notificationId);

	public ResponseEntity<Property> viewPropertyById(Integer propertyId)throws PropertyException;
	public ResponseEntity<List<Property>> viewListOfProperties()throws PropertyException;
	
	public ResponseEntity<Deal> addDealOfferFromCustomer(CustomerOffer customerOffer);
	public ResponseEntity<Deal> editDealOfferFromCustomerByDealId(Integer dealId, CustomerOffer customerOffer);
	public ResponseEntity<Deal> acceptDealForCustomer(Integer dealId, Integer customerId);
	public ResponseEntity<Deal> rejectDealForCustomer(Integer dealId, Integer customerId);
	public ResponseEntity<Deal> payBillForDeal(PaymentDetails paymentDetails);
	
}
