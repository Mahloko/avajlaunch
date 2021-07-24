package za.co.wethinkcode.avajlauncher;

public abstract class Aircraft {
	protected long			id;
	protected String		name;
	protected Coordinates	coordinates;
	private	static long		ID = 0;

	protected Aircraft(final String name, final Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		id = nextId();
	}

	private long nextId() {
		return ++ID;
	}
}
