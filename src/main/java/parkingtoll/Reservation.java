package parkingtoll;

import javax.rmi.CORBA.Util;

import parkingtoll.util.Utils;
import parkingtoll.vehicules.Car;

/**
 * A Reservation is an object that is created when a Car is assigned to a Slot.
 * The arrival Date is the time in which this assigning is done. The departure
 * is when the Car is detached from the Slot.i.e. another car can book it.
 * 
 * @author jlm
 *
 */
public class Reservation {

	private String id;
	private Car car;
	private Slot slot;
	private Long arrivalTime;
	private Long departureTime;
	private Long durationMin;
	private Price price;

	/**
	 * Create the Reservation object with the car and slot associated. Arrival tim
	 * will be set to the reservation creation.
	 * 
	 * @param car
	 * @param slot
	 */
	public Reservation(Car car, Slot slot) {
		this.id = car.getLicensePlate().concat(slot.getType());
		this.car = car;
		this.slot = slot;
		this.arrivalTime = System.nanoTime();
	}

	/**
	 * Put an end to the reservation by setting the departure time at method
	 * execution time.
	 */
	public void closeReservation() {
		this.departureTime = System.nanoTime();
		this.durationMin = Utils.getElapsedMin(this.arrivalTime, this.departureTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Reservation res = (Reservation) obj;
		if (res.getId() == null || this.getId() == null) {
			return false;
		}
		return this.getId().equals(res.getId());
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	public String getId() {
		return id;
	}

	public Long getDurationMin() {
		return durationMin;
	}

}
