package com.brokerApplication.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "customerId")
public class Customer extends User{

	 private String CustomerName;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")   						// added
//	 @JoinColumn(name = "customer")   								// added
	 private List<Property> listOfProperties;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") 	// added
	 private List<Deal> listOfDeals;

	 
	 
	 
	public Customer(String username, String email, String mobile, RoleType role, String city, String password,String customerName) {
		super(username, email, mobile, role, city, password);
		CustomerName = customerName;
	}



//	public Customer(String username, String email, String mobile, String role, String city, String password,
//			String customerName) {
//		super(username, email, mobile, role, city, password);
//		CustomerName = customerName;
//	}
	 
	 
	
	 
}
