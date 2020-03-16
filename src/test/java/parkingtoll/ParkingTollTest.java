package parkingtoll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import parkingtoll.Car;
import parkingtoll.CarType;
import parkingtoll.Currency;
import parkingtoll.GasolineCar;
import parkingtoll.ParkingMaracana;
import parkingtoll.ParkingToll;
import parkingtoll.Price;
import parkingtoll.Reservation;
import parkingtoll.ReservationMaracana;
import parkingtoll.Slot;
import parkingtoll.SlotMaracana;

public class ParkingTollTest {

	private ParkingToll parking;

	private Car gasCar;
	private Car elec20KWCar;
	private Car elec50KWCar;

	private Slot gasSlot;
	private Slot elec20KWSlot;
	private Slot elec50KWSlot;

	@Before
	public void setup() {
		Set<Slot> freeSlots = new HashSet<>();
		gasSlot = new SlotMaracana(1, CarType.GASOLINE);
		elec20KWSlot = new SlotMaracana(2, CarType.ELECTRIC20KW);
		elec50KWSlot = new SlotMaracana(3, CarType.ELECTRIC50KW);
		freeSlots.addAll(Arrays.asList(gasSlot, elec20KWSlot, elec50KWSlot));

		this.parking = new ParkingMaracana(freeSlots); // Parking is created with 2 free spot of each type.

		// init the slot type
		this.gasSlot = new SlotMaracana(1, CarType.GASOLINE);
		this.elec20KWSlot = new SlotMaracana(2, CarType.ELECTRIC20KW);
		this.elec50KWSlot = new SlotMaracana(3, CarType.ELECTRIC50KW);
		// init cat
		this.gasCar = new GasolineCar("01XGMC");
		this.elec20KWCar = new GasolineCar("01XGMC");
		this.elec50KWCar = new GasolineCar("01XGMC");

	}

	@Test
	public void slotUnicityTest() {
		Slot slot120 = new SlotMaracana(1, CarType.ELECTRIC20KW);
		Slot slot150 = new SlotMaracana(1, CarType.ELECTRIC50KW);
		Set<Slot> slots = new HashSet<Slot>();
		slots.addAll(Arrays.asList(slot120, slot150));
		this.parking = new ParkingMaracana(slots);
		assertEquals(parking.getSlots().size(), 1);
	}

	@Test
	public void findSlotTest() throws InterruptedException {
		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana1Completed = new ReservationMaracana();
		Reservation resMaracana1Failed = new ReservationMaracana();
		Optional<Slot> gasSlot1 = this.parking.bookSlot(gasCar, resMaracana1Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new SlotMaracana(1, CarType.GASOLINE), gasSlot1.get());
		Optional<Slot> gasSlot2 = this.parking.bookSlot(gasCar, resMaracana1Failed);
		// 0 gasoline left fot the new car.
		assertFalse(gasSlot2.isPresent());

	}

	@Test
	public void releaseSlotTest() throws InterruptedException {
		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana1Completed = new ReservationMaracana();
		Optional<Slot> gasSlot1 = this.parking.bookSlot(this.gasCar, resMaracana1Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		this.parking.releaseSlot(gasSlot1.get());
		// release slot already released.
		assertTrue(this.parking.releaseSlot(gasSlot1.get()));
		// gasoline type slot is free again. Lets book it again.
		Optional<Slot> reBookedSlot = this.parking.bookSlot(this.gasCar, resMaracana1Completed);
		assertEquals(new SlotMaracana(1, CarType.GASOLINE), reBookedSlot.get());

	}

	@Test
	public void InvoceSlot() {
		Reservation res = new ReservationMaracana();
		res.initReservation(this.gasCar, this.gasSlot);
		// ParkingToll has fixed price strategy
		Price expectedPrice = new Price(Double.valueOf(10), Currency.EUROS);
		Price price = parking.calculatePrice(res);
		assertEquals(expectedPrice, price);

	}

}