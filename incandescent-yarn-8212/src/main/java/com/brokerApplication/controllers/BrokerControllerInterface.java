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

public interface BrokerControllerInterface {
	
	//broker.service
	public ResponseEntity<Broker> registerBrokerHandler(@RequestBody Broker broker);
	public ResponseEntity<Broker> getBrokerByIdHandler(@PathVariable Integer id,@RequestParam String key);
	public ResponseEntity<Broker> updateBrokerByIdHandler(@PathVariable Integer id, Broker broker);
	public ResponseEntity<Broker> deleteBrokerByIdHandler(@PathVariable Integer id);
	public ResponseEntity<BrokerNotification> seeBrokerNotificationById(@PathVariable Integer brokerId, @RequestParam Integer notificationId,@RequestHeader("Auth") String key);

	//property.service
	public ResponseEntity<Property> registerPropertyBrokerHandler(@PathVariable Integer broid, @RequestParam String key,@RequestBody Property property);
	public ResponseEntity<List<Property>> getAllPropertiesOfBrokerHandler(@PathVariable Integer broid,@RequestParam String key);
	public ResponseEntity<List<Deal>> getAllDealsOfBrokerHandler(@PathVariable Integer brokerId,@RequestParam String key);
	public ResponseEntity<Property> getBrokerPropertyById(@PathVariable Integer brokerId,  @RequestParam String key, @RequestParam Integer propertyId);

	//Deal manager handlers
	//Deal service layer
	public ResponseEntity<Deal> negotiateDeal(@RequestHeader("Auth") String key,@RequestBody BrokerOffer bf);
	public ResponseEntity<Deal> acceptDeal(@RequestHeader("Auth") String key,@RequestBody BrokerOffer brokerOffer);
	public ResponseEntity<Deal> rejectDeal(@PathVariable Integer brokerid,@RequestParam String key,@PathVariable Integer dealid);
	
}
