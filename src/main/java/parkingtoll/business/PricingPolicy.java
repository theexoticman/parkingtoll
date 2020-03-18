package parkingtoll.business;

import parkingtoll.model.Reservation;

/**
 * Princing Policy defines the methods that have to be implemented by any
 * Parking Toll Pricing Policy. To remove price clacultion logic from Parking
 * Toll, create an interface that implement Pricing Policy. Parking Toll will
 * implement the new policy.
 */
public interface PricingPolicy {
	/**
	 * Calculate Price must be implemented by every interface implementing pricing
	 * policy. It will be implemented by the ParkingToll class
	 * 
	 * @param res, reservation holding infromation for invocing.
	 * @return price, amount and currency, as implemented by the policy.
	 */
	public Price calculatePrice(Reservation res);
}
