package parkingtoll.model;

import parkingtoll.model.Car;
import parkingtoll.model.CarType;
/**
 * Gasoline car type, representing car needing gasoline fuel.
 *
 * @author jlm
 *
 */
public class GasolineCar extends Car {

	/**
	 * Create an Car instance of type Gasoline.
	 * @param licensePlate, string identifying a car object
	 */
	public GasolineCar(String licensePlate) {
		super(licensePlate, CarType.GASOLINE);
	}
}
