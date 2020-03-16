package parkingtoll.Shared.Examples;

import parkingtoll.Car.Car;
import parkingtoll.Car.CarType;

public class Electric20kwCar extends Car {

	public Electric20kwCar(String licensePlate) {
		super(licensePlate, CarType.ELECTRIC20KW);
	}
}
