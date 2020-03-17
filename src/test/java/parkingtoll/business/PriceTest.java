package parkingtoll.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import parkingtoll.business.Currency;
import parkingtoll.business.Price;

public class PriceTest {

	private Price tenDoubleEuros;
	private Price tenIntegerEuros;
	private Price tenDoubleDollars;
	private Price fiveDoubleDollars;

	@Before
	public void setUp() {
		tenDoubleEuros = new Price(10.0, Currency.EUROS);
		tenIntegerEuros = new Price(10, Currency.EUROS);
		tenDoubleDollars = new Price(10.0, Currency.DOllARS);
		tenDoubleDollars = new Price(5.0, Currency.DOllARS);
	}

	@Test
	public void equalsTest() {
		assertEquals("Price with same Currency and same amount should be equal", tenDoubleEuros, tenIntegerEuros);
		assertNotEquals("Price with different Currency and same amount should be not equal", tenIntegerEuros,
				tenDoubleDollars);
		assertNotEquals("Price with same Currency and but different amount should be equal", tenDoubleDollars,
				fiveDoubleDollars);
	}
}
