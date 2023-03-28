package com.brokerApplication.entities;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends User{

	 private Integer CustomerId;
	 private String CustomerName;
	 private List<Property> listOfProperties;
	 private List<Deal> listOfDeals;

}
