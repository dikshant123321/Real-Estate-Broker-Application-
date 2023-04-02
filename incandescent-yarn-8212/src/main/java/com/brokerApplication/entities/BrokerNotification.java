package com.brokerApplication.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BrokerNotification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer brokerNotificationId;
	
	private Integer brokerId;
	
	private Integer dealId;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime notificationTime;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Size(min = 1, max = 100)
	private String notificationMessage;
	
	@NotNull
	@Enumerated(value = EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private NotificationSatus notificationSatus;

	public BrokerNotification(Integer brokerId, Integer dealId, LocalDateTime notificationTime,
			@NotNull @NotBlank @NotEmpty @Size(min = 1, max = 100) String notificationMessage) {
		this.brokerId = brokerId;
		this.dealId = dealId;
		this.notificationTime = notificationTime;
		this.notificationMessage = notificationMessage;
		this.notificationSatus = NotificationSatus.UNSEEN;
	}

}
