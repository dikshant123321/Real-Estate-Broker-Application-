package com.brokerApplication.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.AssertTrue;
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
	
	//added----
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime startPeriod;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime endPeriod;
	
	@AssertTrue(message = "Start and end period must be specified for rent deals")
	private boolean isValidRentDeal() {
	    if (dealType == DealType.RENT) {
	        return startPeriod != null && endPeriod != null;
	    }
	    return true;
	}
}
