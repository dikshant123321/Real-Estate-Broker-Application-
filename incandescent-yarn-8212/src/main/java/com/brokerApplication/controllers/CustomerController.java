package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.CustomerOffer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.PropertyException;
import com.brokerApplication.services.AuthorizationService;
import com.brokerApplication.services.CustomerService;
import com.brokerApplication.services.DealService;
import com.brokerApplication.services.PropertyService;

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

	@PostMapping("/customers/signup")
	public ResponseEntity<Customer> createCustomerAccount(@RequestBody Customer customer) {
		Customer newCustomer = cs.addCustomer(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> viewCustomerProfileById(@PathVariable Integer customerId,@RequestParam String Key){
		as.Auth(customerId,Key);
		return new  ResponseEntity<Customer>(cs.viewCustomerById(customerId),HttpStatus.OK);
	}

	@PostMapping("/customers/edit")
	public ResponseEntity<Customer> editCustomerProfile(@RequestBody Customer customer) {
		return new  ResponseEntity<Customer>(cs.editCustomer(customer),HttpStatus.OK);
	}

	@PostMapping("/customers/delete/{customerId}")
	public ResponseEntity<Customer> deleteCustomerAccountById(@PathVariable Integer customerId,@RequestParam  String Key) {
		as.Auth(customerId,Key);
		return new  ResponseEntity<Customer>(cs.removeCustomer(customerId),HttpStatus.OK);
	}

	@GetMapping("/customers/properties/{id}")
	public ResponseEntity<List<Property>> viewAllProptiesByCustomerId(@PathVariable Integer id,@RequestParam  String Key) {
		as.Auth(id,Key);
		return new  ResponseEntity<List<Property>>(cs.viewAllProptiesByCustomerId(id),HttpStatus.OK);
	}

	@GetMapping("/customers/deals/{id}")
	public ResponseEntity<List<Deal>> viewAllDealsByCustomerId(@PathVariable Integer id,@RequestParam  String Key) {
		as.Auth(id,Key);
		return new  ResponseEntity<List<Deal>>(cs.viewAllDealsByCustomerId(id),HttpStatus.OK);
	}

//	@Override
//	public ResponseEntity<Property> addNewPropertyById(Integer customerId, Property property) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@GetMapping("/customers/property/{customerId}/{propertyId}")
	public ResponseEntity<Property> viewCustomerPropertyById(@PathVariable Integer customerId,@PathVariable Integer propertyId,@RequestParam String key) {
		as.Auth(customerId, key);
		return new  ResponseEntity<Property>(cs.viewCustomerPropertyById(customerId, propertyId),HttpStatus.OK);
	}

	@GetMapping("/customers/property/{propertyId}")
	public ResponseEntity<Property> viewPropertyById(@PathVariable Integer propertyId) throws PropertyException {
		return new  ResponseEntity<Property>(ps.viewPropertyById(propertyId),HttpStatus.OK);
		
	}

	@GetMapping("/customers/properties")
	public ResponseEntity<List<Property>> viewListOfProperties() throws PropertyException {
		return new  ResponseEntity<List<Property>>(ps.ListAllPropertys(),HttpStatus.OK);
	}

	@PostMapping("/customers/addDeal")
	public ResponseEntity<Deal> addDealOfferFromCustomer(@RequestBody CustomerOffer customerOffer) {
		return new ResponseEntity<Deal>(ds.addDealOfferFromCustomer(customerOffer),HttpStatus.OK);
	}

	@PostMapping("/customers/editDeal")
	public ResponseEntity<Deal> editDealOfferFromCustomerByDealId(@PathVariable Integer dealId,@RequestBody CustomerOffer customerOffer) {
		return new ResponseEntity<Deal>(ds.editDealOfferFromCustomerByDealId(dealId, customerOffer),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Deal> acceptDealForCustomer(Integer dealId, Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Deal> rejectDealForCustomer(Integer dealId, Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Deal> payBillForDeal(PaymentDetails paymentDetails) {
		// TODO Auto-generated method stub
		return null;
	}

//	@GetMapping("/customers/acceptDeal/{dealId}/{customerId}")
//	public ResponseEntity<Deal> acceptDealForCustomer(@PathVariable Integer dealId,@PathVariable Integer customerId) {
//		return new ResponseEntity<Deal>(ds.,HttpStatus.OK);
//	}
//
//	@GetMapping("/customers/rejectDeal/{dealId}/{customerId}")
//	public ResponseEntity<Deal> rejectDealForCustomer(@PathVariable Integer dealId,@PathVariable Integer customerId) {
//		return new ResponseEntity<Deal>(ds..OK);
//	}
//
//	@PostMapping("/customers/payBill")
//	public ResponseEntity<Deal> payBillForDeal(@RequestBody PaymentDetails paymentDetails) {
//		return new ResponseEntity<Deal>(ds.,HttpStatus.OK);
//	}
//	
	

	

}