package parkingtoll.Entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import parkingtoll.Entity.Default.ParkingMaracana;
import parkingtoll.Entity.Default.ReservationMaracana;
import parkingtoll.Entity.Default.SlotMaracana;
import parkingtoll.Exceptions.SlotNotFoundException;
import parkingtoll.Exceptions.SlotOccupiedException;
import parkingtoll.PricingPolicy.Currency;
import parkingtoll.PricingPolicy.Price;
import parkingtoll.Vehicules.Car;
import parkingtoll.Vehicules.CarType;
import parkingtoll.Vehicules.Electric20kwCar;
import parkingtoll.Vehicules.Electric50kwCar;
import parkingtoll.Vehicules.GasolineCar;

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
		assertEquals(parking.getSlots().size(),1);
	}

	@Test
	public void findSlotTest() throws SlotOccupiedException {
		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana1Completed = new ReservationMaracana();
		Reservation resMaracana1Failed = new ReservationMaracana();
		Slot gasSlot1 = this.parking.bookSlot(gasCar, resMaracana1Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new SlotMaracana(1, CarType.GASOLINE), gasSlot1);
		Slot gasSlot2 = this.parking.bookSlot(gasCar, resMaracana1Failed);
		// 0 gasoline left fot the new car.
		assertNull(gasSlot2);

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana2Completed = new ReservationMaracana();
		Reservation resMaracana2Failed = new ReservationMaracana();
		Slot elec20Slot = this.parking.bookSlot(electric20kwCar, resMaracana2Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new SlotMaracana(2, CarType.ELECTRIC20KW), elec20Slot);
		Slot elect20Slot2 = this.parking.bookSlot(electric20kwCar, resMaracana2Failed);
		// 0 gasoline left fot the new car.
		assertNull(elect20Slot2);

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana3Completed = new ReservationMaracana();
		Reservation resMaracana3Failed = new ReservationMaracana();
		Slot elect50Slot1 = this.parking.bookSlot(electric50kwCar, resMaracana3Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new SlotMaracana(3, CarType.ELECTRIC50KW), elect50Slot1);
		Slot elect50Slot2 = this.parking.bookSlot(electric50kwCar, resMaracana3Failed);
		// 0 gasoline left fot the new car.
		assertNull(elect50Slot2);
	}

	@Test
	public void releaseSlotTest() throws SlotOccupiedException, SlotNotFoundException {
		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana1Completed = new ReservationMaracana();
		Slot gasSlot1 = this.parking.bookSlot(gasCar, resMaracana1Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		this.parking.releaseSlot(gasSlot1);
		// gasoline type slot is free again. Lets book it again.
		Slot reBookedSlot = this.parking.bookSlot(gasCar, resMaracana1Completed);
		assertEquals(new SlotMaracana(1, CarType.GASOLINE), reBookedSlot);

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana2Completed = new ReservationMaracana();
		Slot elect20Slot1 = this.parking.bookSlot(electric20kwCar, resMaracana2Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		this.parking.releaseSlot(elect20Slot1);
		// gasoline type slot is free again. Lets book it again.
		reBookedSlot = this.parking.bookSlot(electric20kwCar, resMaracana2Completed);
		assertEquals(new SlotMaracana(2, CarType.ELECTRIC20KW), reBookedSlot);

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Reservation resMaracana3Completed = new ReservationMaracana();
		Slot elect50Slot1 = this.parking.bookSlot(electric50kwCar, resMaracana3Completed);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		this.parking.releaseSlot(elect50Slot1);
		// gasoline type slot is free again. Lets book it again.
		reBookedSlot = this.parking.bookSlot(electric50kwCar, resMaracana3Completed);
		assertEquals(new SlotMaracana(3, CarType.ELECTRIC50KW), reBookedSlot);
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