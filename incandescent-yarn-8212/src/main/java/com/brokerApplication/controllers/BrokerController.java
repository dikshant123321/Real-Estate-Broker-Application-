package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

@RestController
public class BrokerController implements BrokerControllerInterface {
	
	@Autowired
	private BrokerServices brokerServices;
	
	@Autowired
	AuthorizationService as;
	
	@Autowired
	DealService ds;
	
	@Override
	@PostMapping("/brokers/signup")
	public ResponseEntity<Broker> registerBrokerHandler(@RequestBody Broker broker){
		
		Broker newBroker = brokerServices.addBroker(broker);
		return new ResponseEntity<>(newBroker, HttpStatus.CREATED);
	}
	
	@Override
	@PutMapping("/brokers/{Id}")
	@ResponseBody
	public ResponseEntity<Broker> updateBrokerByIdHandler(@PathVariable Integer id, Broker broker){
		Broker newbroker = brokerServices.editBroker(broker);
		return new ResponseEntity<>(newbroker,HttpStatus.OK);
	}
	
	@Override
	@DeleteMapping("/brokers/{id}")
	public ResponseEntity<Broker> deleteBrokerByIdHandler(@PathVariable Integer id){
		Broker broker = brokerServices.removeBrokerById(id);
		return new ResponseEntity<>(broker,HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/brokers/properties/{brokerid}")
	public ResponseEntity<Property> registerPropertyBrokerHandler(@PathVariable Integer brokerid,@RequestHeader("Auth") String key,@RequestBody Property property) throws AuthorizationException {
		as.Auth(brokerid, key);	
		return null;
	}

	@Override
	@PostMapping("/brokers/deals/")
	public ResponseEntity<Deal> negotiateDeal(@RequestHeader("Auth") String key,@RequestBody BrokerOffer brokerOffer)throws AuthorizationException {
		as.Auth(brokerOffer.getBrokerId(),key);
		return new ResponseEntity<Deal>(ds.setDealOfferFromBroker(brokerOffer),HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/brokers/deals/accept")
	public ResponseEntity<Deal> acceptDeal(@RequestHeader("Auth") String key,@RequestBody BrokerOffer brokerOffer)throws AuthorizationException {
		as.Auth(brokerOffer.getBrokerId(), key);	
		return new ResponseEntity<Deal>(ds.approveDealForBroker(brokerOffer),HttpStatus.ACCEPTED);
	}
	
	@Override
	@PostMapping("/brokers/deals/reject")
	public ResponseEntity<Deal> rejectDeal(@PathVariable Integer brokerid,@RequestHeader("Auth") String key,@PathVariable Integer dealid)throws AuthorizationException {
		as.Auth(brokerid, key);	
		return new ResponseEntity<Deal>(ds.abandonedDealForBroker(dealid, brokerid),HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/brokers/{id}")
	public ResponseEntity<Broker> getBrokerByIdHandler(@PathVariable Integer id,@RequestHeader("Auth") String key) throws AuthorizationException{
		as.Auth(id, key);	
		Broker broker = brokerServices.viewBrokerById(id);
		return new ResponseEntity<>(broker,HttpStatus.FOUND);
	}
	
	@Override
	@GetMapping("/brokers/properties/{brokerid}")
	public ResponseEntity<List<Property>> getAllPropertiesOfBrokerHandler(@PathVariable Integer brokerid,@RequestHeader("Auth") String key)
			throws AuthorizationException {
	
		as.Auth(brokerid, key);	
		return new ResponseEntity<List<Property>>(brokerServices.getListOfPropertiesById(brokerid),HttpStatus.OK);
	
	}
	
	@Override
	@GetMapping("/brokers/deals/{brokerid}")
	public ResponseEntity<List<Deal>> getAllDealsOfBrokerHandler(@PathVariable Integer brokerid,@RequestHeader("Auth") String key)
			throws AuthorizationException {
		as.Auth(brokerid, key);	
		return new ResponseEntity<List<Deal>>(brokerServices.viewAllDealsByBrokerId(brokerid),HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/brokers/deals/{brokerid}")
	public ResponseEntity<Property> getBrokerPropertyById(@PathVariable Integer brokerId,  @RequestParam("authKey") String key, @RequestParam Integer propertyId) {
		
		as.Auth(brokerId, key);
		Property property = brokerServices.getBrokerPropertyById(brokerId, propertyId);
		
		return new ResponseEntity<>(property, HttpStatus.FOUND);
		
	}
	
	@Override
	@GetMapping("/broker/notifications/{brokerId}")
	public ResponseEntity<BrokerNotification> seeBrokerNotificationById(@PathVariable Integer brokerId, @RequestParam Integer notificationId,@RequestParam("authKey") String key){
	
		as.Auth(brokerId, key);
		BrokerNotification brokerNotification = brokerServices.seeBrokerNotificationById(brokerId, notificationId);
		
		return new ResponseEntity<>(brokerNotification, HttpStatus.FOUND);
		
	}
	
}
