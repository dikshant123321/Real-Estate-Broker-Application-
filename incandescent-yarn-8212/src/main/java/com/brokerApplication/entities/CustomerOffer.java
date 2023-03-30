package com.brokerApplication.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOffer {
	
	@Min(value = 0, message = "Deal cost cannot be in negative.")
	@NotNull(message = "Deal cost cannot be Null.")
	private Double dealCost;
	
	@NotNull(message = "Deal type cannot be Null")
	private DealType dealType;
	
	@NotNull(message = "Customer cannot be Null.")
	private Integer customerId;
	
	@NotNull(message = "Property cannot be Null.")
	private Integer propertyId;
	
	@NotNull(message = "Broker cannot be Null.")
	private Integer brokerId;
	
}
