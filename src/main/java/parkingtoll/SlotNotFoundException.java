package parkingtoll;

/**
 * Exception when slot is not found in the parking.
 */
public class SlotNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public SlotNotFoundException(Integer slotId) {
		super(String.format("Slot %d not found!", slotId));
	}
}
