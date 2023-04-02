package com.brokerApplication.entities;

import java.time.temporal.ChronoUnit;
import java.util.Objects;

import jakarta.persistence.Entity;
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
	private Double TotalPayableAmout;
	private BillStatus billStatus;
	
	public Bill(Deal deal) {
		
		this.deal = deal;
		
		if(deal.getDealType() == DealType.RENT) {
			
			TotalPayableAmout = deal.getDealCost() * (ChronoUnit.DAYS.between(deal.getRentStartPeriod(), deal.getRentEndPeriod()));
			
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
		return Objects.equals(TotalPayableAmout, other.TotalPayableAmout) && Objects.equals(billId, other.billId)
				&& billStatus == other.billStatus && Objects.equals(deal, other.deal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(TotalPayableAmout, billId, billStatus, deal);
	}
	
	

}
