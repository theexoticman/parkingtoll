package parkingtoll.Vehicules;

/**
 * Car is an abstract class that represent different type of cars. It is defined
 * by a license plate and a type. The type is part of the CarType enum. The list
 * is limited but can be extended. Two cars are considered equal if they have
 * the same license plate.
 * 
 * @author jlm
 *
 */
public abstract class Car {

	private String licensePlate;
	private String type;

	public Car(String licensePlate, Parkable type) {
		this.licensePlate = licensePlate;
		this.type = type.getType();
	}

	public String getType() {
		return this.type;
	}

	public String getLicensePlate() {
		return this.licensePlate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Car car = (Car) obj;
		if (car.getLicensePlate() == null || this.getLicensePlate() == null) {
			return false;
		}
		return car.getLicensePlate().equals(this.getLicensePlate());
	}

	@Override
	public int hashCode() {
		return licensePlate.hashCode();
	}

}
