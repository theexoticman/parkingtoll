package parkingtoll;

public interface PricingPolicy {
	public Price calculatePrice(Reservation res);
}
