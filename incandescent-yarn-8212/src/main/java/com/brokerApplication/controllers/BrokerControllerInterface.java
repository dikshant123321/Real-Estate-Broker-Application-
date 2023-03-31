package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.BrokerOffer;
import com.brokerApplication.entities.CustomerOffer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.AuthorizationException;

public interface BrokerControllerInterface {
	public ResponseEntity<Broker> registerBrokerHandler(@RequestBody Broker broker);
	public ResponseEntity<Broker> updateBrokerByIdHandler(@PathVariable Integer id, Broker broker);
	public ResponseEntity<Broker> deleteBrokerByIdHandler(@PathVariable Integer id);
	
	public ResponseEntity<Property> registerPropertyBrokerHandler(@PathVariable Integer broid,@RequestHeader("Auth") String key,@RequestBody Property property)throws AuthorizationException;
	
	public ResponseEntity<List<Property>> brokerHandlerProperties(@PathVariable Integer broid,@RequestHeader("Auth") String key)throws AuthorizationException;
	public ResponseEntity<List<Deal>> brokerHandlerDeals(@PathVariable Integer broid,@RequestHeader("Auth") String key)throws AuthorizationException;
	
	
	public ResponseEntity<Deal> negotiateDeal(@RequestHeader("Auth") String key,@RequestBody BrokerOffer bf)throws AuthorizationException;
	//new fun
	
	
	public ResponseEntity<String> acceptDeal(@RequestHeader("Auth") String key,@RequestBody BrokerOffer brokerOffer)throws AuthorizationException;
	public ResponseEntity<String> rejectDeal(@PathVariable Integer brokerid,@RequestHeader("Auth") String key,@PathVariable Integer dealid)throws AuthorizationException;
	

}
