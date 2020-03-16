package parkingtoll.PricingPolicy;

import parkingtoll.PricingPolicy.Convertible;

/**
 * 
 * Price represents the Parking lot bill. It is returned by the Parking Toll
 * when invoking calculatePrice method.
 * 
 * @author jlm
 *
 */
public class Price {
	private Double amount;
	private Convertible currency;

	public Price(Double amount, Convertible currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public Price(Integer amount, Convertible currency) {
		this.amount = Double.valueOf(amount);
		this.currency = currency;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Price price = (Price) obj;
		if (price.getCurrency() == null || this.getCurrency() == null) {
			return false;
		}
		if (price.getAmount() == null || this.getAmount() == null) {
			return false;
		}

		if (!price.getAmount().equals(this.getAmount())) {
			return false;
		}
		if (price.getCurrency() != this.getCurrency()) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = amount.hashCode() + currency.hashCode();
		return result;
	}

	private Double getAmount() {
		return this.amount;
	}

	private Convertible getCurrency() {
		return this.currency;
	}
}
