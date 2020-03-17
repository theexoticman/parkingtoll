package parkingtoll.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import parkingtoll.business.Currency;
import parkingtoll.business.Price;
import parkingtoll.model.Car;
import parkingtoll.model.CarType;
import parkingtoll.model.GasolineCar;
import parkingtoll.model.ParkingDefault;
import parkingtoll.model.ParkingToll;
import parkingtoll.model.Reservation;
import parkingtoll.model.Slot;

public class ParkingDefaultTest {

	private ParkingDefault parking;

	private Car gasCar;
	private Car elec20KWCar;
	private Car elec50KWCar;

	private Slot gasSlot;
	private Slot elec20KWSlot;
	private Slot elec50KWSlot;

	@Before
	public void setup() {
		Set<Slot> freeSlots = new HashSet<>();
		gasSlot = new Slot(1, CarType.GASOLINE);
		elec20KWSlot = new Slot(2, CarType.ELECTRIC20KW);
		elec50KWSlot = new Slot(3, CarType.ELECTRIC50KW);
		freeSlots.addAll(Arrays.asList(gasSlot, elec20KWSlot, elec50KWSlot));

		this.parking = (ParkingDefault) ParkingToll.builder("Default").get();

		assertNotNull("Parking instance shouldnt be null.", parking);

		// init the slot type
		this.gasSlot = new Slot(1, CarType.GASOLINE);
		this.elec20KWSlot = new Slot(2, CarType.ELECTRIC20KW);
		this.elec50KWSlot = new Slot(3, CarType.ELECTRIC50KW);
		// init cat
		this.gasCar = new GasolineCar("01XGMC");
		this.elec20KWCar = new GasolineCar("01XGMC");
		this.elec50KWCar = new GasolineCar("01XGMC");

	}

	@Test
	public void slotUnicityTest() {
		Slot slot120 = new Slot(1, CarType.ELECTRIC20KW);
		Slot slot150 = new Slot(1, CarType.ELECTRIC50KW);
		Set<Slot> slots = new HashSet<Slot>();
		slots.addAll(Arrays.asList(slot120, slot150));
		this.parking.setSlots(slots);

		assertEquals("Parking slot list should have unicity of slots.", this.parking.getSlots().size(), 1);
	}

	@Test
	public void findSlotTest() throws InterruptedException {
		// Scenario
		// 1 spot for gasoline cars
		// 1 spot for electric 20kw
		// 1 spot for electric 50kw.
		Optional<Slot> gasSlot1 = this.parking.bookSlot(gasCar);
		assertEquals(CarType.GASOLINE.name(), gasSlot1.get().getType());

		// 0 gasoline left for a new car.
		Optional<Slot> gasSlot2 = this.parking.bookSlot(gasCar);

		assertTrue("Gasoline slot type are all full.", !gasSlot2.isPresent());

	}

	@Test
	public void releaseSlotTest() throws InterruptedException {
		// Scenario
		// 1 spot for gasoline cars
		// 1 spot for electric 20kw
		// 1 spot for electric 50kw.
		Optional<Slot> gasSlot1 = this.parking.bookSlot(this.gasCar);

		Reservation res1Completed = new Reservation(this.gasCar, gasSlot1.get());

		// expected to have a gasoline slot busy. 0 gasoline slot left
		Optional<Reservation> gasSlot1Reservation = this.parking.releaseSlot(gasSlot1.get());

		// release slot already released.
		assertEquals("The slot freed should be the of the reservation", gasSlot1Reservation.get(), res1Completed);

		assertTrue("After realeasing the slor, slot is free", gasSlot1.get().isFree());

		// gasoline type slot is free again. Lets book it again.
		Optional<Slot> reBookedSlot = this.parking.bookSlot(this.gasCar);

		assertEquals("Slot of type Gasoline should be again bookable.", gasSlot1.get(), reBookedSlot.get());

	}

	@Test
	public void InvoceSlot() {
		Reservation res = new Reservation(this.gasCar, this.gasSlot);
		res.setDurationHour((long) 3);
		// ParkingToll has fixed price strategy
		Price expectedPrice = new Price(Double.valueOf(25), Currency.EUROS);
		Price price = parking.calculatePrice(res);

		assertEquals("10e fixed and 5 euros per hour dor 3 hours.", expectedPrice, price);

	}

}