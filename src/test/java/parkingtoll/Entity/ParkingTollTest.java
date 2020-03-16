package parkingtoll.Entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import parkingtoll.Car.Car;
import parkingtoll.Car.CarType;
import parkingtoll.Exception.NullParameterException;
import parkingtoll.Exceptions.SlotNotFoundException;
import parkingtoll.Exceptions.SlotOccupiedException;
import parkingtoll.PricingPolicy.Currency;
import parkingtoll.PricingPolicy.Price;
import parkingtoll.Shared.ParkingToll;
import parkingtoll.Shared.Reservation;
import parkingtoll.Shared.Slot;
import parkingtoll.Shared.Examples.Electric20kwCar;
import parkingtoll.Shared.Examples.Electric50kwCar;
import parkingtoll.Shared.Examples.GasolineCar;
import parkingtoll.Shared.Examples.ParkingMaracana;
import parkingtoll.Shared.Examples.ReservationMaracana;
import parkingtoll.Shared.Examples.SlotMaracana;

public class ParkingTollTest {

	private ParkingToll parking;

	private Car gasCar;
	private Car electric20kwCar;
	private Car electric50kwCar;

	private Slot gasSlot;
	private Slot elec20Slot;
	private Slot elec50Slot;

	@Before
	public void setup() {
		Set<Slot> freeSlots = new HashSet<>();
		freeSlots.addAll(Arrays.asList(new SlotMaracana(1, CarType.GASOLINE), new SlotMaracana(2, CarType.ELECTRIC20KW),
				new SlotMaracana(3, CarType.ELECTRIC50KW)));

		this.parking = new ParkingMaracana(freeSlots); // Parking is created with 2 free spot of each type.

		// init car types
		this.gasCar = new GasolineCar("GAS000");
		this.electric20kwCar = new Electric20kwCar("ELE020");
		this.electric50kwCar = new Electric50kwCar("ELE050");

		// init the slot type
		this.gasSlot = new SlotMaracana(1, CarType.GASOLINE);
		this.gasSlot = new SlotMaracana(2, CarType.GASOLINE);
		this.gasSlot = new SlotMaracana(1, CarType.GASOLINE);

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
	public void findSlotTest() throws SlotOccupiedException, NullParameterException {
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

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana2Completed = new ReservationMaracana();
		Reservation resMaracana2Failed = new ReservationMaracana();
		Optional<Slot> elec20Slot = this.parking.bookSlot(electric20kwCar, resMaracana2Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new SlotMaracana(2, CarType.ELECTRIC20KW), elec20Slot.get());
		Optional<Slot> elect20Slot2 = this.parking.bookSlot(electric20kwCar, resMaracana2Failed);
		// 0 gasoline left fot the new car.
		assertFalse(elect20Slot2.isPresent());

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana3Completed = new ReservationMaracana();
		Reservation resMaracana3Failed = new ReservationMaracana();
		Optional<Slot> elect50Slot1 = this.parking.bookSlot(electric50kwCar, resMaracana3Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new SlotMaracana(3, CarType.ELECTRIC50KW), elect50Slot1.get());
		Optional<Slot> elect50Slot2 = this.parking.bookSlot(electric50kwCar, resMaracana3Failed);
		// 0 gasoline left fot the new car.
		assertFalse(elect50Slot2.isPresent());
	}

	@Test
	public void releaseSlotTest() throws SlotOccupiedException, SlotNotFoundException, NullParameterException {
		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana1Completed = new ReservationMaracana();
		Optional<Slot> gasSlot1 = this.parking.bookSlot(gasCar, resMaracana1Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		this.parking.releaseSlot(gasSlot1.get());
		// gasoline type slot is free again. Lets book it again.
		Optional<Slot> reBookedSlot = this.parking.bookSlot(gasCar, resMaracana1Completed);
		assertEquals(new SlotMaracana(1, CarType.GASOLINE), reBookedSlot.get());

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana2Completed = new ReservationMaracana();
		Optional<Slot> elect20Slot1 = this.parking.bookSlot(electric20kwCar, resMaracana2Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		this.parking.releaseSlot(elect20Slot1.get());
		// gasoline type slot is free again. Lets book it again.
		reBookedSlot = this.parking.bookSlot(electric20kwCar, resMaracana2Completed);
		assertEquals(new SlotMaracana(2, CarType.ELECTRIC20KW), reBookedSlot.get());

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana3Completed = new ReservationMaracana();
		Optional<Slot> elect50Slot1 = this.parking.bookSlot(electric50kwCar, resMaracana3Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		this.parking.releaseSlot(elect50Slot1.get());
		// gasoline type slot is free again. Lets book it again.
		reBookedSlot = this.parking.bookSlot(electric50kwCar, resMaracana3Completed);
		assertEquals(new SlotMaracana(3, CarType.ELECTRIC50KW), reBookedSlot.get());
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