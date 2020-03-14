package com.exotic.parkingtoll;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot implements PricingPolicy {

	private List<Slot> freeSlots;
	private List<Slot> busySlots;

	public ParkingLot() {

	}

	public void setFreeslots(List<Slot> freeSlots) {
		this.freeSlots = freeSlots;
	}

	public void setBusyslots(List<Slot> busySlots) {
		this.busySlots = busySlots;
	}

	@Override
	public Price calculatePrice(Double rentalDuration, CarType type) {
		Integer priceHour = 10 ;
		Double fare = rentalDuration*priceHour;
		Price price = new Price(fare,"Euros");
		return price;
	}

}
