package com.brokerApplication.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class PropertySchedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer propertyScheduleId;
	
	@NotNull
	private Integer propertyId;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	@NotNull
	private PropertyStatus propertyStatus;

	public PropertySchedule(@NotNull Integer propertyId, @NotNull LocalDate startDate, @NotNull LocalDate endDate,
			@NotNull PropertyStatus propertyStatus) {
		this.propertyId = propertyId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.propertyStatus = propertyStatus;
	}
	
	
}
