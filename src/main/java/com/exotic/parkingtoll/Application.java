package com.exotic.parkingtoll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		initializeApplication();
	}
	
	private static  void initializeApplication(){
		ParkingLot parking = new ParkingLot();
		Integer nbParkingSlots = 50;
		List<Slot> freeSlot = new ArrayList<>();
		for (Integer slotId = 1; slotId <= nbParkingSlots; slotId++) {
			freeSlot.add(new Slot(slotId,CarType.GASOLINE));
		}
		parking.setFreeslots(freeSlot);
		parking.setBusyslots(new ArrayList<>());
	}
}
