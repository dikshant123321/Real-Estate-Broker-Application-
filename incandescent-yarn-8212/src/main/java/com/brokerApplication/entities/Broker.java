package com.brokerApplication.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Broker extends User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer brokerId;
	@NotBlank
	@NotNull
	@NotEmpty
	private String brokerName;
	private List<Properties> brokerProperties;
	
}
	