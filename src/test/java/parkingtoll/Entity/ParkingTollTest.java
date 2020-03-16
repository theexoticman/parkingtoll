package parkingtoll.Entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		List<Slot> freeSlots = new ArrayList<>();
		freeSlots.addAll(Arrays.asList(new Slot(1, CarType.GASOLINE.toString()),
				new Slot(2, CarType.ELECTRIC20KW.toString()), new Slot(3, CarType.ELECTRIC50KW.toString())));

		this.parking = new ParkingToll(freeSlots); // Parking is created with 2 free spot of each type.

		// init car types
		this.gasCar = new GasolineCar("GAS000");
		this.electric20kwCar = new Electric20kwCar("ELE020");
		this.electric50kwCar = new Electric50kwCar("ELE050");

		// init the slot type
		this.gasSlot = new Slot(1, CarType.GASOLINE.toString());
		this.gasSlot = new Slot(2, CarType.GASOLINE.toString());
		this.gasSlot = new Slot(1, CarType.GASOLINE.toString());
		
	}

	@Test
	public void findSlotTest() throws SlotOccupiedException {
		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Slot gasSlot1 = this.parking.bookSlot(gasCar);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new Slot(1, CarType.GASOLINE.toString()), gasSlot1);
		Slot gasSlot2 = this.parking.bookSlot(gasCar);
		// 0 gasoline left fot the new car.
		assertNull(gasSlot2);

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Slot elec20Slot = this.parking.bookSlot(electric20kwCar);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new Slot(1, CarType.ELECTRIC20KW.toString()), elec20Slot);
		Slot elect20Slot2 = this.parking.bookSlot(electric20kwCar);
		// 0 gasoline left fot the new car.
		assertNull(elect20Slot2);

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Slot elect50Slot1 = this.parking.bookSlot(electric50kwCar);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new Slot(3, CarType.ELECTRIC50KW.toString()), elect50Slot1);
		Slot elect50Slot2 = this.parking.bookSlot(electric50kwCar);
		// 0 gasoline left fot the new car.
		assertNull(elect50Slot2);
	}

	@Test
	public void releaseSlotTest() throws SlotOccupiedException, SlotNotFoundException {
		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Slot gasSlot1 = this.parking.bookSlot(gasCar);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		this.parking.releaseSlot(gasSlot1);
		// gasoline type slot is free again. Lets book it again.
		Slot reBookedSlot = this.parking.bookSlot(gasCar);
		assertEquals(new Slot(1, CarType.GASOLINE.toString()), reBookedSlot);

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Slot elect20Slot1 = this.parking.bookSlot(electric20kwCar);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		this.parking.releaseSlot(elect20Slot1);
		// gasoline type slot is free again. Lets book it again.
		reBookedSlot = this.parking.bookSlot(electric20kwCar);
		assertEquals(new Slot(2, CarType.ELECTRIC20KW.toString()), reBookedSlot);

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Slot elect50Slot1 = this.parking.bookSlot(electric50kwCar);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		this.parking.releaseSlot(elect50Slot1);
		// gasoline type slot is free again. Lets book it again.
		reBookedSlot = this.parking.bookSlot(electric50kwCar);
		assertEquals(new Slot(3, CarType.ELECTRIC50KW.toString()), reBookedSlot);
	}

	@Test
	public void InvoceSlot() {
		Reservation res = new Reservation(this.gasCar, this.gasSlot);
		// ParkingToll has fixed price strategy
		Price expectedPrice = new Price(Double.valueOf(10), Currency.EUROS);
		Price price = parking.calculatePrice(res);
		assertEquals(expectedPrice, price);

	}

	@After
	public void cleanUp() {

	}
}