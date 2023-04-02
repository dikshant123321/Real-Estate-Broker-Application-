package com.brokerApplication.services;

import java.util.List;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.BrokerNotification;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;

public interface BrokerServices {
	
	//for broker
	public Broker addBroker(Broker broker);
	public Broker viewBrokerById(Integer brokerId);
	public Broker removeBrokerById(Integer brokerId);
	public Broker editBroker(Broker broker);
	public List<Property>  getListOfPropertiesById(Integer brokerId);
	public List<Deal> viewAllDealsByBrokerId(Integer brokerId);
	public Property getBrokerPropertyById(Integer brokerId ,Integer propertyId);
	public BrokerNotification seeBrokerNotificationById(Integer brokerId, Integer notificationId);
	
	//for admin
	public List<Broker> listAllBrokers();

	//internal use only
	public Deal deleteBrokerDealById(Integer brokerId, Integer dealId);
	public Deal editBrokerDealById(Integer brokerId, Deal deal);
	public Deal addBrokerDealById(Integer brokerId, Deal deal);
	public void sendNotificationToBrokerAboutDeal(Integer brokerId, BrokerNotification brokerNotification);


}
