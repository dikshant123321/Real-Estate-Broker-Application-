package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.CustomerNotification;
import com.brokerApplication.entities.CustomerOffer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.PaymentDetails;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.PropertyException;
import com.brokerApplication.services.AuthorizationService;
import com.brokerApplication.services.BillingService;
import com.brokerApplication.services.CustomerService;
import com.brokerApplication.services.DealService;
import com.brokerApplication.services.PropertyService;
import jakarta.validation.Valid;

@RestController
public class CustomerController implements CustomerControllerInterface{
	
	@Autowired
	CustomerService cs;
	
	@Autowired
	PropertyService ps;
	
	@Autowired
	DealService ds;
	
	@Autowired
	AuthorizationService as;
	
	@Autowired
	BillingService bls;
	
	@Override
	@PostMapping("/customers/signup")
	public ResponseEntity<Customer> createCustomerAccount(@Valid @RequestBody Customer customer) {

		Customer newCustomer = cs.addCustomer(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
		
	}
	
	@Override
	@GetMapping("/customers/profile/{customerId}")
	public ResponseEntity<Customer> viewCustomerProfileById(@PathVariable Integer customerId, @RequestParam String Key){

		as.Auth(customerId,Key);
		return new  ResponseEntity<Customer>(cs.viewCustomerById(customerId),HttpStatus.OK);
		
	}
	
	@Override
	@PostMapping("/customers/profile/edit")
	public ResponseEntity<Customer> editCustomerProfile(@Valid @RequestBody Customer customer, @RequestParam String Key) {
		
		as.Auth(customer.getUserId(),Key);
		return new  ResponseEntity<Customer>(cs.editCustomer(customer),HttpStatus.OK);
		
	}
	
//	@Override
//	@PostMapping("/customers/delete/{customerId}")
//	public ResponseEntity<Customer> deleteCustomerAccountById(@PathVariable Integer customerId,@RequestParam  String Key) {
//		
//		as.Auth(customerId,Key);
//		return new  ResponseEntity<Customer>(cs.removeCustomer(customerId),HttpStatus.OK);
//		
//	}
	
	@Override
	@GetMapping("/customers/properties/{id}")
	public ResponseEntity<List<Property>> viewAllProptiesByCustomerId(@PathVariable Integer customerId,@RequestParam  String Key) {
		
		as.Auth(customerId,Key);
		return new  ResponseEntity<List<Property>>(cs.viewAllProptiesByCustomerId(customerId),HttpStatus.OK);
		
	}
	
	@Override
	@GetMapping("/customers/{customerId}/deals/{id}")
	public ResponseEntity<List<Deal>> viewAllDealsByCustomerId(@PathVariable Integer customerId,@RequestParam  String Key) {
		
		as.Auth(customerId,Key);
		return new  ResponseEntity<List<Deal>>(cs.viewAllDealsByCustomerId(customerId),HttpStatus.OK);
		
	}
	
	@Override
	@GetMapping("/customers/property/{customerId}/{propertyId}")
	public ResponseEntity<Property> viewCustomerPropertyById(@PathVariable Integer customerId,@PathVariable Integer propertyId,@RequestParam String key) {
		
		as.Auth(customerId, key);
		return new  ResponseEntity<Property>(cs.viewCustomerPropertyById(customerId, propertyId),HttpStatus.OK);
		
	}
	
	@Override
	@GetMapping("/customers/property/{propertyId}")
	public ResponseEntity<Property> viewPropertyById(@PathVariable Integer propertyId) throws PropertyException {
		
		return new  ResponseEntity<Property>(ps.viewPropertyById(propertyId),HttpStatus.OK);
		
	}
	
	@Override
	@GetMapping("/customers/properties")
	public ResponseEntity<List<Property>> viewListOfProperties() throws PropertyException {
		return new  ResponseEntity<List<Property>>(ps.viewListOfProperties(),HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/customers/deal/addDeal")
	public ResponseEntity<Deal> addDealOfferFromCustomer(@Valid @RequestBody CustomerOffer customerOffer, @RequestParam String Key) {
		
		as.Auth(customerOffer.getCustomerId(),Key);
		return new ResponseEntity<Deal>(ds.addDealOfferFromCustomer(customerOffer),HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/customers/editDeal/{dealId}")
	public ResponseEntity<Deal> editDealOfferFromCustomerByDealId(@PathVariable Integer dealId,@RequestBody CustomerOffer customerOffer, @RequestParam String Key) {
		as.Auth(customerOffer.getCustomerId(),Key);
		return new ResponseEntity<Deal>(ds.editDealOfferFromCustomerByDealId(dealId, customerOffer),HttpStatus.OK);
		
	}
	
	@Override
	@GetMapping("/customers/acceptDeal/{dealId}/{customerId}")
	public ResponseEntity<Deal> acceptDealForCustomer(@PathVariable Integer dealId,@PathVariable Integer customerId, @RequestParam String Key) {
		
		as.Auth(customerId,Key);
		return new ResponseEntity<Deal>(ds.acceptDealForCustomer(dealId, customerId) ,HttpStatus.OK);
		
	}
	
	@Override
	@GetMapping("/customers/rejectDeal/{dealId}/{customerId}")
	public ResponseEntity<Deal> rejectDealForCustomer(@PathVariable Integer dealId,@PathVariable Integer customerId, @RequestParam String Key) {
		
		as.Auth(customerId,Key);
		return new ResponseEntity<Deal>(ds.rejectDealForCustomer(dealId, customerId), HttpStatus.OK);
		
	}
	
	@Override
	@PostMapping("/customers/payBill")
	public ResponseEntity<Deal> payBillForDeal(@Valid @RequestBody PaymentDetails paymentDetails, @RequestParam String Key) {
		
		as.Auth(paymentDetails.getCustomerId(), Key);
		return new ResponseEntity<Deal>(bls.payBillForDeal(paymentDetails) ,HttpStatus.OK);
		
	}

	@Override
	@GetMapping("customer/{customerId}/notification")
	public ResponseEntity<CustomerNotification> seeCustomerNotificationByBy(@PathVariable Integer customerId, @RequestParam Integer notificationId,
			@RequestParam String Key) {
		
		as.Auth(customerId, Key);
		CustomerNotification customerNotification = cs.seeCustomerNotificationByBy(customerId, notificationId);		
		return new ResponseEntity<>(customerNotification, HttpStatus.OK);
		
	}

	@Override
	@GetMapping("customer/{customerId}/notifications")
	public ResponseEntity<List<CustomerNotification>> viewCustomerAllNotificationbyId(@PathVariable Integer customerId, @RequestParam String key) {
		as.Auth(customerId, key);
		return new ResponseEntity<List<CustomerNotification>>(cs.viewCustomerAllNotificationById(customerId), HttpStatus.OK);
	}

	@Override
	@DeleteMapping("customer/{customerId}/deal")
	public ResponseEntity<Deal> deleteDealOfferForCustomer(@RequestParam Integer dealId,@PathVariable Integer customerId, @RequestParam String key) {
		as.Auth(customerId, key);
		return new ResponseEntity<Deal>(cs.deleteCustomerDealById(customerId, dealId), HttpStatus.OK);
	}
		
}