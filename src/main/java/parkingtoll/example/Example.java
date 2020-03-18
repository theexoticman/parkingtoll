package parkingtoll.example;

import java.util.Optional;

import parkingtoll.business.Price;
import parkingtoll.model.ParkingDefault;
import parkingtoll.model.ParkingToll;
import parkingtoll.model.Car;
import parkingtoll.model.Slot;
import parkingtoll.model.GasolineCar;
import parkingtoll.model.Reservation;

public class Example {

	public static void main(String[] args) throws InterruptedException {
		// instantiating a parking
		ParkingToll parking = new ParkingDefault();

		// new car to be parked
		Car gasolineCar = new GasolineCar("LSN0908");

		// Car parked in slot
		Optional<Slot> slot = parking.bookSlot(gasolineCar);

		// Car leave the slot. Slot is free
		Optional<Reservation> res = parking.releaseSlot(slot.get());

		// Invocing the client for its reservation.
		Price price = parking.calculatePrice(res.get());
	}
}
