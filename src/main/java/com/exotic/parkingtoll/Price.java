package com.exotic.parkingtoll;

public class Price {
	Double amount;
	String currency;

	public Price(Double amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public Price(String currency) {
		this.currency=currency;
	}
}
