package com.brokerApplication.services;

import java.util.List;

import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.PropertyException;

public interface PropertyService {

	public Property addProperty(Property property);
	public Property editProperty(Property property) throws PropertyException;
	public List<Property> ListAllPropertys()throws PropertyException;
	public Property viewPropertyById(Integer propertyId)throws PropertyException;
	public Property removePropertyById(Integer propertyId)throws PropertyException;
}
