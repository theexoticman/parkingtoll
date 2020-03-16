package parkingtoll;

public interface FixedPrincingPolicy extends PricingPolicy {
	public default Price calculatePrice(Reservation res) {
		return new Price(10, Currency.EUROS);
	}
}
