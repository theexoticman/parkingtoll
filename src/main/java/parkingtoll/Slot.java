package parkingtoll;

/**
 * Slot is a class that represents a slot in the parking. It can only used by
 * one car at a time. A slot it reserved to a type of car. A car from another
 * type cannot use the spot.
 * 
 * @author jlm
 *
 */
public class Slot {

	private Integer location;
	private CarType type;
	private Boolean isFree;

	/**
	 * Slot Constructor
	 * 
	 * @param slotId, slot identification.
	 * @param type,   associated to a car type.
	 */
	public Slot(Integer location, CarType type) {
		this.location = location;
		this.type = type;
		this.isFree = true;
	}

	/**
	 * two slots are equal is they have the same location.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) {
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
	public CarType getType() {
		return this.type;
	}

	public boolean isFree() {
		return this.isFree;
	}

	protected void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

}
