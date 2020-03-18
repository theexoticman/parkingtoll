package parkingtoll.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import parkingtoll.model.CarType;
import parkingtoll.model.Slot;

public class SlotTest {
	private Slot slotGas1;
	private Slot slotGas2;
	private Slot slotElec1;

	@Before
	public void setUp() {
		this.slotGas1 = new Slot(1, CarType.GASOLINE);
		this.slotGas2 = new Slot(2, CarType.GASOLINE);
		this.slotElec1 = new Slot(1, CarType.ELECTRIC20KW);

	}

	@Test
	public void equalsTest() {

		assertEquals("Slots that have the same location id should be equals.", slotGas1, slotElec1);
		assertNotEquals("Slots that have different location id should be not equal.", slotGas1, slotGas2);
		assertEquals("Slots with same location id but different type will be considered the same.", slotGas1,
				slotElec1);
	}

}
