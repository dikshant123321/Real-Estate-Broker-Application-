package com.brokerApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.PropertyException;
import com.brokerApplication.repositorys.PropertyRepository;

@Service
public class PropertyServiceImple implements PropertyService{

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
    private BrokerServices bs;
	
	
	@Override
	public Property addProperty(Property property, Integer brokerId) {
		
		Broker broker = bs.viewBrokerById(brokerId);
		
		property.setIsAvailable(true);
		property.setBroker(broker);
		
		broker.getListOfProperties().add(property);
		
		return propertyRepository.save(property);
	}

	@Override
	public Property editProperty(Property property) throws PropertyException {
	     Optional<Property> opt=propertyRepository.findById(property.getPropertyId());
	     if(opt.isPresent())
	     { 
	    	 return propertyRepository.save(property);
	     }
	     else
	     {
	    	 throw new PropertyException("Property does not exist...");
	     }
	}

	@Override
	public List<Property> ListAllPropertys() throws PropertyException {
		List<Property> property=propertyRepository.findAll();
		
		if(!property.isEmpty())
		{
			return property;
		}
		else
		{
			throw new PropertyException("Property does not exist...");
		}
		
	}

	@Override
	public Property viewPropertyById(Integer propertyId) throws PropertyException {
		Optional<Property> opt= propertyRepository.findById(propertyId);
		
		if(opt.isPresent())
		{
			return opt.get();
		}
		else
		{
			throw new PropertyException("Property does not exist...");
		}
		
	}

	@Override
	public Property removePropertyById(Integer propertyId) throws PropertyException {
		Property st=propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyException("Property with propertyId "+propertyId+"does not exit.."));
		
		propertyRepository.delete(st);
		
		return st;
	}
	
	
	
	
}
