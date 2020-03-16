package parkingtoll;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import parkingtoll.Car;
import parkingtoll.Electric20kwCar;
import parkingtoll.GasolineCar;

public class TestCar {

	private Car carPlate01;
	private Car carDiffPlateSameType;
	private Car carSamePlateDiffType;

	@Before
	public void setUp() {
		carPlate01 = new GasolineCar("01");
		carDiffPlateSameType = new GasolineCar("02");
		carSamePlateDiffType = new Electric20kwCar("01");
	}

	@Test
	public void equalsTest() {
		assertTrue(carPlate01.equals(carSamePlateDiffType));
		assertNotEquals(carPlate01, carDiffPlateSameType);
		assertNotEquals(carDiffPlateSameType, carSamePlateDiffType);
	}

}
