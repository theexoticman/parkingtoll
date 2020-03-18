package parkingtoll.model;

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
	private Parkable type;

	/**
	 * Main constructor
	 * 
	 * @param licensePlate, string car identification
	 * @param type,         cartype enum
	 */
	public Car(String licensePlate, Parkable type) {
		this.licensePlate = licensePlate;
		this.type = type;
	}

	/**
	 * Get car type,
	 * 
	 * @return string, car type, enum representation.
	 */
	protected String getType() {
		return this.type.getType();
	}

	/**
	 * get license plate, car identification 
	 * @return, string, car identification
	 */
	protected String getLicensePlate() {
		return this.licensePlate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
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
