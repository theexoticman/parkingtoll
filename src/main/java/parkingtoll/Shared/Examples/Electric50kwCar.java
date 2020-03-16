package parkingtoll.Shared.Examples;

import parkingtoll.Car.Car;
import parkingtoll.Car.CarType;

public class Electric50kwCar extends Car {

	public Electric50kwCar(String licensePlate) {
		super(licensePlate, CarType.ELECTRIC50KW);
	}
}
