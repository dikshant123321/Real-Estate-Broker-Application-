package com.brokerApplication.controllers;

import java.util.ArrayList;
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
import com.brokerApplication.entities.BrokerOffer;
import com.brokerApplication.entities.CustomerOffer;
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
	
	private List<Broker> brokers= new ArrayList<>();
	
	@PostMapping("/brokers/signup")
	public ResponseEntity<Broker> registerBrokerHandler(@RequestBody Broker broker){
		
		Broker newBroker = brokerServices.addBroker(broker);
		return new ResponseEntity<>(newBroker, HttpStatus.CREATED);
	}
	@PutMapping("/brokers/{Id}")
	@ResponseBody
	public ResponseEntity<Broker> updateBrokerByIdHandler(@PathVariable Integer id, Broker broker){
		Broker newbroker = brokerServices.editBroker(broker);
		return new ResponseEntity<>(newbroker,HttpStatus.OK);
	}
	@DeleteMapping("/brokers/{id}")
	public ResponseEntity<Broker> deleteBrokerByIdHandler(@PathVariable Integer id){
		Broker broker = brokerServices.removeBrokerById(id);
		return new ResponseEntity<>(broker,HttpStatus.OK);
	}
	
	@PostMapping("/brokers/properties/{brokerid}")
	public ResponseEntity<Property> registerPropertyBrokerHandler(@PathVariable Integer brokerid,@RequestHeader("Auth") String key,@RequestBody Property property) throws AuthorizationException {
		as.Auth(brokerid, key);	
		
		return null;
	}
	
	@GetMapping("/brokers/properties/{brokerid}")
	public ResponseEntity<List<Property>> brokerHandlerProperties(@PathVariable Integer brokerid,@RequestHeader("Auth") String key)throws AuthorizationException{
		as.Auth(brokerid, key);	
		return new ResponseEntity<List<Property>>(brokerServices.listBrokerHandlerProperties(brokerid),HttpStatus.OK);
	}
	@GetMapping("/brokers/deals/{brokerid}")
	public ResponseEntity<List<Deal>> brokerHandlerDeals(@PathVariable Integer brokerid,@RequestHeader("Auth") String key) throws AuthorizationException {
		as.Auth(brokerid, key);	
		return new ResponseEntity<List<Deal>>(brokerServices.listBrokerHandlerDeals(brokerid),HttpStatus.OK);
	}

	@PostMapping("/brokers/deals/")
	public ResponseEntity<Deal> negotiateDeal(@RequestHeader("Auth") String key,@RequestBody BrokerOffer brokerOffer)throws AuthorizationException {
		return new ResponseEntity<Deal>(ds.setDealOfferFromBroker(brokerOffer),HttpStatus.OK);
	}
	@PostMapping("/brokers/deals/accept")
	public ResponseEntity<String> acceptDeal(@RequestHeader("Auth") String key,@RequestBody BrokerOffer brokerOffer)throws AuthorizationException {
		as.Auth(brokerOffer.getBrokerId(), key);	
		return new ResponseEntity<String>(ds.approveDeal(brokerOffer),HttpStatus.ACCEPTED);
	}
	@PostMapping("/brokers/deals/reject")
	public ResponseEntity<String> rejectDeal(@PathVariable Integer brokerid,@RequestHeader("Auth") String key,@PathVariable Integer dealid)throws AuthorizationException {
		// TODO Auto-generated method stub
		return new ResponseEntity<String>(ds.AbandonedDeal(dealid,brokerid),HttpStatus.OK);
	}
	
	@GetMapping("/brokers/{id}")
	public ResponseEntity<Broker> getBrokerByIdHandler(@PathVariable Integer id){
		Broker broker = brokerServices.viewBrokerById(id);
		return new ResponseEntity<>(broker,HttpStatus.FOUND);
	}
		
	
	
	
	
	
}
