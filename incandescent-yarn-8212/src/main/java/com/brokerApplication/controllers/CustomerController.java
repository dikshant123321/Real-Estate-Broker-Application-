package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.CustomerOffer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.services.CustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController implements CustomerControllerInterface {
	
	@Autowired
	CustomerService customerServices;
	
	@PostMapping("/customers/signup")
	public ResponseEntity<Customer> createCustomerAccount(@Valid @RequestBody Customer customer) {
		Customer newCustomer = customerServices.addCustomer(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> viewCustomerProfileById(@PathVariable Integer id) {
		Customer newCustomer = customerServices.viewCustomerById(id);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Customer> editCustomerProfile(@Valid @RequestBody Customer customer) {
		Customer newCustomer = customerServices.editCustomer(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Customer> deleteCustomerAccountById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Property>> viewAllCustomerPropertiesById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Deal>> viewAllCustomerDealsById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Deal> requestDealToBroker(CustomerOffer customerOffer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Deal> viewDealByCustomerIdAndDealId(Integer customerId, Integer dealId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Deal> editDealByCustomerIdAndDealId(Integer customerId, Integer dealId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PaymentReceipt> makeAPaymentByDealIdAndCustomerId(PaymentDetails paymentDeatils,
			Integer customerId, Integer dealId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Deal> acceptTheDealOfferFromBroker() {
		// TODO Auto-generated method stub
		return null;
	}


}
