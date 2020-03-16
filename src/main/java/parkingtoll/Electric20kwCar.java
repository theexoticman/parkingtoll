package parkingtoll;

import parkingtoll.Car;
import parkingtoll.CarType;

public class Electric20kwCar extends Car {

	public Electric20kwCar(String licensePlate) {
		super(licensePlate, CarType.ELECTRIC20KW);
	}
}
