package parkingtoll;

public class Slot {

	private Integer id;
	private CarType type;
	private Boolean isFree;

	/**
	 * Slot Constructor
	 * 
	 * @param slotId, slot identification.
	 * @param type,   associated to a car type.
	 */
	public Slot(Integer slotId, CarType type) {
		this.id = slotId;
		this.type = type;
		this.isFree = true;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Slot slot = (Slot) o;
		if (slot.getId() == null || getId() == null) {
			return false;
		}
		return this.getId().equals(slot.getId());
	}

	/**
	 * Slot Id getter @return, slot id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Slot Type getter @return, slot type
	 */
	public CarType getType() {
		return this.type;
	}

	@Override
	public int hashCode() {
		int result = id;
		result += type.hashCode();
		return result;
	}

	public boolean isFree() {
		return this.isFree;
	}

	protected void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

}
