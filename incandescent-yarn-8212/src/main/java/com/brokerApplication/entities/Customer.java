package com.brokerApplication.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	 private String CustomerName;
	 @OneToMany(cascade = CascadeType.ALL)
	 private List<Property> listOfProperties;
	 
	 private List<Deal> listOfDeals;

}
