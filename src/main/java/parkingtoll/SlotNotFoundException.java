package parkingtoll;

public class SlotNotFoundException extends Exception {
	public SlotNotFoundException(Integer slotId) {
		super(String.format("Slot %d not found!", slotId));
	}
}
