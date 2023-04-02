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
	
	public ResponseEntity<Customer> createCustomerAccount(Customer customer);//1
	public ResponseEntity<Customer> viewCustomerProfileById(Integer customerId, String Key);//2
	public ResponseEntity<Customer> editCustomerProfile(Customer customer, String Key);//3
	public ResponseEntity<Customer> deleteCustomerAccountById(Integer customerId, String Key);//4
	public ResponseEntity<List<Property>> viewAllProptiesByCustomerId(Integer customerId, String Key);//5
	public ResponseEntity<List<Deal>> viewAllDealsByCustomerId(Integer customerId, String Key);//6
	public ResponseEntity<Property> viewCustomerPropertyById(Integer customerId, Integer propertyId, String Key);//7
	public ResponseEntity<CustomerNotification> seeCustomerNotificationByBy(Integer customerId, Integer notificationId, String Key);//8

	public ResponseEntity<Property> viewPropertyById(Integer propertyId)throws PropertyException;//9
	public ResponseEntity<List<Property>> viewListOfProperties()throws PropertyException;//10
	
	public ResponseEntity<Deal> addDealOfferFromCustomer(CustomerOffer customerOffer, String Key);//11
	public ResponseEntity<Deal> editDealOfferFromCustomerByDealId(Integer dealId, CustomerOffer customerOffer, String Key);//12
	public ResponseEntity<Deal> acceptDealForCustomer(Integer dealId, Integer customerId, String Key);//13
	public ResponseEntity<Deal> rejectDealForCustomer(Integer dealId, Integer customerId, String Key);//14
	public ResponseEntity<Deal> payBillForDeal(PaymentDetails paymentDetails, String Key);//15
	
}
