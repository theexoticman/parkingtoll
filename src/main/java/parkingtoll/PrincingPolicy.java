package parkingtoll;

public interface PrincingPolicy {
	public Price calculatePrice(Reservation res);
}
