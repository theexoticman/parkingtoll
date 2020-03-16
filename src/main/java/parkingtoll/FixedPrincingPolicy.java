package parkingtoll;

import parkingtoll.Currency;
import parkingtoll.Price;
import parkingtoll.PricingPolicy;
import parkingtoll.Reservation;

/**
 * Specific pricing Policy that implements the Princing Policy main method:
 * calculatePrice. This iinterface removes all the pricing policy logic from the
 * Parking Toll when implementing any interface implementing PricingPolicy.
 * 
 * @author jlm
 *
 */
public interface FixedPrincingPolicy extends PricingPolicy {
	public default Price calculatePrice(Reservation res) {
		return new Price(10, Currency.EUROS);
	}
}
