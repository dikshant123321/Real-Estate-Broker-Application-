package com.brokerApplication.webApplicationServices;

import java.util.List;
import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.Property;

public interface BrokerWebServices {
	
	public List<Broker> getAllBrokers();
	public List<Property> getAllProperties(Integer id);
	public Broker saveBr(Broker b);
	public void delbrokerbyid(Integer id);
}
