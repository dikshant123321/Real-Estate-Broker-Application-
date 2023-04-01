package com.brokerApplication.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	 
	 
	 @JsonIgnore       
	 @OneToMany(mappedBy = "customer")  
	 private List<Property> listOfProperties;
	 
	 
	 @JsonIgnore     
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") 
	 private List<Deal> listOfDeals;

	 
	 
	 
	public Customer(String username, String email, String mobile, RoleType role, String city, String password,@NotBlank @NotNull @NotEmpty String customerName) {
		super(username, email, mobile, role, city, password);
		this.CustomerName = customerName;
	}





//	public Customer(String username, String email, String mobile, String role, String city, String password,
//			String customerName) {
//		super(username, email, mobile, role, city, password);
//		CustomerName = customerName;
//	}
	 
	 

//	private List<CustomerNotification> notifications;

}
