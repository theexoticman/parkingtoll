package parkingtoll;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.HandlerBase;

import parkingtoll.Car;
import parkingtoll.PricingPolicy;

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

	private Semaphore mutex;
	private Set<Slot> slots;
	private Set<Reservation> reservations;
	private final Logger logger = LoggerFactory.getLogger(ParkingToll.class);

	public ParkingToll(Set<Slot> freeSlots) {
		this.mutex = new Semaphore(1);
		this.slots = freeSlots;
		this.reservations = new HashSet<>();
	}

	/**
	 * Find slot for car depending on the car type, only if any free slot is
	 * available.
	 * 
	 * Method must be syncrhonized to prevent, in a distributed context, two new car
	 * having the same slot assigned.
	 * 
	 * @param newCar,      new car to be parked
	 * @param reservation, reservation to be userd
	 * @return Slot If available, null if no free slot for the cartype
	 * 
	 */
	public Optional<Slot> bookSlot(Car newCar, Reservation reservation) throws InterruptedException {
		Slot freeSlot = null;
		if (newCar == null || reservation == null) {
			return Optional.ofNullable(freeSlot);
		}

		String type = newCar.getType().toString();

		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			logger.error("Mutex Aquisition failed; Internal error.");
			throw e;
		}
		for (Slot slot : this.getSlots()) {
			if ((type.equals(slot.getType())) && slot.isFree()) {
				freeSlot = slot;
				logger.info("Slot %d is booked for car type: %s", slot.getLocation(), type);
				slot.book(newCar);
				reservation.initReservation(newCar, slot);
				this.reservations.add(reservation);
			}
		}
		mutex.release();
		return Optional.ofNullable(freeSlot);
	}

	/**
	 * Release slot with set the Slot isFree parameter to true. If the @param is a
	 * free slot. nothing happens.
	 * 
	 * @param bookedSlot, we assume the Slot is not free.
	 * @return true, if slot if found. return False, if slot does not exist in the
	 *         parking lot.
	 * 
	 */
	public Boolean releaseSlot(Slot bookedSlot) {
		for (Slot slot : this.slots) {
			if (slot.equals(bookedSlot)) {
				logger.info("Slot %d is free.", bookedSlot.getLocation());
				bookedSlot.free();
				return true;
			}
		}
		return false;
	}

	/**
	 * get parking slots for testing purposed @return, set of slots.
	 */
	protected Set<Slot> getSlots() {
		return slots;
	}
}
