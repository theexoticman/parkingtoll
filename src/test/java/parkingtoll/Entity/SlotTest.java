package parkingtoll.Entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import parkingtoll.Entity.Default.SlotMaracana;
import parkingtoll.Vehicules.CarType;

public class SlotTest {
	private Slot slotGas1;
	private Slot slotGas2;
	private Slot slotElec1;

	@Before
	public void setUp() {
		this.slotGas1 = new SlotMaracana(1, CarType.GASOLINE);
		this.slotGas2 = new SlotMaracana(2, CarType.GASOLINE);
		this.slotElec1 = new SlotMaracana(1, CarType.ELECTRIC20KW);

	}

	@Test
	public void equalsTest() {
		// same locatio id.
		assertEquals(slotGas1, slotElec1);
		// different location id
		assertNotEquals(slotGas1, slotGas2);
		// different location id and car type
		assertNotEquals(slotGas2, slotElec1);
	}

}
