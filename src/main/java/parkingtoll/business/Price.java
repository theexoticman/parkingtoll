package parkingtoll.business;

import parkingtoll.business.Convertible;

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

	/**
	 * Create price object 
	 * @param amount, numerical amount
	 * @param currency, currency enum
	 */
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

	/**
	 * get Price amount 
	 * @return, return amount part
	 */
	public Double getAmount() {
		return this.amount;
	}

	/**
	 * get the currency 
	 * @return, currency, from enum
	 */
	public Convertible getCurrency() {
		return this.currency;
	}
}
