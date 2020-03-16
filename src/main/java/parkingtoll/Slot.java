package parkingtoll;

import parkingtoll.Car;
import parkingtoll.Parkable;

/**
 * Slot is a class that represents a slot in the parking Toll. It can only used
 * by one car at a time. A slot it reserved to a type of car. A car from another
 * type cannot use the spot.
 * 
 * @author jlm
 *
 */
public abstract class Slot {

	private Integer location;
	private String type;
	private Car car;

	/**
	 * Slot Constructor
	 * 
	 * @param location, slot location and identification
	 * @param parkable, one of a car type
	 */
	protected Slot(Integer location, Parkable parkable) {
		this.location = location;
		this.type = parkable.getType();
	}

	/**
	 * two slots are equal is they have the same location.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		Slot slot = (Slot) o;
		if (slot.getLocation() == null || getLocation() == null) {
			return false;
		}
		return this.getLocation().equals(slot.getLocation());
	}

	@Override
	public int hashCode() {
		return location.hashCode();
	}

	/**
	 * Slot Id getter @return, slot id` @return, slot location.
	 */
	public Integer getLocation() {
		return location;
	}

	/**
	 * Get Slot type @return, string, slot type.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * return is the slot is free @return, true is slot free, false, else
	 */
	protected boolean isFree() {
		return this.car == null ? true : false;
	}

	/**
	 * Set a car to this slot.
	 * 
	 * @param newCar, new to car park @throws, if slot is
	 */
	protected void book(Car newCar) {
		this.car = newCar;
	}

	public void free() {
		this.car = null;
	}

}
