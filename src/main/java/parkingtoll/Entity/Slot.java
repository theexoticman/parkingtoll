package parkingtoll.Entity;

import parkingtoll.Exceptions.SlotOccupiedException;
import parkingtoll.Vehicules.Car;
import parkingtoll.Vehicules.Parkable;

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
	 * @param slotId, slot identification.
	 * @param type,   associated to a car type.
	 */
	public Slot(Integer location, Parkable parkable) {
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
		int result = location;
		result += type.hashCode();
		return result;
	}

	/**
	 * Slot Id getter @return, slot id
	 */
	public Integer getLocation() {
		return location;
	}

	/**
	 * Slot Type getter @return, slot type
	 */
	public String getType() {
		return this.type;
	}

	public boolean isFree() {
		return this.car == null ? true : false;
	}

	protected void book(Car newCar) throws SlotOccupiedException {
		if (this.car != null) {
			// slot already booked.
			// software state shouldn't be possible.
			throw new SlotOccupiedException(getLocation());
		}
		this.car = newCar;
	}

	public void free() {
		this.car = null;
	}

}
