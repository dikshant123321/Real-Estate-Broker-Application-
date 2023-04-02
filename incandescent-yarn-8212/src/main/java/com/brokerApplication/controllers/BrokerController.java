package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.BrokerNotification;
import com.brokerApplication.entities.BrokerOffer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.AuthorizationException;
import com.brokerApplication.services.AuthorizationService;
import com.brokerApplication.services.BrokerServices;
import com.brokerApplication.services.DealService;
import com.brokerApplication.services.PropertyService;

import jakarta.validation.Valid;

@RestController
public class BrokerController implements BrokerControllerInterface {
	
	@Autowired
	private BrokerServices brokerServices;
	
	@Autowired
	AuthorizationService as;
	
	@Autowired
	DealService ds;
	
	@Autowired
	PropertyService ps;
	
	@Override
	@PostMapping("/brokers/signup")
	public ResponseEntity<Broker> registerBrokerHandler(@Valid @RequestBody Broker broker){
		
		Broker newBroker = brokerServices.addBroker(broker);
		return new ResponseEntity<>(newBroker, HttpStatus.CREATED);
	}
	
	@Override
	@PutMapping("/brokers/{Id}")
	@ResponseBody
	public ResponseEntity<Broker> updateBrokerByIdHandler(@PathVariable Integer id, @Valid @RequestBody Broker broker){
		Broker newbroker = brokerServices.editBroker(broker);
		return new ResponseEntity<>(newbroker,HttpStatus.OK);
	}
	
//	@Override
//	@DeleteMapping("/brokers/{id}")
//	public ResponseEntity<Broker> deleteBrokerByIdHandler(@PathVariable Integer id){
//		Broker broker = brokerServices.removeBrokerById(id);
//		return new ResponseEntity<>(broker,HttpStatus.OK);
//	}
	
	@Override
	@PostMapping("/brokers/property/{brokerid}")
	public ResponseEntity<Property> registerPropertyBrokerHandler(@PathVariable Integer brokerid,@RequestParam String key,@RequestBody Property property) throws AuthorizationException {
		as.Auth(brokerid, key);	
		System.out.println(property);
		Property p = ps.addProperty(property, brokerid);
		
		
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}

	@Override
	@PostMapping("/brokers/deals")
	public ResponseEntity<Deal> negotiateDeal(@RequestParam String key,@RequestBody BrokerOffer brokerOffer)throws AuthorizationException {
		as.Auth(brokerOffer.getBrokerId(),key);
		return new ResponseEntity<Deal>(ds.setDealOfferFromBroker(brokerOffer),HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/brokers/{brokerId}/deals/{dealId}/accept")
	public ResponseEntity<Deal> acceptDeal(@PathVariable Integer brokerId, @PathVariable Integer dealId ,@RequestParam String key)throws AuthorizationException {
		as.Auth(brokerId, key);	
		return new ResponseEntity<Deal>(ds.approveDealForBroker(dealId, brokerId),HttpStatus.ACCEPTED);
	}
	
	@Override
	@PostMapping("/broker/{brokerId}/deals/{dealId}/reject")
	public ResponseEntity<Deal> rejectDeal(@PathVariable Integer brokerId,@RequestParam String key,@PathVariable Integer dealId)throws AuthorizationException {
		as.Auth(brokerId, key);	
		return new ResponseEntity<Deal>(ds.abandonedDealForBroker(dealId, brokerId),HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/brokers/{brokerId}")
	public ResponseEntity<Broker> getBrokerByIdHandler(@PathVariable Integer brokerId,@RequestParam String key) throws AuthorizationException{
		as.Auth(brokerId, key);	
		Broker broker = brokerServices.viewBrokerById(brokerId);
		return new ResponseEntity<>(broker,HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/brokers/allProperties/{brokerid}")
	public ResponseEntity<List<Property>> getAllPropertiesOfBrokerHandler(@PathVariable Integer brokerid,@RequestParam String key)
			throws AuthorizationException {
	
		as.Auth(brokerid, key);	
		return new ResponseEntity<List<Property>>(brokerServices.getListOfPropertiesById(brokerid),HttpStatus.OK);
	
	}
	
	@Override
	@GetMapping("/brokers/deal/{deal}/{brokerId}")
	public ResponseEntity<List<Deal>> getAllDealsOfBrokerHandler(@PathVariable Integer brokerId,@RequestParam String key)
			throws AuthorizationException {
		as.Auth(brokerId, key);	
		return new ResponseEntity<List<Deal>>(brokerServices.viewAllDealsByBrokerId(brokerId),HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/brokers/property/{brokerId}")
	public ResponseEntity<Property> getBrokerPropertyById(@PathVariable Integer brokerId,  @RequestParam String key, @RequestParam Integer propertyId) {
		
		as.Auth(brokerId, key);
		Property property = brokerServices.getBrokerPropertyById(brokerId, propertyId);
		
		return new ResponseEntity<>(property, HttpStatus.OK);
		
	}
	
	@Override
	@GetMapping("/broker/{brokerId}/notification")
	public ResponseEntity<BrokerNotification> seeBrokerNotificationById(@PathVariable Integer brokerId, @RequestParam Integer notificationId,@RequestParam String key){
	
		as.Auth(brokerId, key);
		BrokerNotification brokerNotification = brokerServices.seeBrokerNotificationById(brokerId, notificationId);
		
		return new ResponseEntity<>(brokerNotification, HttpStatus.OK);
		
	}

	@Override
	@GetMapping("/broker/{brokerId}/notifications")
	public ResponseEntity<List<BrokerNotification>> viewAllNotifications(@PathVariable Integer brokerId, @RequestParam String key) {
		as.Auth(brokerId, key);
		return new ResponseEntity<List<BrokerNotification>>(brokerServices.viewAllBrokerNotificationById(brokerId), HttpStatus.OK);
	}
	
}
