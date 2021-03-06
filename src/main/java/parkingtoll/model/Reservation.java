package parkingtoll.model;

import parkingtoll.business.Price;

/**
 * A Reservation is an object that is created when a Car is assigned to a Slot.
 * The arrival Date is the time in which this assigning is done. The departure
 * time is calculated when parking toll release the slot and the Car is detached
 * from the Slot. i.e. another car can book it.
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
	private Long durationHour;
	private Price price;

	/**
	 * Create the Reservation object with the car and slot associated. Arrival tim
	 * will be set to the reservation creation.
	 * @param car, a car instance of type T
	 * @param slot, slot of same type T
	 */
	protected Reservation(Car car, Slot slot) {
		this.arrivalTime = System.nanoTime();
		this.id = car.getLicensePlate().concat(String.valueOf(slot.getLocation()));
		this.car = car;
		this.slot = slot;
	}

	/**
	 * Put an end to the reservation by setting the departure time at method
	 * execution time.
	 */
	protected void closeReservation() {
		this.departureTime = System.nanoTime();
		this.setDurationHour(Utils.getElapsedHours(this.getArrivalTime(), this.getDepartureTime()));
	}

	/**
	 * Two reservation are equals if they contain the reference to the same car,
	 * same slot and were created at the same time.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		Reservation res = (Reservation) obj;
		return this.getId().equals(res.getId());
	}

	/**
	 * Resources are instantied. isEmpty allows to know if the reservation as been
	 * filled with the reservation information, or not.
	 * 
	 * @return, true is empty, false otherwise
	 */
	public boolean isEmpty() {
		return hashCode() == 0 ? true : false;
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	/**
	 * get reservation id @return, string id
	 */
	protected String getId() {
		return id;
	}

	/**
	 * reservation duration in hours @return, long, duration
	 */
	public Long getDurationHour() {
		return durationHour;
	}

	/**
	 * return car associated to the reservation @return, car, this car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * return slot associated to the reservation @return, slot, this slor
	 */
	public Slot getSlot() {
		return slot;
	}

	/**
	 * price associated to the reservation @return, price instance.
	 */
	protected Price getPrice() {
		return price;
	}

	/**
	 * Should be used for testing purposes only
	 * 
	 * @param durationHour, hours in long representation.
	 */
	protected void setDurationHour(Long durationHour) {
		this.durationHour = durationHour;
	}

	/**
	 * Get the tim where the reservation has started
	 * @return long, representing the time from System.nanotime()
	 */
	public Long getArrivalTime() {
		return arrivalTime;
	}
	
	/**
	 * Get the time when reservation got closed. System.nanotime()
	 * @return long,
	 */
	public Long getDepartureTime() {
		return departureTime;
	}

}
