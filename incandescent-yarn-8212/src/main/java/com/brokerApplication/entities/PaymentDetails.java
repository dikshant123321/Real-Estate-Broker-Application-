package com.brokerApplication.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {
	
	@NotNull
	private Integer dealId;
	
	@NotNull
	private Integer customerId;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Pattern(regexp = "^[0-9]{16}$", message = "Please enter currect card number.")
	private String cardNo;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Pattern(regexp = "^[0-9]{3}$", message = "Please enter currect card's cvv number.")
	private String cvv;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 3, max = 26, message = "Card holder's name's length must be in between 3 to 26 characters.")
	private String cardHolderName;
	
	@NotNull
	@Min(value = 0)
	private Double paymentAmount;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Future
	private LocalDate expiryDate;
	
}
