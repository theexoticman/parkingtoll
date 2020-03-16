package parkingtoll;

import parkingtoll.Car;
import parkingtoll.CarType;

public class Electric50kwCar extends Car {

	public Electric50kwCar(String licensePlate) {
		super(licensePlate, CarType.ELECTRIC50KW);
	}
}
