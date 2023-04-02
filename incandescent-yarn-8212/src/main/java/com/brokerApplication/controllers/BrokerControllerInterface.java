package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.BrokerNotification;
import com.brokerApplication.entities.BrokerOffer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.AuthorizationException;

public interface BrokerControllerInterface {
	
	//broker.service
	public ResponseEntity<Broker> registerBrokerHandler(@RequestBody Broker broker);
	public ResponseEntity<Broker> getBrokerByIdHandler(@PathVariable Integer id,@RequestHeader("Auth") String key) throws AuthorizationException;
	public ResponseEntity<Broker> updateBrokerByIdHandler(@PathVariable Integer id, Broker broker);
	public ResponseEntity<Broker> deleteBrokerByIdHandler(@PathVariable Integer id);
	public ResponseEntity<BrokerNotification> seeBrokerNotificationById(@PathVariable Integer brokerId, @RequestParam Integer notificationId,@RequestHeader("Auth") String key);

	//property.service
	public ResponseEntity<Property> registerPropertyBrokerHandler(@PathVariable Integer broid,@RequestHeader("Auth") String key,@RequestBody Property property)throws AuthorizationException;
	public ResponseEntity<List<Property>> getAllPropertiesOfBrokerHandler(@PathVariable Integer broid,@RequestHeader("Auth") String key)throws AuthorizationException;
	public ResponseEntity<List<Deal>> getAllDealsOfBrokerHandler(@PathVariable Integer brokerId,@RequestHeader("Auth") String key)throws AuthorizationException;
	public ResponseEntity<Property> getBrokerPropertyById(@PathVariable Integer brokerId,  @RequestHeader("Auth") String key, @RequestParam Integer propertyId);

	//Deal manager handlers
	//Deal service layer
	public ResponseEntity<Deal> negotiateDeal(@RequestHeader("Auth") String key,@RequestBody BrokerOffer bf)throws AuthorizationException;
	public ResponseEntity<Deal> acceptDeal(@RequestHeader("Auth") String key,@RequestBody BrokerOffer brokerOffer)throws AuthorizationException;
	public ResponseEntity<Deal> rejectDeal(@PathVariable Integer brokerid,@RequestHeader("Auth") String key,@PathVariable Integer dealid)throws AuthorizationException;
	
}
