package parkingtoll.business;

import parkingtoll.business.Currency;
import parkingtoll.business.Price;
import parkingtoll.business.PricingPolicy;
import parkingtoll.model.Reservation;

/**
 * Specific pricing Policy that implements the Princing Policy main method:
 * calculatePrice. This iinterface removes all the pricing policy logic from the
 * Parking Toll when implementing any interface implementing PricingPolicy.
 * 
 * @author jlm
 *
 */
public interface AfinePrincingPolicy extends PricingPolicy {

	final double fixFare = 10.0;
	final double hourRate = 5;
	final Currency currency = Currency.EUROS;

	public default Price calculatePrice(Reservation res) {
		Integer hours = Math.toIntExact(res.getDurationHour());
		double varFare = hours * hourRate;
		return new Price(fixFare + varFare, currency);
	}
}
