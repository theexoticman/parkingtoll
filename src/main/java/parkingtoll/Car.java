package parkingtoll;

public class Car {

	private Integer id;
	private CarType type;

	public Car(Integer id, CarType type) {
		this.id = id;
		this.type = type;
	}

	public CarType getType() {
		return this.type;
	}
}
