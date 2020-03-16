package parkingtoll;

import parkingtoll.Car;
import parkingtoll.CarType;

public class GasolineCar extends Car {

	public GasolineCar(String licensePlate) {
		super(licensePlate, CarType.GASOLINE);
	}
}
