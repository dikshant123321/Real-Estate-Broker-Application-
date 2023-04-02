package com.brokerApplication.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class LoggedSession {
	@Id
	@Column(unique = true)
	private Integer userId;
	private String uniquekey;
	@Enumerated(value = EnumType.STRING)
	private UserRoleType role;
	private LocalDateTime localDateTime;
}