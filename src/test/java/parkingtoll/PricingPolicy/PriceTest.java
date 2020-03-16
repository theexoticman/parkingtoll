package parkingtoll.PricingPolicy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class PriceTest {

	private Price tenDoubleEuros;
	private Price tenIntegerEuros;
	private Price tenDoubleDollars;

	@Before
	public void setUp() {
		tenDoubleEuros = new Price(10.0, Currency.EUROS);
		tenIntegerEuros = new Price(10, Currency.EUROS);
		tenDoubleDollars = new Price(10.0, Currency.DOllARS);
	}

	@Test
	public void equalsTest() {
		assertEquals(tenDoubleEuros, tenIntegerEuros);
		assertNotEquals(tenIntegerEuros, tenDoubleDollars);
		assertNotEquals(tenDoubleEuros, tenDoubleDollars);
	}
}
