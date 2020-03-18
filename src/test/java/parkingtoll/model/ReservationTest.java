package parkingtoll.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import parkingtoll.model.Car;
import parkingtoll.model.CarType;
import parkingtoll.model.Reservation;
import parkingtoll.model.Slot;

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
		Car car1 = new Car("01XM", CarType.GASOLINE);
		Car carSame1 = new Car("01XM", CarType.GASOLINE);
		Car car2 = new Car("02ZN", CarType.GASOLINE);

		Slot slot1 = new Slot(1, CarType.GASOLINE);
		Slot slotSame1 = new Slot(1, CarType.GASOLINE);
		Slot slot2 = new Slot(2, CarType.ELECTRIC20KW);

		// car 1, slot 1
		this.res1 = new Reservation(car1, slot1);
		this.resSame1 = new Reservation(carSame1, slotSame1);
		// car 1 and slot 2
		this.resSameCarDifferentSlot = new Reservation(car1, slot2);
		// car 2 and slot 1
		this.resSameSlotDifferentCar = new Reservation(car2, slot1);

	}

	@Test
	public void equalsTest() {
		assertEquals("Reservation should be equal, Same car and same slot", res1, resSame1);
		assertNotEquals("Same car but slots are different.", res1, resSameCarDifferentSlot);
		assertNotEquals("Same slot but differnet car.", res1, resSameSlotDifferentCar);
	}

	/**
	 * test if the departure time is set and if is is bigger than arrival time.
	 */
	@Test
	public void closeReservationTest() {
		res1.closeReservation();
		assertTrue("Reservation end must happen after reservation beggining.", res1.getDurationHour() >= 0);
	}

}
