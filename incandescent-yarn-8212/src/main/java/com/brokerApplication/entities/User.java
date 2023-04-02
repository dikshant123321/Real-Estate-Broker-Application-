
package com.brokerApplication.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer userId;
	
	@Column(unique = true)
	private String username;
	
//	@URL
	private String profilePhoto;

	
	@Email
	@Column(unique = true)
	private String email;
	
	@Pattern(regexp = "^[0-9]{10}$", message = "Please enter currect mobile number.")
	@Column(unique = true)
	private String mobile;
	

//	@Enumerated(value = EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private UserRoleType role;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String city;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$", message = "Password must contains 8 to 16 characters and it should have at least one digit, one alphabet, one special character from the set [@#$%^&+=!] and no whitespace allowed.")
	//User@1234
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	

	public User() { }


	public User(String username, String email, String mobile, UserRoleType role, String city, String password) {
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
		this.city = city;
		this.password = password;
	}


	public User(Integer userId, String username, String email, String mobile, UserRoleType role, String city,
			String password) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
		this.city = city;
		this.password = password;
	}
	
	
}
