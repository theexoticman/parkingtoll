package parkingtoll.Entity.Default;

import java.util.List;

import parkingtoll.Entity.ParkingToll;
import parkingtoll.Entity.Slot;
import parkingtoll.PricingPolicy.FixedPrincingPolicy;

/**
 * this is Default impelmentation of ParkingToll. Used for testing. it can be
 * used as an example by library users to create there new Parking.
 * 
 * @author jlm
 *
 */
public class ParkingMaracana extends ParkingToll implements FixedPrincingPolicy {

	public ParkingMaracana(List<Slot> freeSlots) {
		super(freeSlots);
	}

}
