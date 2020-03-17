package parkingtoll;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import parkingtoll.progressivePrincingPolicy;
import parkingtoll.ParkingToll;
import parkingtoll.Slot;

/**
 * this is Default impelmentation of ParkingToll. Used for testing. it can be
 * used as an example by library users to create there new Parking.
 * 
 * @author jlm
 *
 */
public class ParkingMaracana extends ParkingToll implements progressivePrincingPolicy {

	private int gasSlotNb = 20;
	private int elec20KwSlotNb = 20;
	private int elec50KwSlotNb = 20;

	protected ParkingMaracana() {
		super();
		Set<Slot> freeSlots = generateSlots();
		super.setReservations(new HashSet<Reservation>());
		super.setSlots(freeSlots);
	}

	@Override
	protected Set<Slot> generateSlots() {
		Set<Slot> slotSet = new HashSet<Slot>();
		List<Slot> freeSlot = new ArrayList<>();
		for (Integer slotId = 1; slotId <= gasSlotNb; slotId++) {
			freeSlot.add(new SlotMaracana(slotId, CarType.GASOLINE));
		}
		for (Integer slotId = 1; slotId <= elec20KwSlotNb; slotId++) {
			freeSlot.add(new SlotMaracana(slotId, CarType.ELECTRIC20KW));
		}
		for (Integer slotId = 1; slotId <= elec50KwSlotNb; slotId++) {
			freeSlot.add(new SlotMaracana(slotId, CarType.ELECTRIC50KW));
		}
		slotSet.addAll(freeSlot);
		return slotSet;

	}

}
