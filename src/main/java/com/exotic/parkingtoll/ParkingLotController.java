package com.exotic.parkingtoll;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {

	@PostMapping 
    @RequestMapping("api/v1/price")
    Price calcuatePrice(@RequestParam int locationId) {
    	return new Price();
    }
    
    @GetMapping 
    @RequestMapping("api/v1/slot")
    Slot slot(@RequestParam int locationId) {
    	Slot freeSlot = new Slot();
    	return freeSlot;
    }
    
    @PostMapping 
    @RequestMapping("api/v1/slot")
    Slot slot(@RequestParam String carType) {
    	return new Slot();
    }

}
