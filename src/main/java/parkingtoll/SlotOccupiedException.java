package parkingtoll;

public class SlotOccupiedException extends Exception {
	public SlotOccupiedException(Integer location) {
		super(String.format("Slot %d is already occupied by another car!", location));
	}
}
