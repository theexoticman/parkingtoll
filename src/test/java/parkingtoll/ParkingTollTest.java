package parkingtoll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import parkingtoll.Car;
import parkingtoll.CarType;
import parkingtoll.Currency;
import parkingtoll.GasolineCar;
import parkingtoll.ParkingMaracana;
import parkingtoll.ParkingToll;
import parkingtoll.Price;
import parkingtoll.Reservation;
import parkingtoll.Slot;
import parkingtoll.SlotMaracana;

public class ParkingTollTest {

	private ParkingMaracana parking;

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

		this.parking = (ParkingMaracana) ParkingToll.builder("Maracana").get(); // Parking is created with 2 free spot
																				// of each type.

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
		this.parking.setSlots(slots);
		assertEquals(this.parking.getSlots().size(), 1);
	}

	@Test
	public void findSlotTest() throws InterruptedException {
		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Optional<Slot> gasSlot1 = this.parking.bookSlot(gasCar);

		assertEquals(new SlotMaracana(1, CarType.GASOLINE).getType(), gasSlot1.get().getType());

		Optional<Slot> gasSlot2 = this.parking.bookSlot(gasCar);
		// 0 gasoline left fot the new car.

		assertTrue(!gasSlot2.isPresent());

	}

	@Test
	public void releaseSlotTest() throws InterruptedException {
		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Optional<Slot> gasSlot1 = this.parking.bookSlot(this.gasCar);

		Reservation res1Completed = new Reservation(this.gasCar, gasSlot1.get());
		// expected to have a gasoline slot busy. 0 gasoline slot left
		Optional<Reservation> gasSlot1Reservation = this.parking.releaseSlot(gasSlot1.get());

		// release slot already released.
		assertEquals(gasSlot1Reservation.get(), res1Completed);

		// gasoline type slot is free again. Lets book it again.
		Optional<Slot> reBookedSlot = this.parking.bookSlot(this.gasCar);

		assertEquals(gasSlot1.get(), reBookedSlot.get());

	}

	@Test
	public void InvoceSlot() {
		Reservation res = new Reservation(this.gasCar, this.gasSlot);
		res.setDurationHour((long) 3);
		// ParkingToll has fixed price strategy
		Price expectedPrice = new Price(Double.valueOf(25), Currency.EUROS);
		Price price = parking.calculatePrice(res);
		assertEquals(expectedPrice, price);

	}

}