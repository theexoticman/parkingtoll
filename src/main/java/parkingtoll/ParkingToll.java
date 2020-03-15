package parkingtoll;

import java.util.ArrayList;
import java.util.List;

public class ParkingToll {

	List<Slot> freeSlots;
	List<Slot> busySlots;

	public ParkingToll(List<Slot> freeSlots) {
		this.freeSlots = freeSlots;
		this.busySlots = new ArrayList<>();
	}

	public Slot findSlot(Car car) {
		return new Slot(0, car.getType());
	}

}
