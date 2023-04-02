package com.brokerApplication.services;

import java.util.List;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.PropertyException;

public interface PropertyService {

	//broker only
	public Property addProperty(Property property, Integer brokerId);
	public Property editPropertyById(Property property) throws PropertyException;
	public Property removePropertyById(Integer propertyId)throws PropertyException;
	
	//broker, customer, administrator
	public Property viewPropertyById(Integer propertyId)throws PropertyException;
	public List<Property> viewListOfProperties()throws PropertyException;

	//internal use only
	public void rentPropertyById(Deal deal);
	public Property buyPropertyById(Deal deal);
	
}
