package com.brokerApplication.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private String configuration;
	private String offerType ;
	private Double offerCost;
	private Double areaSqft;
	private String address;
	private String street;
	private String city;
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean isAvailable; // Broker owns = true ----- Customer owns = false
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer")
	@JsonIgnore
	Customer customer;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "broker")
	@JsonIgnore
	Broker broker;
	
}
