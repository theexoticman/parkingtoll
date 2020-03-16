package parkingtoll.Entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import parkingtoll.Car.Car;
import parkingtoll.Car.CarType;
import parkingtoll.PricingPolicy.Price;
import parkingtoll.Shared.Reservation;
import parkingtoll.Shared.Slot;
import parkingtoll.Shared.Examples.Electric20kwCar;
import parkingtoll.Shared.Examples.GasolineCar;
import parkingtoll.Shared.Examples.ReservationMaracana;
import parkingtoll.Shared.Examples.SlotMaracana;
import parkingtoll.Util.Utils;

/**
 * A Reservation is an object that is created when a Car is assigned to a Slot.
 * The arrival Date is the time in which this assigning is done. The departure
 * time is calculated when parking toll release the slot and the Car is detached
 * from the Slot. i.e. another car can book it. ======= Testing the reservation
 * testing class >>>>>>> feature/make_abstract
 * 
 * @author jlm
 *
 */
public class ReservationTest {

	private Reservation res1;
	private Reservation resSame1;
	private Reservation resSameCarDifferentSlot;
	private Reservation resSameSlotDifferentCar;

	@Before
	public void setUp() {
		Car car1 = new GasolineCar("01XM");
		Car carSame1 = new GasolineCar("01XM");
		Car car2 = new Electric20kwCar("02ZN");

		Slot slot1 = new SlotMaracana(1, CarType.GASOLINE);
		Slot slotSame1 = new SlotMaracana(1, CarType.GASOLINE);
		Slot slot2 = new SlotMaracana(2, CarType.ELECTRIC20KW);

		this.res1 = new ReservationMaracana();
		res1.initReservation(car1, slot1);
		this.resSame1 = new ReservationMaracana();
		resSame1.initReservation(carSame1, slotSame1);
		this.resSameCarDifferentSlot = new ReservationMaracana();
		resSameCarDifferentSlot.initReservation(car1, slotSame1);
		this.resSameSlotDifferentCar = new ReservationMaracana();
		resSameSlotDifferentCar.initReservation(car2, slot1);

	}

	@Test
	public void equalsTest() {
		// same license plate and same slot id
		assertNotEquals(res1, resSame1);
		// Same car but different license plate
		assertNotEquals(res1, resSameCarDifferentSlot);
		// Same license plate but different car
		assertNotEquals(res1, resSameSlotDifferentCar);
	}

	/**
	 * test if the departure time is set and if is is bigger than arrival time.
	 */
	@Test
	public void closeReservationTest() {
		res1.closeReservation();
		assertTrue(res1.getDurationMin() > 0);
	}

}
