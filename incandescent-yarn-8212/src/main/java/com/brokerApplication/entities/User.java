
package com.brokerApplication.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(unique = true)
	private String username;
	private String email;
	private String mobile;
	@Enumerated(value = EnumType.STRING)
	private RoleType role;
	private String city;
	private String password;
	

	public User() { }


	public User(String username, String email, String mobile, RoleType role, String city, String password) {
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
		this.city = city;
		this.password = password;
	}


	public User(Integer userId, String username, String email, String mobile, RoleType role, String city,
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
