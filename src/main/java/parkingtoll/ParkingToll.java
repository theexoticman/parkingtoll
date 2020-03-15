package parkingtoll;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParkingToll {

	private List<Slot> slots;
	private final Logger logger = LoggerFactory.getLogger(ParkingToll.class);

	public ParkingToll(List<Slot> freeSlots) {
		this.slots = freeSlots;
	}

	/**
	 * Find slot for car depending on the car type, only if any free slot is
	 * available.
	 * 
	 * @param newCar
	 * @return Slot If available, null if no free slot for the cartype
	 */
	public Slot bookSlot(Car newCar) {
		CarType type = newCar.getType();
		for (Slot slot : this.getSlots()) {
			if ((type == slot.getType()) && slot.isFree()) {
				logger.debug("Slot %d is avaialble for car type: %s", slot.getId(), type);
				slot.setIsFree(false);
				return slot;
			}
		}
		logger.debug("No slot available for car type: %s", type);
		return null;
	}

	/**
	 * Release slot with set the Slot isFree parameter to true. If the @param is a
	 * free slot. nothing happens.
	 * 
	 * @param bookedSlot, we assume the Slot is not free.
	 * @return true, if slot if found. return False, if slot does not exist in the
	 *         parking lot.
	 */
	public Boolean releaseSlot(Slot bookedSlot) {
		for (Slot slot : slots) {
			if (slot.equals(bookedSlot)) {
				bookedSlot.setIsFree(true);
				return true;
			}
		}
		return false;
	}

	public List<Slot> getSlots() {
		return slots;
	}

}
