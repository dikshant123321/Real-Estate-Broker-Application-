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
	public ResponseEntity<Customer> viewCustomerProfileById(Integer customerId, String Key);
	public ResponseEntity<Customer> editCustomerProfile(Customer customer, String Key);
//	public ResponseEntity<Customer> deleteCustomerAccountById(Integer customerId, String Key);
	public ResponseEntity<List<Property>> viewAllProptiesByCustomerId(Integer customerId, String Key);
	public ResponseEntity<List<Deal>> viewAllDealsByCustomerId(Integer customerId, String Key);
	public ResponseEntity<Property> viewCustomerPropertyById(Integer customerId, Integer propertyId, String Key);
	public ResponseEntity<CustomerNotification> seeCustomerNotificationByBy(Integer customerId, Integer notificationId, String Key);
	public ResponseEntity<List<CustomerNotification>> viewCustomerAllNotificationbyId(Integer customerId, String key);
	
	public ResponseEntity<Property> viewPropertyById(Integer propertyId)throws PropertyException;
	public ResponseEntity<List<Property>> viewListOfProperties()throws PropertyException;
	
	public ResponseEntity<Deal> addDealOfferFromCustomer(CustomerOffer customerOffer, String Key);
	public ResponseEntity<Deal> editDealOfferFromCustomerByDealId(Integer dealId, CustomerOffer customerOffer, String Key);
	public ResponseEntity<Deal> acceptDealForCustomer(Integer dealId, Integer customerId, String Key);
	public ResponseEntity<Deal> rejectDealForCustomer(Integer dealId, Integer customerId, String Key);
	public ResponseEntity<Deal> payBillForDeal(PaymentDetails paymentDetails, String Key);
	public ResponseEntity<Deal> deleteDealOfferForCustomer(Integer dealId, Integer customerId, String key);
}
