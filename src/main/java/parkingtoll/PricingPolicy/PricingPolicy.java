package parkingtoll.PricingPolicy;

import parkingtoll.Entity.Reservation;

/**
 * Princing Policy defines the methods that have to be implemented by any
 * Parking Toll Pricing Policy. To remove price clacultion logic from Parking
 * Toll, create an interface that implement Pricing Policy. Parking Toll will
 * implement the new policy.
 */
public interface PricingPolicy {
	public Price calculatePrice(Reservation res);
}
