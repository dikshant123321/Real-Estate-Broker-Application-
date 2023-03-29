package com.brokerApplication.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Property {
     
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer propId;
	private String configuration;
	private String offerType ;
	private Double offerCost;
	private Double areaSqft;
	private String address;
	private String street;
	private String city;
	private Boolean status;
	
	
}
