package parkingtoll.Shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parkingtoll.Car.Car;
import parkingtoll.Exception.NullParameterException;
import parkingtoll.Exceptions.SlotNotFoundException;
import parkingtoll.Exceptions.SlotOccupiedException;
import parkingtoll.PricingPolicy.PricingPolicy;

/**
 * A Parking Toll is a representation of a parking lot with a list of slots.
 * Slots are all free when Parking Toll is created.
 * 
 * This class offers three main methods: bookSlot will allow to associate a car
 * to a free slot if any for that car type; Also, it can release the slot by
 * removing the car reference from the wanted slot. Finally, it allows to
 * calculate the price of a reservation object. The price is defined by a
 * Pricing Policy that has to be implemented by the ParkingToll Class.
 * 
 * Parkingtoll is abstracted so that the library user can create its own praking
 * object, being able to override any of the standard behaviour and allowing it
 * to implements its own pricing policy
 * 
 * @author jlm
 *
 */
public abstract class ParkingToll implements PricingPolicy {

	private Set<Slot> slots;
	private List<Reservation> reservations;
	private final Logger logger = LoggerFactory.getLogger(ParkingToll.class);

	public ParkingToll(Set<Slot> freeSlots) {
		this.slots = freeSlots;
		this.reservations = new ArrayList<>();
	}

	/**
	 * Find slot for car depending on the car type, only if any free slot is
	 * available.
	 * 
	 * Method must be syncrhonized to prevent, in a distributed context, two new car
	 * having the same slot assigned.
	 * 
	 * @param newCar
	 * @return Slot If available, null if no free slot for the cartype
	 * @throws SlotOccupiedException
	 * @throws NullParameterException
	 */
	public synchronized Optional<Slot> bookSlot(Car newCar, Reservation reservation)
			throws SlotOccupiedException, NullParameterException {
		if (newCar == null) {
			throw new NullParameterException(newCar.getClass().toString());
		}
		if (reservation == null) {
			throw new NullParameterException(reservation.getClass().toString());
		}
		String type = newCar.getType().toString();
		Slot freeSlot = null;
		for (Slot slot : this.getSlots()) {
			if ((type.equals(slot.getType())) && slot.isFree()) {
				freeSlot = slot;
				logger.debug("Slot %d is avaialble for car type: %s", slot.getLocation(), type);
				slot.book(newCar);
				reservation.initReservation(newCar, slot);
				this.reservations.add(reservation);
			}
		}
		logger.debug("No slot available for car type: %s", type);
		return Optional.ofNullable(freeSlot);
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
	 * @throws NullParameterException
	 */
	public void releaseSlot(Slot bookedSlot) throws SlotNotFoundException, NullParameterException {
		if (bookedSlot == null) {
			throw new NullParameterException(bookedSlot.getClass().toString());
		}
		for (Slot slot : this.slots) {
			if (slot.equals(bookedSlot)) {
				bookedSlot.free();
				return;
			}
		}
		throw new SlotNotFoundException(bookedSlot.getLocation());
	}

	public Set<Slot> getSlots() {
		return slots;
	}
}
