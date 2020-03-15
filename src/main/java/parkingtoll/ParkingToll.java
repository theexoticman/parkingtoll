package parkingtoll;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Parking Toll is a representation of a parking with a list of slots. Slots
 * are all available when Object is created.
 * 
 * This class offers three main methods: bookSlot will allow to associate a car
 * to a free slot if any; Also, it can release the slot by removing the car
 * reference from the wanted slot. Finally, it allows to calculate the price of
 * a reservation object. The price is defined by a Pricing Policy that has to be
 * implemented by the ParkingToll Class.
 * 
 * 
 * 
 * @author jlm
 *
 */
public class ParkingToll implements PrincingPolicy {

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
	 * @throws SlotOccupiedException
	 */
	public Slot bookSlot(Car newCar) throws SlotOccupiedException {
		CarType type = newCar.getType();
		for (Slot slot : this.getSlots()) {
			if ((type == slot.getType()) && slot.isFree()) {
				logger.debug("Slot %d is avaialble for car type: %s", slot.getLocation(), type);
				slot.book(newCar);
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
	 * @throws SlotNotFoundException
	 * @throws SlotOccupiedException
	 */
	public void releaseSlot(Slot bookedSlot) throws SlotNotFoundException, SlotOccupiedException {
		for (Slot slot : slots) {
			if (slot.equals(bookedSlot)) {
				bookedSlot.free();
				return;
			}
		}
		throw new SlotNotFoundException(bookedSlot.getLocation());
	}

	@Override
	public Price calculatePrice(Reservation res) {
		return new Price(10, Currency.EUROS);
	}

	public List<Slot> getSlots() {
		return slots;
	}
}
