package parkingtoll;

import java.util.Set;

import parkingtoll.progressivePrincingPolicy;
import parkingtoll.ParkingToll;
import parkingtoll.Slot;

/**
 * this is Default impelmentation of ParkingToll. Used for testing. it can be
 * used as an example by library users to create there new Parking.
 * 
 * @author jlm
 *
 */
public class ParkingMaracana extends ParkingToll implements progressivePrincingPolicy {

	public ParkingMaracana(Set<Slot> freeSlots) {
		super(freeSlots);
	}

}
