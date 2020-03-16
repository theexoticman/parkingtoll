package parkingtoll;

public class NullParameterException extends Exception {

	private static final long serialVersionUID = 3L;

	public NullParameterException(String msg) {
		super(String.format("Parater %s is null", msg));
	}
}
