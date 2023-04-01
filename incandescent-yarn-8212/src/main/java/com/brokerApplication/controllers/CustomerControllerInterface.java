package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.CustomerOffer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface CustomerControllerInterface {
	
	public ResponseEntity<Customer> createCustomerAccount(@RequestBody Customer customer);
	public ResponseEntity<Customer> viewCustomerProfileById(Integer id);
	public ResponseEntity<Customer> editCustomerProfile(Customer customer);
	public ResponseEntity<Customer> deleteCustomerAccountById(Integer id);
	public ResponseEntity<List<Property>> viewAllCustomerPropertiesById(Integer id);
	public ResponseEntity<List<Deal>> viewAllCustomerDealsById(Integer id);
	
	public ResponseEntity<Deal> requestDealToBroker(CustomerOffer customerOffer);
	public ResponseEntity<Deal> viewDealByCustomerIdAndDealId(Integer customerId, Integer dealId);
	public ResponseEntity<Deal> editDealByCustomerIdAndDealId(Integer customerId, Integer dealId);
	
	public ResponseEntity<PaymentReceipt> makeAPaymentByDealIdAndCustomerId(PaymentDetails paymentDeatils, Integer customerId, Integer dealId);
	
	public ResponseEntity<Deal> acceptTheDealOfferFromBroker();
}
