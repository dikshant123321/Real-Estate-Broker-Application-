
package com.brokerApplication.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(unique = true)
	private String username;
	private String email;
	private String mobile;
	private String role;
	private String city;
	private String password;
	
	 
	public User(String username, String email, String mobile, String role, String city,
			String password) {
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
		this.city = city;
		this.password = password;
	}


	public User(Integer userId, String username, String email, String mobile, String role, String city,
			String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
		this.city = city;
		this.password = password;
	}


	public User() {
		
	}
	
}
