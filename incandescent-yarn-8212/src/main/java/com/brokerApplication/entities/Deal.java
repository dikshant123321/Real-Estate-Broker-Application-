package com.brokerApplication.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
	@JsonProperty(access = Access.READ_ONLY)
	private Integer dealid;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dealDateTime;
	
	@Min(value = 0, message = "Deal cost cannot be in negative.")
	@NotNull(message = "Deal cost cannot be Null.")
	private Double dealCost;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "Deal type cannot be Null")
	private DealType dealType;
	
	@Enumerated(value = EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private DealStatus dealStatus; 
	
	@ManyToOne
	@NotNull(message = "Customer cannot be Null.")
	@JoinColumn(name = "customer")  // added
	private Customer customer;
	
	@ManyToOne
	@NotNull(message = "Property cannot be Null.")
	private Property property;
	
	@ManyToOne
	@NotNull(message = "Broker cannot be Null.")
	@JoinColumn(name = "broker")  // added
	private Broker broker;
	
	@JsonProperty(access = Access.READ_ONLY)
	private boolean isCustomerAgree;
	
	@JsonProperty(access = Access.READ_ONLY)
	private boolean isBrokerAgree;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate rentStartPeriod;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate rentEndPeriod;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty(access = Access.READ_ONLY)
	@JsonIgnore
	private Bill bill;
	
	
}
