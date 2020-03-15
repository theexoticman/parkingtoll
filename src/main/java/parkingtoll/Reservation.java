package parkingtoll;

import java.util.Date;

/**
 * A Reservation is an object that is created when a Car is assigned to a Slot.
 * The arrival Date is the time in which this assignement is done. The departure
 * is when the Car is detached from the Slot.i.e. another car can book it.
 * 
 * @author jlm
 *
 */
public class Reservation {

	private Car car;
	private Slot slot;
	private Long arrivalTime;
	private Long departureTime;
	private Price price;

	/**
	 * Create the Reservation object with the car and slot associated. Arrival tim
	 * will be set to the reservation creation.
	 * 
	 * @param car
	 * @param slot
	 */
	public Reservation(Car car, Slot slot) {
		this.car = car;
		this.slot = slot;
		this.arrivalTime = System.currentTimeMillis();
	}

	/**
	 * Put an end to the reservation by setting the departure time at method
	 * execution time.
	 */
	public void closeReservation() {
		this.departureTime = System.currentTimeMillis();
	}
}
