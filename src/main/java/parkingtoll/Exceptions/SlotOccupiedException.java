package parkingtoll.Exceptions;

/**
 * Exception generated when slot is occupied.Reveal a problem on Library usage.
 * 
 * @author jlm
 *
 */
public class SlotOccupiedException extends Exception {
	public SlotOccupiedException(Integer location) {
		super(String.format("Slot %d is already occupied by another car!", location));
	}
}
