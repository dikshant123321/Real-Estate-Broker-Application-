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

@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@PrimaryKeyJoinColumn(name = "brokerId")
public class Broker extends User{

	@NotBlank
	@NotNull
	@NotEmpty
	private String brokerName;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "broker") 	// added
	private List<Deal> listOfDeals; 							// added
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "broker") 	// added
	private List<Property> listOfProperties;

	public Broker(String username, String email, String mobile, RoleType role, String city, String password,
			@NotBlank @NotNull @NotEmpty String brokerName) {
		super(username, email, mobile, role, city, password);
		this.brokerName = brokerName;
	}
	
	

}
	