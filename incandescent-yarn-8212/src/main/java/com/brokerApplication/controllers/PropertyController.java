//package com.brokerApplication.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.brokerApplication.entities.Property;
//import com.brokerApplication.exceptions.PropertyException;
//import com.brokerApplication.services.PropertyService;
//
//@RestController
//public class PropertyController {
//
//	@Autowired
//	private PropertyService propertyService;
//	
//	@PostMapping("/property")
//	public ResponseEntity<Property> addProperty(@RequestParam Integer brokerId,@RequestBody Property property ) 
//	{
//		
//		Property savedProperty = propertyService.addProperty(property, brokerId);
//		
//		return new ResponseEntity<Property>(savedProperty,HttpStatus.CREATED);
//		
//	}
//	
//	@PutMapping("/property")
//	public ResponseEntity<Property> editProperty(@RequestBody Property property) throws PropertyException{
//		
//		Property editedproperty=propertyService.editProperty(property);
//		return new ResponseEntity<Property>(editedproperty,HttpStatus.ACCEPTED);
//		
//	}
//	
//	@GetMapping("/property")
//	public ResponseEntity<List<Property>> ListAllPropertys() throws PropertyException{
//		List<Property> allPropertys=  propertyService.ListAllPropertys();
//		return new ResponseEntity<List<Property>>(allPropertys,HttpStatus.OK);
//	}
//	
//	@GetMapping("/property/{propertyId}")
//	public ResponseEntity<Property> viewPropertyById(@PathVariable("propertyId") Integer propertyId) throws PropertyException
//	{
//		Property property= propertyService.viewPropertyById(propertyId);
//		return new ResponseEntity<Property>(property,HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/property/{propertyId}")
//	public ResponseEntity<Property> removePropertyById(@PathVariable("propertyId") Integer propertyId) throws PropertyException
//	{
//		Property removedProperty= propertyService.removePropertyById(propertyId);
//		return new ResponseEntity<Property>(removedProperty,HttpStatus.ACCEPTED);
//	}
//	
//}
