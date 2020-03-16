package parkingtoll.Entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import parkingtoll.PricingPolicy.Price;
import parkingtoll.Util.Utils;
import parkingtoll.Vehicules.Car;
import parkingtoll.Vehicules.CarType;
import parkingtoll.Vehicules.Electric20kwCar;
import parkingtoll.Vehicules.GasolineCar;

/**
 * A Reservation is an object that is created when a Car is assigned to a Slot.
 * The arrival Date is the time in which this assigning is done. The departure
 * time is calculated when parking toll release the slot and the Car is detached
 * from the Slot. i.e. another car can book it.
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

		Slot slot1 = new Slot(1, CarType.GASOLINE);
		Slot slotSame1 = new Slot(1, CarType.GASOLINE);
		Slot slot2 = new Slot(2, CarType.ELECTRIC20KW);

		this.res1 = new Reservation(car1, slot1);
		this.resSame1 = new Reservation(carSame1, slotSame1);
		this.resSameCarDifferentSlot = new Reservation(car1, slot2);
		this.resSameSlotDifferentCar = new Reservation(car2, slot1);

	}

	@Test
	public void equalsTest() {
		// same license plate and same slot id
		assertEquals(res1, resSame1);
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
