package parkingtoll.Shared.Examples;

import parkingtoll.Car.Car;
import parkingtoll.Car.CarType;

public class GasolineCar extends Car {

	public GasolineCar(String licensePlate) {
		super(licensePlate, CarType.GASOLINE);
	}
}
