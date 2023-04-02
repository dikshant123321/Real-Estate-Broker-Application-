package com.brokerApplication.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrokerOffer {
	
	@Min(value = 0, message = "Deal cost cannot be in negative.")
	@NotNull(message = "Deal cost cannot be Null.")
	private Double dealCost;
	
	@NotNull(message = "customerId cannot be Null.")
	private Integer customerId;
	
	@NotNull(message = "propertyId cannot be Null.")
	private Integer propertyId;
	
	@NotNull(message = "brokerId cannot be Null.")
	private Integer brokerId;
	
	@NotNull(message = "dealId cannot be Null.")
	private Integer dealId;
	

	
}
