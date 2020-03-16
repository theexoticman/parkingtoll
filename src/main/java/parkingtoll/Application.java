package parkingtoll;

import parkingtoll.vehicules.CarType;

public class Application {

	public static void main(String[] args) {
		Enum e = CarType.GASOLINE;
		System.out.println(e.name());
	}
}
