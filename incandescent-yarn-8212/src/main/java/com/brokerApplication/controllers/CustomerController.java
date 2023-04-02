package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.CustomerNotification;
import com.brokerApplication.entities.CustomerOffer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.PaymentDetails;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.PropertyException;

@RestController
@RequestMapping("/customer_sec")
public class CustomerController implements CustomerControllerInterface{

	@Override
	public ResponseEntity<Customer> createCustomerAccount(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Customer> viewCustomerProfileById(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Customer> editCustomerProfile(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Customer> deleteCustomerAccountById(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Property>> viewAllProptiesByCustomerId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Deal>> viewAllDealsByCustomerId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Property> viewCustomerPropertyById(Integer customerId, Integer propertyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<CustomerNotification> seeCustomerNotificationByBy(Integer customerId,
			Integer notificationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Property> viewPropertyById(Integer propertyId) throws PropertyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Property>> viewListOfProperties() throws PropertyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Deal> addDealOfferFromCustomer(CustomerOffer customerOffer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Deal> editDealOfferFromCustomerByDealId(Integer dealId, CustomerOffer customerOffer) {
		// TODO Auto-generated method stub
		return null;
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

}
