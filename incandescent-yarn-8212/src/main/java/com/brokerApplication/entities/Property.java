package com.brokerApplication.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Property {
     
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer propertyId;
	
	@NotNull(message = "Property's dimensions cannot be Null.")
	@Min(value = 10 , message = "Property's dimensions cannot be less than 10 Sqft.")
	private Double areaSqft;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String address;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String street;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String city;
	
//	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "Please specify property is for what purpose, in 'propertyStatus' field. (RENTABLE, SALEABLE or SALEABLE_AND_RENTABLE)")
	private PropertyStatus propertyStatus;
	
	private Double rentPricePerNight;
	private Double salePrice;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean isAvailable; // Broker owns = true ----- Customer owns = false
	
	@ManyToOne
	@JoinColumn(name = "customer")
	@JsonIgnore
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "broker")
	@JsonProperty(access = Access.READ_ONLY)
	private Broker broker;
	
	@AssertTrue(message = "Price fields are not satisfied, make sure to provide value of rentPricePerNight if propertyStatus is RENTABLE, salePrice if propertyStatus is SALEABLE, and both if the propertyStatus is SALEABLE_AND_RENTABLE.")
	private boolean isValidRentDeal() {
	    if (propertyStatus == PropertyStatus.RENTABLE && rentPricePerNight == null)
	    	return false;
	    if(propertyStatus == PropertyStatus.SALEABLE && salePrice == null)
	    	return false;
	    if(propertyStatus == PropertyStatus.SALEABLE_AND_RENTABLE && (rentPricePerNight == null || salePrice == null)) {
	    	return false;
	    }
	    return true;
	}
}
