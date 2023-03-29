package com.brokerApplication.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Deal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer dealid;
	LocalDate dealDate;
	Double dealCost;
	String dealType;
	boolean dealStatus;
	
	@ManyToOne
	Customer customer;
	
	@ManyToOne
	Property property;
	
}
