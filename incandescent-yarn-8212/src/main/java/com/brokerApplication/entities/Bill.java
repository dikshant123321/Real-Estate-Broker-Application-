package com.brokerApplication.entities;

import java.time.temporal.ChronoUnit;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;
	
	@OneToOne
	private Deal deal;
	private Double totalPayableAmout;
	
	@Enumerated(value = EnumType.STRING)
	private BillStatus billStatus;
	
	public Bill(Deal deal) {
		
		this.deal = deal;
		
		billStatus = BillStatus.PENDING;
		
		if(deal.getDealType() == DealType.RENT) {
			
			totalPayableAmout = deal.getDealCost() * (ChronoUnit.DAYS.between(deal.getRentStartPeriod(), deal.getRentEndPeriod()));
			
		}else {
			
			totalPayableAmout = deal.getDealCost();
			
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		return Objects.equals(totalPayableAmout, other.totalPayableAmout) && Objects.equals(billId, other.billId)
				&& billStatus == other.billStatus && Objects.equals(deal, other.deal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(totalPayableAmout, billId, billStatus, deal);
	}
	
	

}
