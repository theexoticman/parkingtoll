package parkingtoll;

/**
 * Default Enum listing the types of car. It is used for instantiating a Car
 * instance. One can add extend this feature and define more types of car
 * creating an enum and implementing the Parkable interface.
 * 
 * @author jlm
 *
 */
public enum CarType implements Parkable {
	GASOLINE, ELECTRIC20KW, ELECTRIC50KW;

	@Override
	public String getType() {
		return this.name();
	}
}
