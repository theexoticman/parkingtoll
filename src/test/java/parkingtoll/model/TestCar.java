package parkingtoll.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import parkingtoll.model.Car;
import parkingtoll.model.Electric20kwCar;
import parkingtoll.model.GasolineCar;

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
		assertTrue("Car from different type but same plate should be equal.",carPlate01.equals(carSamePlateDiffType));
		assertNotEquals("Same car type but different license plate should be different.",carPlate01, carDiffPlateSameType);
		assertNotEquals("Different car type but different license plate should be different.",carDiffPlateSameType, carSamePlateDiffType);
	}

}
