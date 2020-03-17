package parkingtoll.model;

import parkingtoll.model.Car;
import parkingtoll.model.CarType;

/**
 * Electric car type, representing car requiring 50kw charger.
 *
 * @author jlm
 *
 */
public class Electric50kwCar extends Car {

	/**
	 * Create an Car instance of type Electric50kw.
	 * @param licensePlate, string identifying a car object
	 */
	public Electric50kwCar(String licensePlate) {
		super(licensePlate, CarType.ELECTRIC50KW);
	}
}
