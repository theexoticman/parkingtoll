package parkingtoll.vehicules;

public enum CarType implements Parkable {
	GASOLINE, ELECTRIC20KW, ELECTRIC50KW;

	@Override
	public String getType() {
		return this.name();
	}
}
