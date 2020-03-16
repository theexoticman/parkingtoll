package parkingtoll.Exceptions;

/**
 * Exception raise when slot is not found in the parking. Reveal a problem on
 * Library implementation.
 */
public class SlotNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public SlotNotFoundException(Integer slotId) {
		super(String.format("Slot %d not found!", slotId));
	}
}
