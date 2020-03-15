package parkingtoll;

public class Price {
	private Double amount;
	private Currency currency;

	public Price(Double amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public Price(Integer amount, Currency currency) {
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

	private Currency getCurrency() {
		return this.currency;
	}
}
