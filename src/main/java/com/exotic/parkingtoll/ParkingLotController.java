package com.exotic.parkingtoll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ParkingLotController {

	private PricingService pricingService;

	@PostMapping
	@RequestMapping("/price")
	Price calcuatePrice(@RequestParam Slot slot) {
		return null;
	}

	@GetMapping(value = "/slot", params = "slotId", produces = "application/json")
	Slot slot(@RequestParam int locationId) {
		Slot freeSlot = new Slot();
		return freeSlot;
	}

	@PostMapping
	@RequestMapping("/slot")
	Slot slot(@RequestParam String carType) {
		return new Slot();
	}

}
