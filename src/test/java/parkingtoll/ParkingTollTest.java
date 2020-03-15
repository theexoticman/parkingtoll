package parkingtoll;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParkingTollTest {

	ParkingToll parking;
	Car gasCar;
	Car electric20kwCar;
	Car electric50kwCar;
	private final Logger logger = LoggerFactory.getLogger(ParkingToll.class);

	@Before
	public void setup() {
		List<Slot> freeSlots = new ArrayList<>();
		freeSlots.addAll(Arrays.asList(new Slot(1, CarType.GASOLINE), new Slot(2, CarType.ELECTRIC20KW),
				new Slot(3, CarType.ELECTRIC50KW)));

		this.parking = new ParkingToll(freeSlots); // Parking is created with 2 free spot of each type.

		this.gasCar = new Car(1, CarType.GASOLINE);
		this.electric20kwCar = new Car(2, CarType.ELECTRIC20KW);
		this.electric50kwCar = new Car(3, CarType.ELECTRIC50KW);

	}

	@Test
	public void findSlotTest() {
		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Slot gasSlot1 = this.parking.bookSlot(gasCar);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new Slot(1, CarType.GASOLINE), gasSlot1);
		Slot gasSlot2 = this.parking.bookSlot(gasCar);
		// 0 gasoline left fot the new car.
		assertNull(gasSlot2);

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Slot elect20Slot1 = this.parking.bookSlot(electric20kwCar);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new Slot(2, CarType.ELECTRIC20KW), elect20Slot1);
		Slot elect20Slot2 = this.parking.bookSlot(electric20kwCar);
		// 0 gasoline left fot the new car.
		assertNull(elect20Slot2);

		// 1 spot for gasoline cars, 1 spot for electric 20kw and 1 spot for electric
		// 50kw.
		Slot elect50Slot1 = this.parking.bookSlot(electric50kwCar);
		// expected to have a gasoline slot busy. 0 gasoline slot left
		assertEquals(new Slot(3, CarType.ELECTRIC50KW), elect50Slot1);
		Slot elect50Slot2 = this.parking.bookSlot(electric50kwCar);
		// 0 gasoline left fot the new car.
		assertNull(elect50Slot2);
	}

	@After
	public void cleanUp() {

	}
}
