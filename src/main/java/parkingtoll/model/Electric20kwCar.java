package parkingtoll.model;

import parkingtoll.model.Car;
import parkingtoll.model.CarType;
/**
 * Electric car type, representing car requiring 20kw charger.
 * 
 * @author jlm
 *
 */
public class Electric20kwCar extends Car {

	/**
	 * Create an Car instance of type Electric20kw.
	 * @param licensePlate, string identifying a car object
	 */
	public Electric20kwCar(String licensePlate) {
		super(licensePlate, CarType.ELECTRIC20KW);
	}
}
