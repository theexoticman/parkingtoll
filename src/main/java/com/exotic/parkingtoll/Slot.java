package com.exotic.parkingtoll;

import java.util.Date;

public class Slot {

	private Integer locationId;
	private Boolean isFree;
	private CarType type;

	private Date arrivalDate;
	private Date leaveDate;

	public Slot(Integer locationId, CarType type) {
		this.locationId = locationId;
		this.type = type;
		this.isFree = true;
	}

	public Slot() {

	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!Slot.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		Slot other = (Slot) obj;
		if (this.locationId != other.locationId) {
			return false;
		}
		return true;
	}

	public CarType getType() {
		return type;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

}
