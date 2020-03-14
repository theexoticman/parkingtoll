package com.exotic.parkingtoll;

import java.util.Date;

public interface PricingPolicy {

	public default Price calculatePrice(Double rentalDuration, CarType type) {
		Double fixedFare = new Double(10);
		Price price = new Price(fixedFare, "Euros");
		return price;	
	}

}
